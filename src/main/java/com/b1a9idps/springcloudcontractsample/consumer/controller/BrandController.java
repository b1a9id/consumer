package com.b1a9idps.springcloudcontractsample.consumer.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.b1a9idps.springcloudcontractsample.consumer.dto.BrandListDto;
import com.b1a9idps.springcloudcontractsample.consumer.service.BrandService;

@RestController
@RequestMapping("/brands")
public class BrandController {

    private final BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping
    public BrandListDto list() {
        return brandService.list();
    }
}
