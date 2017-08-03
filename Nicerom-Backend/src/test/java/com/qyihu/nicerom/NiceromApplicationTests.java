package com.qyihu.nicerom;

import com.qyihu.nicerom.model.RomSummary;
import com.qyihu.nicerom.service.NiceromConsumeService;
import com.qyihu.nicerom.service.NiceromProduceService;
import com.qyihu.nicerom.utils.Container;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.concurrent.BlockingQueue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NiceromApplicationTests {

	Logger logger = LoggerFactory.getLogger(NiceromApplicationTests.class);

	@Resource
	NiceromProduceService niceromService;

	@Resource
	NiceromConsumeService niceromConsumeService;

	@Test
	public void collectRomSummary() {
		niceromService.collectRomSummary();
		logger.info(Arrays.asList(Container.romSummaries).toString());
	}

	@Test
	public void saveRom() {
		BlockingQueue<RomSummary> queue = Container.romSummaries;
		RomSummary romSummary = new RomSummary();
		romSummary.setLink("https://www.loveroms.com/download/amiga-500/allo-allo-cartoon-fundisk2/58410");
		queue.add(romSummary);
		niceromConsumeService.saveRom();
	}

}
