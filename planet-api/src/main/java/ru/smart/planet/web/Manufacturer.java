package ru.smart.planet.web;

import java.io.Serializable;

/**
 * Производитель веб модель
 * Created on 08.06.19.
 */
public class Manufacturer implements Serializable {

    private String uid;
    private String title;
    private String description;
    private String lng;
    private String lat;

    public Manufacturer() {
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }
}
