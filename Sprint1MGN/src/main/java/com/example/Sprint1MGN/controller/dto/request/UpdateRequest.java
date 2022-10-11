package com.example.Sprint1MGN.controller.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRequest {
    //kacType 不提供修改
    @NotBlank(message = "id should not be blank")
    @Length(max = 21, message = "id should less than 21 characters")
    private String id;
    @Length(max = 7, message = "cmNo should less than 7 characters")
    private String cmNo; //結算會員代號及名稱

    @Length(max = 3, message = "bankNo should less than 3 characters")
    private String bankNo; //存入結算銀行代號及名稱
    @Length(max = 3, message = "ccy should less than 3 characters")
    @Pattern(regexp = "[A-Z]{3}", message = "ccy should be 3 English letters")
    private String ccy; //存入幣別 TWD
    @Length(min = 1, max = 1, message = "pvType should be 1 character")
    @Pattern(regexp = "[12]", message = "pvType should be 1 or 2")
    private String pvType; //存入方式 1虛擬帳戶 2實體帳戶
    @Length(max = 21, message = "bicaccNo should less than 21 characters")
    private String bicaccNo; //實體帳號/虛擬帳號

    @Valid
    private List<ClearingAccount> clearingAccountList;//存入結算賬號
    @Length(max = 120, message = "ctName should less than 120 characters")
    private String ctName; //聯絡人
    @Length(max = 30, message = "ctTel should less than 30 characters")
    private String ctTel; //電話

    //can be null
    @Pattern(regexp = "[1234]?", message = "iType should be 1,2,3 or 4") // 交割金注意必填, 保證金無值
    @Length(min = 1, max = 1, message = "please choose iType")
    private String iType; //存入類別 1開業 2續繳 3其他 4額外分擔金額
    @Length(max = 50, message = "pReason should less than 50 characters")
    private String pReason; //存入實體帳號原因


}
