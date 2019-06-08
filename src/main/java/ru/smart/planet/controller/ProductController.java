package ru.smart.planet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.smart.planet.service.ProductService;
import ru.smart.planet.web.Product;

import java.util.List;

/**
 * Created on 08.06.19.
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    protected ProductService productService;

    @RequestMapping(path = "/get/{uid}")
    public Product get(@PathVariable String uid) {
        return productService.getOne(uid);
    }

    @RequestMapping(path = "/all")
    public List<Product> getAll() {
        return productService.getAll();
    }

    @RequestMapping(path = "/cat/{category}")
    public List<Product> getByCategory(@PathVariable String category) {
        return productService.getByCategory(category);
    }

    @RequestMapping(path = "/group/{group}")
    public List<Product> getByGroup(@PathVariable String group) {
        return productService.getByGroup(group);
    }

}
