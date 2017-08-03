package com.qyihu.nicerom;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class Tests {

    @Test
    public void testParseHTML() throws IOException {
        File file = new File("/Users/weihuang/Downloads/jsoup.html");
        Document doc = Jsoup.parse(file, "UTF-8");
        Element element = doc.body();
        Element row = element.select("div#wrap > div.page-heading > div.container > div.row").first();
        String bigPicture = row.select("div#game-cover > img:first-child").attr("src");
        Element gameInfo = row.select("div#game-info").first();
        String name = gameInfo.select("h1:first-child").text();
        String genre = gameInfo.select("div#mobile-stats span.genre").text();
        float rating = Float.parseFloat(gameInfo.select("div#mobile-stats > div:nth-child(2)").text().split(":")[1].split("/")[0]);
        Element tbody = gameInfo.select("table > tbody").first();
        String fileName = tbody.select("tr:nth-child(1) td:nth-child(2)").first().ownText();
        String fileSize = tbody.select("tr:nth-child(2) td:nth-child(2)").first().ownText();
        String region = tbody.select("tr:nth-child(3) td:nth-child(2)").text();
        String console = tbody.select("tr:nth-child(4) td:nth-child(2)").first().ownText();
        String views = tbody.select("tr:nth-child(5) td:nth-child(2)").first().ownText();
    }
}
