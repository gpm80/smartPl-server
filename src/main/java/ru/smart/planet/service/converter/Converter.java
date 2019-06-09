package ru.smart.planet.service.converter;

import org.ektorp.Attachment;
import org.ektorp.support.CouchDbDocument;
import ru.smart.planet.domain.SpManufacturer;
import ru.smart.planet.domain.SpProduct;
import ru.smart.planet.web.Manufacturer;
import ru.smart.planet.web.Product;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created on 08.06.19.
 */
public class Converter {

    public static Product convert(SpProduct val, String absoluteUrl) {
        if (val == null) {
            return null;
        }
        Product p = new Product();
        p.setUid(val.getId());
        p.setTitle(val.getTitle());
        p.setDescription(val.getDescription());
        p.setFullDescription(val.getFullDescription());
        p.setCategory(val.getCategory());
        p.setGroup(val.getGroup());
        p.setBioStatus(val.getBioStatus());
        p.setSrcImage(getUrlImage(val, absoluteUrl));
        //TODO

//        p.setManufacturer(val.getManufacturerUid());
        return p;
    }

    public static SpProduct convert(Product val) {
        SpProduct sp = new SpProduct();
        sp.setTitle(val.getTitle());
        sp.setDescription(val.getDescription());
        sp.setFullDescription(val.getFullDescription());
        sp.setManufacturerUid(val.getManufacturer().getUid());
        sp.setBioStatus(val.getBioStatus());
        sp.setCategory(val.getCategory());
        sp.setGroup(val.getGroup());
        return sp;
    }

    public static List<Product> convertsProd(List<SpProduct> list, String absoluteUrl) {
        return list.stream().map(p -> convert(p, absoluteUrl)).filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    public static Manufacturer convert(SpManufacturer val, String absoluteUrl) {
        if (val == null) {
            return null;
        }
        Manufacturer m = new Manufacturer();
        m.setTitle(val.getTitle());
        m.setDescription(val.getDescription());
        m.setUid(val.getId());
        m.setLat(val.getLat());
        m.setLng(val.getLng());
        m.setSrcImage(getUrlImage(val, absoluteUrl));
        return m;
    }

    public static List<Manufacturer> convertsManuf(List<SpManufacturer> list, String absoluteUrl) {
        return list.stream().map(m -> convert(m, absoluteUrl)).filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    private static String getUrlImage(CouchDbDocument doc, String head) {
        Map<String, Attachment> attachments = doc.getAttachments();
        if (attachments != null && !attachments.isEmpty()) {
            Map.Entry<String, Attachment> next = attachments.entrySet().iterator().next();
            return String.format(head + "%s/%s", doc.getId(), next.getKey());
        }
        return null;
    }

}
