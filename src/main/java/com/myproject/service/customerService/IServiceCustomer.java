package com.myproject.service.customerService;

import com.myproject.model.Customer;
import com.myproject.model.Province;

import java.util.List;
import java.util.Optional;

public interface IServiceCustomer {
    List<Customer> showAll();
    void save(Customer customer);
    void delete(long id);
    Optional<Customer> findById(long id);
    Iterable<Customer> findAllByProvince(Province province);
}
