package com.b1a9idps.springcloudcontractsample.consumer.service.impl;

import org.assertj.core.api.Assertions;
import org.assertj.core.groups.Tuple;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties.StubsMode;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.util.UriComponentsBuilder;

import com.b1a9idps.springcloudcontractsample.consumer.dto.Brand;
import com.b1a9idps.springcloudcontractsample.consumer.dto.BrandListDto;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureStubRunner(ids = {"com.b1a9idps.spring-cloud-contract-sample:producer:+:stubs:8080"}, stubsMode = StubsMode.LOCAL)
public class BrandServiceImplTest {

    @Value("${local.server.port}")
    private int port;
    private TestRestTemplate restTemplate = new TestRestTemplate();
    private UriComponentsBuilder uriComponentsBuilder;

    @Before
    public void setup() {
        UriComponentsBuilder builder = UriComponentsBuilder.newInstance();
        builder.scheme("http")
                .host("localhost")
                .port(port);
        this.uriComponentsBuilder = builder;
    }


    @Test
    public void list() {
        BrandListDto result =
                restTemplate.getForObject(uriComponentsBuilder.path("brands").build().toUriString(), BrandListDto.class);
        Assertions.assertThat(result.getBrands())
                .extracting(Brand::getName, Brand::getDesigner)
                .containsExactly(Tuple.tuple("STOF", "Tanita"));
    }
}
