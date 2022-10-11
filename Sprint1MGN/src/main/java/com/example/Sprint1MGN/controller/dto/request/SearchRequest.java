package com.example.Sprint1MGN.controller.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Pattern;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SearchRequest {
    @Length(max = 21, message = "id should less than 21 characters")
    private String id;
    @Length(max = 7, message = "cmNo should less than 7 characters")
    private String cmNo; //結算會員代號及名稱
    @Length(min = 1, max = 1, message = "kacType should be 1 characters")
    private String kacType; //存入保管專戶別 1結算保證金 2交割結算基金專戶
    @Length(max = 3, message = "bankNo should less than 3 characters")
    private String bankNo; //存入結算銀行代號及名稱
    @Pattern(regexp = "[A-Z]{3}", message = "ccy should be 3 English letters")
    private String ccy; //存入幣別 TWD
    @Length(min = 1, max = 1, message = "pvType should less than 6 characters")
    private String pvType; //存入方式 1虛擬帳戶 2實體帳戶
    @Length(max = 21, message = "bicaccNo should less than 21 characters")
    private String bicaccNo; //實體帳號/虛擬帳號

    @Length(max = 120, message = "ctName should less than 120 characters")
    private String ctName; //聯絡人
    @Length(max = 30, message = "ctTel should less than 30 characters")
    private String ctTel; //電話

    @Length(min = 1, max = 1, message = "iType should less than 6 characters")
    private String iType; //存入類別 1開業 2續繳 3其他 4額外分擔金額

    //    @Length(min = 8, max = 8, message = "date should be 8 characters")
    private String date;

}
