package com.qyihu.nicerom.service;

import com.qyihu.nicerom.threads.HandleRomSummary;
import com.qyihu.nicerom.utils.Constants;
import com.qyihu.nicerom.utils.Container;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.AsyncRestTemplate;

import javax.annotation.Resource;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class NiceromProduceService extends BaseService {

    Logger logger = LoggerFactory.getLogger(NiceromProduceService.class);

    private static final String URI = Constants.DOMAIN + Constants.SLASH + Constants.ROMS_TAG + Constants.SLASH;


    @Resource
    AsyncRestTemplate asyncRestTemplate;

    public void collectRomSummary() {
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        Container.romTypes.stream().forEach(romType -> {
            String url = URI + romType;
            cachedThreadPool.execute(new HandleRomSummary(url, asyncRestTemplate));
        });
        cachedThreadPool.shutdown();
    }
}

