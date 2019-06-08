package ru.smart.planet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.smart.planet.service.ManufacturerService;
import ru.smart.planet.web.Manufacturer;

import java.util.List;

/**
 * Created on 08.06.19.
 */
@RestController
@RequestMapping("/manufacturer")
public class ManufacturerController {


    @Autowired
    protected ManufacturerService manufacturerService;

    @RequestMapping(path = "/get/{uid}")
    public Manufacturer get(@PathVariable String uid) {
        return manufacturerService.getOne(uid);
    }

    @RequestMapping(path = "/all")
    public List<Manufacturer> getAll() {
        return manufacturerService.getAll();
    }
}
