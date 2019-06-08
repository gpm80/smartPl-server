package ru.smart.planet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.smart.planet.repository.SpManufactrurerRepository;
import ru.smart.planet.service.converter.Converter;
import ru.smart.planet.web.Manufacturer;

import java.util.List;

/**
 * Created on 08.06.19.
 */
@Service
public class ManufacturerService {

    @Autowired
    protected SpManufactrurerRepository repository;
    @Value("${absolute.url.couch.attachment}")
    protected String absoluteUrl;


    public Manufacturer getOne(String uid) {
        return Converter.convert(repository.findOne(uid), absoluteUrl);
    }

    public List<Manufacturer> getAll() {
        return Converter.convertsManuf(repository.getAll(), absoluteUrl);
    }

}
