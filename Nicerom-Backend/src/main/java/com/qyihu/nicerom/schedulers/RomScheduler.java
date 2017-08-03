package com.qyihu.nicerom.schedulers;

import com.qyihu.nicerom.service.NiceromConsumeService;
import com.qyihu.nicerom.service.NiceromProduceService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class RomScheduler {

    @Resource
    NiceromProduceService niceromProduceService;

    @Resource
    NiceromConsumeService niceromConsumeService;

    @Scheduled(cron = "0 0/1 * * * ?")
    public void crawlerRomSummary() {
        niceromProduceService.collectRomSummary();
    }

    @Scheduled(cron = "0 0/2 * * * ?")
    public void crawlerRom() {
        niceromConsumeService.saveRom();
    }
}
