package com.myproject.controller;

import com.myproject.model.Province;
import com.myproject.service.customerService.IServiceCustomer;
import com.myproject.service.provinceService.IServiceProvince;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/homeProvince")
public class ProvinceController {

    @Autowired
    IServiceProvince serviceProvince;

    @Autowired
    IServiceCustomer serviceCustomer;
    @GetMapping("")
    public ModelAndView showListProvince(){
        Iterable<Province> provinces = serviceProvince.showAll();
        ModelAndView mv = new ModelAndView("province/list2");
        mv.addObject("listProvince", provinces);
        return mv;
    }

    @GetMapping("create")
    public ModelAndView showCreateProvinceForm(){
        ModelAndView modelAndView = new ModelAndView("province/create");
        modelAndView.addObject("province", new Province());
        return modelAndView;
    }
    @PostMapping("create")
    public String createProvince(@ModelAttribute("province") Province province, RedirectAttributes redirect){
        serviceProvince.save(province);
        redirect.addFlashAttribute("message","Create province success!!");
        return "redirect:/homeProvince";
    }

    @GetMapping("{id}/edit")
    public ModelAndView showEditProvinceForm(@PathVariable("id")Optional<Province> province){
//        (@PathVariable Long id);
//        Optional<Province> province1 = serviceProvince.findById(id);
        ModelAndView md = new ModelAndView("province/edit");
        md.addObject("province",province.get());
        return md;
    }
    @PostMapping("{id}/edit")
    public String editProvince(@ModelAttribute("province") Province province, RedirectAttributes redirect){
        serviceProvince.save(province);
        redirect.addFlashAttribute("message","Edit province success!!");
        return "redirect:/homeProvince";
    }


    @GetMapping("{id}/delete")
    public ModelAndView showDeleteProvinceForm(@PathVariable("id")Optional<Province> province){
        ModelAndView md = new ModelAndView("province/delete");
        md.addObject("province",province.get());
        return md;
    }
    @PostMapping("{id}/delete")
    public String deleteProvince(@ModelAttribute("province") Province province, RedirectAttributes redirect){
        serviceProvince.delete(province.getId());
        redirect.addFlashAttribute("message","Delete province success!!");
        return "redirect:/homeProvince";
    }

    @GetMapping("{id}/detail")
    public ModelAndView showDetailProvinceForm(@PathVariable("id")Optional<Province> province){
        ModelAndView md = new ModelAndView("province/detail");
        md.addObject("customers",serviceCustomer.findAllByProvince(province.get()));
        return md;
    }
}
