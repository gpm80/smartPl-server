package ru.smart.planet.web;

/**
 * Created on 08.06.19.
 */
public class Product {

    private String uid;
    private Manufacturer manufacturer;
    private String title;
    private String description;
    private String fullDescription;
    private BioStatus bioStatus;
    private Category category;
    private String group;
    private String srcImage;

    public Product() {
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
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

    public String getSrcImage() {
        return srcImage;
    }

    public void setSrcImage(String srcImage) {
        this.srcImage = srcImage;
    }
}
