package com.qyihu.nicerom.model;

public class RomSummary {
    private String name;
    private String genra;
    private String year;
    private String thumbnail;
    private String link;
    private String type;


    public RomSummary() {
    }

    public RomSummary(String name, String genra, String year, String thumbnail, String link, String type) {
        this.name = name;
        this.genra = genra;
        this.year = year;
        this.thumbnail = thumbnail;
        this.link = link;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenra() {
        return genra;
    }

    public void setGenra(String genra) {
        this.genra = genra;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getLink() {
        return link;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return "RomSummary{" +
                "name='" + name + '\'' +
                ", genra='" + genra + '\'' +
                ", year='" + year + '\'' +
                ", thumbnail='" + thumbnail + '\'' +
                ", link='" + link + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
