package com.b1a9idps.springcloudcontractsample.consumer.service.impl;

import java.net.URI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.b1a9idps.springcloudcontractsample.consumer.dto.BrandListDto;
import com.b1a9idps.springcloudcontractsample.consumer.service.BrandService;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Service
public class BrandServiceImpl implements BrandService {

    private final RestTemplate restTemplate;
    private final URI producerUrl;

    public BrandServiceImpl(RestTemplateBuilder restTemplateBuilder, @Value("${producer.url}") String producerUrl) {
        this.restTemplate = restTemplateBuilder.build();
        this.producerUrl = UriComponentsBuilder.fromHttpUrl(producerUrl).build().toUri();
    }

    @Override
    public BrandListDto list() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON_VALUE);

        ResponseEntity<BrandListDto> responseEntity = restTemplate.exchange(
                producerUrl,
                HttpMethod.GET,
                new HttpEntity<>(httpHeaders),
                new ParameterizedTypeReference<>() {});

        return responseEntity.getBody();
    }
}
