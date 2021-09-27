package com.myproject.model;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "provices")
public class Province {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nameProvince;
    @OneToMany(targetEntity = Customer.class)
    private List<Customer> customerList;

    public Province() {
    }

    public Province(Long id, String nameProvince, List<Customer> customerList) {
        this.id = id;
        this.nameProvince = nameProvince;
        this.customerList = customerList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameProvince() {
        return nameProvince;
    }

    public void setNameProvince(String nameProvince) {
        this.nameProvince = nameProvince;
    }

    public List<Customer> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(List<Customer> customerList) {
        this.customerList = customerList;
    }
}
