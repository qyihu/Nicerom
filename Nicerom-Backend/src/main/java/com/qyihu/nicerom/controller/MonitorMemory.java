package com.qyihu.nicerom.controller;

import com.qyihu.nicerom.model.RomSummary;
import com.qyihu.nicerom.utils.Container;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("monitor")
public class MonitorMemory {

    @RequestMapping(value = "romsummary", method = RequestMethod.GET)
    public List<RomSummary> monitorRomSummary() {
        return Container.romSummaries.stream().collect(Collectors.toList());
    }
}
