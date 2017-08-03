package com.qyihu.nicerom.service;

import com.qyihu.nicerom.model.Rom;
import com.qyihu.nicerom.repository.NiceromRepository;
import com.qyihu.nicerom.threads.HandleRoms;
import com.qyihu.nicerom.utils.Container;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.AsyncRestTemplate;

import javax.annotation.Resource;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class NiceromConsumeService extends BaseService {

    Logger logger = LoggerFactory.getLogger(NiceromConsumeService.class);

    @Resource
    NiceromRepository niceromRepository;

    @Resource
    AsyncRestTemplate asyncRestTemplate;

    public void saveRom() {
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        int length = Container.romSummaries.size();
        for(int i = 0; i < length; i++) {
            cachedThreadPool.execute(new HandleRoms(Container.romSummaries.poll(), asyncRestTemplate, t -> {
                if(niceromRepository.findByLink(((Rom) t).getLink()) == null) {
                    niceromRepository.save((Rom) t);
                }
            }));
        }
    }
}
