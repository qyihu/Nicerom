package com.qyihu.nicerom.service;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ExecuteService {

    @Resource
    NiceromProduceService niceromProduceService;

    @Resource
    NiceromConsumeService niceromConsumeService;

    public void execute() {
//        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
//        fixedThreadPool.execute(new Runnable() {
//            @Override
//            public void run() {
//            }
//        });
                niceromProduceService.collectRomSummary();

//        fixedThreadPool.execute(new Runnable() {
//            @Override
//            public void run() {
//                niceromConsumeService.consumeRomSummary();
//            }
//        });
//
//        fixedThreadPool.execute(new Runnable() {
//            @Override
//            public void run() {
//                niceromConsumeService.consumeRom();
//            }
//        });
//        fixedThreadPool.shutdown();
    }

}
