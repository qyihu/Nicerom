package com.qyihu.nicerom.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Rom extends RomSummary {

    public static final String YES = "YES";
    public static final String NO = "NO";

    @Id
    private String id;
    private String fileName;
    private String fileSize;
    private String region;
    private String console;
    private long views;
    private double rating;
    private String bigPicture;
    private String downloadLink;
    private String status;

    public Rom() {}

    public Rom(String fileName, String fileSize, String region, String console, long views, float rating, String bigPicture, String downloadLink, String status) {
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.region = region;
        this.console = console;
        this.views = views;
        this.rating = rating;
        this.bigPicture = bigPicture;
        this.downloadLink = downloadLink;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getConsole() {
        return console;
    }

    public void setConsole(String console) {
        this.console = console;
    }

    public long getViews() {
        return views;
    }

    public void setViews(long views) {
        this.views = views;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getBigPicture() {
        return bigPicture;
    }

    public void setBigPicture(String bigPicture) {
        this.bigPicture = bigPicture;
    }

    public String getDownloadLink() {
        return downloadLink;
    }

    public void setDownloadLink(String downloadLink) {
        this.downloadLink = downloadLink;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
