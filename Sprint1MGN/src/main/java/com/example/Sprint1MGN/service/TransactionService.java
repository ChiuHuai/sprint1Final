package com.example.Sprint1MGN.service;

import com.example.Sprint1MGN.controller.dto.request.ClearingAccount;
import com.example.Sprint1MGN.controller.dto.request.DepositRequest;
import com.example.Sprint1MGN.controller.dto.request.UpdateRequest;
import com.example.Sprint1MGN.controller.dto.response.Response;
import com.example.Sprint1MGN.controller.dto.response.customException.MgnException;
import com.example.Sprint1MGN.model.CashiRepository;
import com.example.Sprint1MGN.model.MgniRepository;
import com.example.Sprint1MGN.model.entity.Cashi;
import com.example.Sprint1MGN.model.entity.Mgni;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(rollbackOn = Exception.class)
public class TransactionService {
    @Autowired
    private MgniRepository mgniRepository;

    @Autowired
    private CashiRepository cashiRepository;

    public Response deposit(DepositRequest request) {

        Mgni mgni = addMgni(request);
        List<ClearingAccount> clearingAccountList = request.getClearingAccountList();
        List<String> distinctAccNos = clearingAccountList.stream().map(e -> e.getAccNo()).distinct()
                .collect(Collectors.toList()); //取出不同 AccNo

        if ("1".equals(request.getKacType())) {
            if (request.getClearingAccountList() == null) {
                throw new MgnException("Clearing accountList should not be null.");
            }
            for (String accNo : distinctAccNos) {
                BigDecimal tempTotalAmt = new BigDecimal(0);
                for (ClearingAccount clearingAccount : clearingAccountList) {
                    if (accNo.equals(clearingAccount.getAccNo())) {
                        tempTotalAmt = tempTotalAmt.add(clearingAccount.getAmt());
                    }
                }
                addCashi(tempTotalAmt, accNo, mgni.getId(), mgni.getCcy());
            }
        }
        return new Response().builder().message("OK").build();
    }

    public Response updateMGN(UpdateRequest request) {
        //find by id
        //update
        Mgni mgni;
        cashiRepository.deleteById(request.getId());
        Optional<Mgni> optionalMgni = mgniRepository.findById(request.getId());

        if (optionalMgni.isPresent()) {
            mgni = optionalMgni.get();
        } else {
            throw new MgnException("this id is not exist");
        }

        updateMgni(mgni, request);


        return new Response().builder().message("OK").build();
    }

    public Mgni updateMgni(Mgni mgni, UpdateRequest request) {
        //id, create time, kacType, type(寫定), status, uTime(自動產生) 不提供修改
        mgni.setUTime(LocalDateTime.now());

        //只有kacType(2) -> 交割金可改itype
        if (mgni.getKacType().equals("2") && request.getIType() != null && !request.getIType().isBlank()) {
            mgni.setIType(request.getIType());
        }

        //只有kacType(1) -> 保證金可改 ClearingAccount 及 amt
        List<ClearingAccount> clearingAccountList = request.getClearingAccountList();
        if (mgni.getKacType().equals("1") && clearingAccountList != null) {
            BigDecimal totalAmt = clearingAccountList.stream().map(e -> e.getAmt()).reduce(BigDecimal.ZERO, BigDecimal::add);
            mgni.setAmt(totalAmt);

            List<String> distinctAccNos = clearingAccountList.stream().map(e -> e.getAccNo()).distinct()
                    .collect(Collectors.toList()); //取出不同 AccNo

            for (String accNo : distinctAccNos) {
                BigDecimal tempTotalAmt = new BigDecimal(0);
                for (ClearingAccount clearingAccount : clearingAccountList) {
                    if (accNo.equals(clearingAccount.getAccNo())) {
                        tempTotalAmt = tempTotalAmt.add(clearingAccount.getAmt());
                    }
                }
                addCashi(tempTotalAmt, accNo, mgni.getId(), mgni.getCcy());
            }
        }

        if (request.getCmNo() != null && !request.getCmNo().isBlank())
            mgni.setCmNo(request.getCmNo().trim());
        if (request.getBankNo() != null && !request.getBankNo().isBlank())
            mgni.setBankNo(request.getBankNo().trim());
        if (request.getCcy() != null && !request.getCcy().isBlank())
            mgni.setCcy(request.getCcy());
        if (request.getPvType() != null && !request.getPvType().isBlank())
            mgni.setPvType(request.getPvType());
        if (request.getBicaccNo() != null && !request.getBicaccNo().isBlank())
            mgni.setBicaccNo(request.getBicaccNo().trim());
        if (request.getPReason() != null && !request.getPReason().isBlank())
            mgni.setPReason(request.getPReason().trim());
        if (request.getCtName() != null && !request.getCtName().isBlank())
            mgni.setCtName(request.getCtName());
        if (request.getCtTel() != null || !request.getCtTel().isBlank())
            mgni.setCtTel(request.getCtTel());

        mgniRepository.save(mgni);
        return mgni;
    }

    public Response deleteMGN(String id) {
        //findById
        //deleteById
        Mgni mgni;
        Optional<Mgni> optionalMgni = mgniRepository.findById(id);
        if (optionalMgni.isPresent()) {
            mgni = optionalMgni.get();
        } else {
            throw new MgnException("this id is not exist");
        }
        mgniRepository.deleteById(id);
        return new Response().builder().message("delete: " + id).build();
    }

    public Mgni addMgni(DepositRequest request) {
        Mgni mgni = new Mgni();
        //kacType(2) -> 交割金 iType 必填
        if (request.getKacType().equals("2")) {
            if (request.getIType() == null || request.getIType().isBlank()) {
                throw new MgnException("please choose itype(1~4).");
            }
            mgni.setIType(request.getIType());
        }
        mgni.setId(idCreater());
        mgni.setTime(LocalDateTime.now());
        mgni.setType("1"); //1入金
        mgni.setCmNo(request.getCmNo());
        mgni.setKacType(request.getKacType());
        mgni.setBankNo(request.getBankNo());
        mgni.setCcy(request.getCcy());
        mgni.setPvType(request.getPvType());
        mgni.setBicaccNo(request.getBicaccNo());
        mgni.setPReason(request.getPReason());
        BigDecimal totalAmt = request.getClearingAccountList().stream().map(e -> e.getAmt()).reduce(BigDecimal.ZERO, BigDecimal::add);
        mgni.setAmt(totalAmt);
        mgni.setCtName(request.getCtName());
        mgni.setCtTel(request.getCtTel());
        mgni.setStatus("0"); //介面沒提供選項，先寫0
        mgni.setUTime(LocalDateTime.now());
        mgniRepository.save(mgni);

        return mgni;
    }

    public void addCashi(BigDecimal amt, String accNo, String id, String ccy) {
        Cashi cashi = new Cashi();
        cashi.setAmt(amt);
        cashi.setAccNo(accNo);
        cashi.setId(id);
        cashi.setCcy(ccy);
        cashiRepository.save(cashi);
    }

    public String idCreater() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
        String now = "MGI" + LocalDateTime.now().format(formatter);
        return now;
    }

}
