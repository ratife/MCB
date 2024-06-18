package com.mcb.manager;

import com.mcb.model.Society;

import java.util.List;
import java.util.Optional;

public interface CustomerService {

    Optional<Society> findById(Long id);
    Society saveCustomer(Society customer);
    void delete(Long id);
    List<Society> findAll();
}
