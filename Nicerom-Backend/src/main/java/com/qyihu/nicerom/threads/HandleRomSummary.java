package com.qyihu.nicerom.threads;

import com.qyihu.nicerom.model.RomSummary;
import com.qyihu.nicerom.service.BaseService;
import com.qyihu.nicerom.utils.Constants;
import com.qyihu.nicerom.utils.Container;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.client.AsyncRestTemplate;

import java.util.ArrayList;
import java.util.List;

public class HandleRomSummary extends BaseService implements Runnable {

    static Logger logger = LoggerFactory.getLogger(HandleRomSummary.class);

    String url;
    AsyncRestTemplate asyncRestTemplate;
    public HandleRomSummary(String url, AsyncRestTemplate asyncRestTemplate) {
        this.url = url;
        this.asyncRestTemplate = asyncRestTemplate;
    }

    @Override
    public void run() {
        httpGet(url, new ListenerCallback(), asyncRestTemplate);
    }

    private class ListenerCallback implements ListenableFutureCallback {
        Logger logger = LoggerFactory.getLogger(ListenerCallback.class);
        @Override
        public void onFailure(Throwable ex) {
            logger.error("http request collect rom summary failed: {}", ex);
        }

        @Override
        public void onSuccess(Object result) {
            List<RomSummary> list = parseHTML(result.toString());
            for(RomSummary romSummary : list) {
                try {
                    logger.info("collect rom summary into queue success: {}", romSummary);
                    Container.romSummaries.put(romSummary);
                } catch (InterruptedException e) {
                    logger.error("collect rom summary into queue failed: {} \n error: {}", romSummary, e);
                }
            }
        }

        private List<RomSummary> parseHTML(String html) {
            Document doc = Jsoup.parse(html);
            Element element = doc.body();
            Elements trs = element.select("table[class=table table-striped table-bordered table-list ziriusTable] > tbody > tr");
            List<RomSummary> list = new ArrayList<>();
            trs.forEach(tr -> {
                Elements td1 = tr.select("td:first-child");
                String link = Constants.PROTOCAL + td1.select("a").attr("href");
                String thumbnail = td1.select("a > img").attr("src");
                String name = tr.select("td:nth-child(2) > a > span:nth-child(2)").text();
                String genra = tr.select("td:nth-child(2) > a > div > strong").get(0).nextSibling().outerHtml();
                String year = tr.select("td:nth-child(2) > a > div > strong").get(1).nextSibling().outerHtml();
                RomSummary romSummary = new RomSummary(name, genra, year, thumbnail, link, getType());
                list.add(romSummary);
            });
            return list;
        }
    }

    private String getType() {
        return url.substring(url.lastIndexOf("/") + 1);
    }
}
