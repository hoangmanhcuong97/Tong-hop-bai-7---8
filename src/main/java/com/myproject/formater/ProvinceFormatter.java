package com.myproject.formater;

import com.myproject.model.Province;
import com.myproject.service.provinceService.IServiceProvince;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;
@Component
public class ProvinceFormatter implements Formatter<Province> {
    private IServiceProvince iServiceProvince;

    public ProvinceFormatter(IServiceProvince iServiceProvince) {
        this.iServiceProvince = iServiceProvince;
    }

    @Override
    public Province parse(String text, Locale locale) throws ParseException {
        Province province = iServiceProvince.findById(Long.parseLong(text)).get();
        return province;
    }

    @Override
    public String print(Province object, Locale locale) {
        return null;
    }
}
