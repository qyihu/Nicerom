package com.qyihu.nicerom.threads;

import com.qyihu.nicerom.callback.PersistCallback;
import com.qyihu.nicerom.model.Rom;
import com.qyihu.nicerom.model.RomSummary;
import com.qyihu.nicerom.service.BaseService;
import com.qyihu.nicerom.utils.Constants;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.client.AsyncRestTemplate;

public class HandleRoms extends BaseService implements Runnable {

    AsyncRestTemplate asyncRestTemplate;

    PersistCallback persistCallback;

    RomSummary romSummary;

    public HandleRoms(RomSummary romSummary, AsyncRestTemplate asyncRestTemplate, PersistCallback persistCallback) {
        this.asyncRestTemplate = asyncRestTemplate;
        this.persistCallback = persistCallback;
        this.romSummary = romSummary;
    }

    Logger logger = LoggerFactory.getLogger(HandleRoms.class);

    @Override
    public void run() {
        httpGet(romSummary.getLink(), new ListenerCallback(), asyncRestTemplate);
    }

    private class ListenerCallback implements ListenableFutureCallback {

        Logger logger = LoggerFactory.getLogger(ListenerCallback.class);

        @Override
        public void onFailure(Throwable ex) {
            logger.error("http request rom failed", ex);
        }

        @Override
        public void onSuccess(Object result) {
            Rom rom = parseHTML(((ResponseEntity<String>)result).getBody());
            logger.info("collect rom into db: {}", rom);
            persistCallback.persist(rom);
        }

        public Rom parseHTML(String html) {
            Document doc = Jsoup.parse(html);
            Element element = doc.body();
            Element row = element.select("div#wrap > div.page-heading > div.container > div.row").first();
            String bigPicture = row.select("div#game-cover > img:first-child").attr("src");
            Element gameInfo = row.select("div#game-info").first();
            String name = gameInfo.select("h1:first-child").text();
//            String genre = gameInfo.select("div#mobile-stats span.genre").text();
            String rating = gameInfo.select("div#mobile-stats > div:nth-child(2)").text();
            Element tBody = gameInfo.select("table > tBody").first();
            String fileName = tBody.select("tr:nth-child(1) td:nth-child(2)").first().ownText();
            String fileSize = tBody.select("tr:nth-child(2) td:nth-child(2)").first().ownText();
            String region = tBody.select("tr:nth-child(3) td:nth-child(2)").text();
            String console = tBody.select("tr:nth-child(4) td:nth-child(2)").first().ownText();
            String views = tBody.select("tr:nth-child(5) td:nth-child(2)").first().ownText();
            Rom rom = new Rom();
            rom.setName(name);
            rom.setFileName(fileName);
            rom.setFileSize(fileSize);
            rom.setRegion(region);
            rom.setConsole(console);
            rom.setViews(convertViews(views));
            rom.setRating(convertRating(rating));
            rom.setBigPicture(bigPicture);
            rom.setDownloadLink(convertDownLoadLink());
            rom.setStatus(Rom.YES);
            rom.setGenra(romSummary.getGenra());
            rom.setYear(romSummary.getYear());
            rom.setThumbnail(romSummary.getThumbnail());
            rom.setLink(romSummary.getLink());
            rom.setType(rom.getType());
            return rom;
        }

        private long convertViews(String views) {
            return Long.parseLong(views.replace(",", ""));
        }

        private String convertDownLoadLink() {
            String downLinkId = romSummary.getLink().substring(romSummary.getLink().lastIndexOf(Constants.SLASH) + 1);
            return String.format(Constants.DOWNLOAD_LINK, downLinkId);
        }

        private double convertRating(String rating) {
            rating = rating.split(":")[1].split("/")[0];
            return Double.parseDouble(rating.trim());
        }
    }
}
