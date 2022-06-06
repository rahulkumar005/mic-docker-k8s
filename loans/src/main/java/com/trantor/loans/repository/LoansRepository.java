package com.trantor.loans.repository;

import com.trantor.loans.model.Loans;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface LoansRepository extends CrudRepository<Loans,Long> {
    List<Loans> findByCustomerIdOrderByStartDtDesc(int customerId);
}
