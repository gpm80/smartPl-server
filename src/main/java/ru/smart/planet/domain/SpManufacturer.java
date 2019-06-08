package ru.smart.planet.domain;

import org.ektorp.support.CouchDbDocument;

/**
 * Производитель в БД
 * Created on 08.06.19.
 */
public class SpManufacturer extends CouchDbDocument {

    private final String type = "MANUFACTURE";
    private String title;
    private String description;
    private String lng;
    private String lat;

    public String getType() {
        return type;
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
