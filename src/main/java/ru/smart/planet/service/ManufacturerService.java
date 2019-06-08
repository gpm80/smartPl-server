package ru.smart.planet.service;

import org.springframework.beans.factory.annotation.Autowired;
import ru.smart.planet.repository.SpManufactrurerRepository;
import ru.smart.planet.service.converter.Converter;
import ru.smart.planet.web.Manufacturer;

/**
 * Created on 08.06.19.
 */
public class ManufacturerService {

    @Autowired
    protected SpManufactrurerRepository repository;

    public Manufacturer getOne(String uid) {
        return Converter.convert(repository.findOne(uid));
    }


}
