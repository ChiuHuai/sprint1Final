package com.example.Sprint1MGN.model;

import com.example.Sprint1MGN.model.entity.Mgni;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface MgniRepository extends JpaRepository<Mgni, String>, JpaSpecificationExecutor<Mgni> {
}
