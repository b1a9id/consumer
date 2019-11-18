package com.b1a9idps.springcloudcontractsample.consumer.service.impl;

import org.assertj.core.api.Assertions;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.cloud.contract.stubrunner.junit.StubRunnerExtension;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties.StubsMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.b1a9idps.springcloudcontractsample.consumer.dto.Brand;
import com.b1a9idps.springcloudcontractsample.consumer.service.BrandService;

@ExtendWith({SpringExtension.class, StubRunnerExtension.class})
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureStubRunner(ids = {"com.b1a9idps.spring-cloud-contract-sample:producer:+:stubs:8080"}, stubsMode = StubsMode.LOCAL)
class BrandServiceImplTest {

    @Autowired
    private BrandService brandService;

    @Test
    void list() {
        Assertions.assertThat(brandService.list().getBrands())
                .extracting(Brand::getName, Brand::getDesigner)
                .containsExactly(Tuple.tuple("STOF", "Tanita"));
    }
}
