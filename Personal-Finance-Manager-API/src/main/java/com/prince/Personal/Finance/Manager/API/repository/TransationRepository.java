package com.prince.Personal.Finance.Manager.API.repository;

import com.prince.Personal.Finance.Manager.API.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransationRepository extends JpaRepository<Transaction,Long> {

    List<Transaction> findByUserId(Long userId);
    List<Transaction> findByUserIdAndType(Long userId,String type);
    List<Transaction> findByUserIdAndCategory(Long userId,String category);
}
