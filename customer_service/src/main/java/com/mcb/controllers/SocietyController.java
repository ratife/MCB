package com.mcb.controllers;


import com.mcb.dto.SocietyAddUser;
import com.mcb.manager.CustomerService;
import com.mcb.manager.UserService;
import com.mcb.dto.SocietyDTO;
import com.mcb.dto.mapper.SocietyMapper;
import com.mcb.model.Society;
import com.mcb.model.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("/society")
public class SocietyController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<Society> addCustomer(@RequestBody SocietyDTO customerDtao) throws Exception{
        try {

            Society customer = customerService.saveCustomer(SocietyMapper.toEntity(customerDtao));
            if (customer == null){
                throw new Exception("Error to create  found " + customer.toString());
            }else{
                return ResponseEntity.ok(customer);
            }

        }catch (IllegalArgumentException e) {
            throw new Exception(e.getMessage());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<SocietyDTO>> getAllCustomers() {
        List<Society> customers = customerService.findAll();
        List<SocietyDTO> customerDTOs = customers.stream().map(SocietyMapper::toDTO).toList();
        return ResponseEntity.ok(customerDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SocietyDTO> getCustomerById(@PathVariable Long id) {
        Optional<Society> customer = customerService.findById(id);
        return customer.map(value -> ResponseEntity.ok(SocietyMapper.toDTO(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<Society> updateCustomer(@PathVariable Long id, @RequestBody SocietyDTO customerDto) {
        Optional<Society> existingCustomer = customerService.findById(id);
        if (existingCustomer.isPresent()) {
            Society customer = SocietyMapper.upDate(existingCustomer.get(),customerDto);
            Society updatedCustomer = customerService.saveCustomer(customer);
            return ResponseEntity.ok(updatedCustomer);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        Optional<Society> existingCustomer = customerService.findById(id);
        if (existingCustomer.isPresent()) {
            customerService.delete(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/addUser")
    public ResponseEntity<Society> updateCustomer(@RequestBody SocietyAddUser customerAddUser) {
        Optional<Society> existingCustomer = customerService.findById(customerAddUser.getIdCustomer());
        Optional<User> existingUser =userService.findById(customerAddUser.getIdUser());
        if (existingCustomer.isPresent() && existingUser.isPresent()) {
            Society customer = existingCustomer.get();
            User user = existingUser.get();
            customer.addUser(user,customerAddUser.getTypeUser());
            Society updatedCustomer = customerService.saveCustomer(customer);
            return ResponseEntity.ok(updatedCustomer);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
