package ru.smart.planet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
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

    @RequestMapping(path = "/save", method = RequestMethod.POST)
    public Boolean add(@RequestBody Product product) {
        return productService.save(product);
    }

    @RequestMapping(value = "/saveFile", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Boolean addRecipes(@RequestPart("product") Product product, @RequestPart("file") MultipartFile multipartFile) {
        return productService.save(product, multipartFile);
    }

}
