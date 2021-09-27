package com.myproject.repository.provinceRP;

import com.myproject.model.Province;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProvinceRP extends PagingAndSortingRepository<Province,Long> {
}
