package ru.smart.planet.service.converter;

import ru.smart.planet.domain.SpManufacturer;
import ru.smart.planet.web.Manufacturer;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created on 08.06.19.
 */
public class Converter {

    public static Manufacturer convert(SpManufacturer val) {
        if (val == null) {
            return null;
        }
        Manufacturer m = new Manufacturer();
        m.setTitle(val.getTitle());
        m.setDescription(val.getDescription());
        m.setUid(val.getId());
        m.setLat(val.getLat());
        m.setLng(val.getLng());
        return m;
    }

    public static List<Manufacturer> convert(List<SpManufacturer> list) {
        return list.stream().map(Converter::convert).filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}
