package com.myproject.service.provinceService;

import com.myproject.model.Province;
import com.myproject.repository.provinceRP.IProvinceRP;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class ProvinceSv implements IServiceProvince{
    @Autowired
    private IProvinceRP iProvinceRP;
    @Override
    public List<Province> showAll() {
        return (List<Province>) iProvinceRP.findAll();
    }

    @Override
    public void delete(long id) {
        iProvinceRP.deleteById(id);
    }

    @Override
    public void save(Province province) {
        iProvinceRP.save(province);
    }

    @Override
    public Optional<Province> findById(long id) {
        return iProvinceRP.findById(id);
    }
}
