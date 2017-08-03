package com.qyihu.nicerom.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.ResourceAccessException;

public abstract class BaseService {

    Logger logger = LoggerFactory.getLogger(NiceromProduceService.class);

    public void httpGet(String url, ListenableFutureCallback callback, AsyncRestTemplate restTemplate) {
        logger.info("http request url: {}", url);
        try {
            ListenableFuture<ResponseEntity<String>> future = restTemplate.exchange(url, HttpMethod.GET, getHttpEntity(), String.class);
            future.addCallback(callback);
        } catch (ResourceAccessException e) {
            logger.error("http request failed(Recursive): {} \n error: {}", url, e);
            httpGet(url, callback, restTemplate);
        } catch (Exception e) {
            logger.error("http request failed: {} \n error: {}" + url, e);
        }
    }

    private HttpEntity<byte[]> getHttpEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36");
        headers.add("Connection", "close");
        return new HttpEntity<byte[]>(headers);
    }
}
