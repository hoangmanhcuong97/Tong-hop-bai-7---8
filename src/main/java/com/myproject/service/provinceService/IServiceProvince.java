package com.myproject.service.provinceService;

import com.myproject.model.Province;

import java.util.List;
import java.util.Optional;

public interface IServiceProvince {
    List<Province> showAll();
    void save(Province province);
    void delete(long id);
    Optional<Province> findById(long id);
}
