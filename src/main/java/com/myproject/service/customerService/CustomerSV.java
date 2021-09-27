package com.myproject.service.customerService;

import com.myproject.model.Customer;
import com.myproject.model.Province;
import com.myproject.repository.customerRP.ICustomerRP;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class CustomerSV implements IServiceCustomer{
    @Autowired
    private ICustomerRP iCustomerRP;
    @Override
    public List<Customer> showAll() {
        return (List<Customer>) iCustomerRP.findAll();
    }

    @Override
    public void save(Customer customer) {
        iCustomerRP.save(customer);
    }

    @Override
    public void delete(long id) {
        iCustomerRP.deleteById(id);
    }

    @Override
    public Optional<Customer> findById(long id) {
        return iCustomerRP.findById(id);
    }

    @Override
    public Iterable<Customer> findAllByProvince(Province province) {
        return iCustomerRP.findAllByProvince(province);
    }
}
