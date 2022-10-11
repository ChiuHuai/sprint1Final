package com.example.Sprint1MGN.service.tool.searchTool.searchable;

import com.example.Sprint1MGN.controller.dto.request.SearchRequest;
import com.example.Sprint1MGN.controller.dto.response.SearchResponse;

public interface MgniSearchable {
    SearchResponse search(Integer pageNum, Integer pageSize, SearchRequest request);

    //LocalDateTime time
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