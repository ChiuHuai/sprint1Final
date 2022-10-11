package com.example.Sprint1MGN.model;

import com.example.Sprint1MGN.model.entity.Cashi;
import com.example.Sprint1MGN.model.entity.CashiId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface CashiRepository extends JpaRepository<Cashi, CashiId>, JpaSpecificationExecutor<Cashi> {

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM CASHI WHERE CASHI_MGNI_ID = ?1", nativeQuery = true)
    void deleteById(String id);
}
