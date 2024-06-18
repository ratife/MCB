package com.mcb.manager.impl;

import com.mcb.manager.CustomerService;
import com.mcb.repository.CustomerRepository;
import com.mcb.model.Society;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class CustomerServiceImp implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;


    @Override
    public Optional<Society> findById(Long id) {
        return customerRepository.findById(id);
    }

    @Override
    public Society saveCustomer(Society customer) {
        return customerRepository.save(customer);
    }

    @Override
    public void delete(Long id) {
        customerRepository.deleteById(id);
    }

    @Override
    public List<Society> findAll() {
        return customerRepository.findAll();
    }
}
