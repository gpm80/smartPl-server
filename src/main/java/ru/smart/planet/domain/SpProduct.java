package ru.smart.planet.domain;

import org.ektorp.support.CouchDbDocument;
import ru.smart.planet.web.BioStatus;
import ru.smart.planet.web.Category;

/**
 * Продукт
 * Created on 08.06.19.
 */
public class SpProduct extends CouchDbDocument {

    private String manufacturerUid;
    private String title;
    private String description;
    private String fullDescription;
    private BioStatus bioStatus;
    private Category category;
    private String group;

    public SpProduct() {
    }

    public String getManufacturerUid() {
        return manufacturerUid;
    }

    public void setManufacturerUid(String manufacturerUid) {
        this.manufacturerUid = manufacturerUid;
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

    public String getFullDescription() {
        return fullDescription;
    }

    public void setFullDescription(String fullDescription) {
        this.fullDescription = fullDescription;
    }

    public BioStatus getBioStatus() {
        return bioStatus;
    }

    public void setBioStatus(BioStatus bioStatus) {
        this.bioStatus = bioStatus;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}
