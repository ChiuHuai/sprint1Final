package com.example.Sprint1MGN.service.tool.searchTool;

import com.example.Sprint1MGN.controller.dto.request.SearchRequest;
import com.example.Sprint1MGN.model.entity.Mgni;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MgniSpecs {
    public static Specification<Mgni> getSpecs(SearchRequest request) {
        Specification<Mgni> spec = null;
        Specification<Mgni> temp;

        if (null != request.getId()) {
            temp = getMgniById(request.getId());
            spec = addSpec(temp, spec);
        }

        if (null != request.getBicaccNo()) {
            temp = getMgniByEqual("bicaccNo",request.getBicaccNo());
            spec = addSpec(temp, spec);
        }

        if (null != request.getBankNo()) {
            temp = getMgniByEqual("bankNo",request.getBankNo());
            spec = addSpec(temp, spec);
        }

        if (null != request.getCcy()) {
            temp = getMgniByEqual("ccy",request.getCcy());
            spec = addSpec(temp, spec);
        }

        if (null != request.getCmNo()) {
            temp = getMgniByEqual("cmNo",request.getCmNo());
            spec = addSpec(temp, spec);
        }

        if (null != request.getCtName()) {
            temp = getMgniByEqual("ctName",request.getCtName());
            spec = addSpec(temp, spec);
        }

        if (null != request.getCtTel()) {
            temp = getMgniByEqual("ctTel",request.getCtTel());
            spec = addSpec(temp, spec);
        }

        if (null != request.getIType()) {
            temp = getMgniByEqual("iType",request.getIType());
            spec = addSpec(temp, spec);
        }

        if (null != request.getKacType()) {
            temp = getMgniByEqual("kacType",request.getKacType());
            spec = addSpec(temp, spec);
        }

        if (null != request.getPvType()) {
            temp = getMgniByEqual("pvType",request.getPvType());
            spec = addSpec(temp, spec);
        }

        //以創立date搜尋
        if (null != request.getDate()) {
            temp = getMgniByDate("time",request.getDate());
            spec = addSpec(temp, spec);
        }

        return spec;
    }

    private static Specification<Mgni> getMgniByDate(String fieldName, String date) { //查詢時間～now
        LocalDateTime dateTime = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyyMMdd")).atStartOfDay();
        return ((root, query, criteriaBuilder) -> criteriaBuilder
                .between(root.get(fieldName),dateTime,LocalDateTime.now()));
    }

    private static Specification<Mgni> getMgniByEqual(String fieldName,String value) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder
                .equal(root.get(fieldName), value.trim()));
    }

    private static Specification<Mgni> getMgniById(String id) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder
                .equal(root.get("id"), id));
    }

    private static Specification<Mgni> addSpec(Specification<Mgni> temp, Specification<Mgni> spec) {
        return temp != null ? Specification.where(spec).and(temp) : temp;
    }

}

//String id;
//String cmNo; //結算會員代號及名稱
//String kacType; //存入保管專戶別 1結算保證金 2交割結算基金專戶
//String bankNo; //存入結算銀行代號及名稱
//String ccy; //存入幣別 TWD
//String pvType; //存入方式 1虛擬帳戶 2實體帳戶
//String bicaccNo; //實體帳號/虛擬帳號
//String ctName; //聯絡人
//String ctTel; //電話
//    // can be null
// String iType; //存入類別 1開業 2續繳 3其他 4額外分擔金額