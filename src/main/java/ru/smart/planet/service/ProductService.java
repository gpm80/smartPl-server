package ru.smart.planet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.smart.planet.repository.SpProductRepository;
import ru.smart.planet.service.converter.Converter;
import ru.smart.planet.web.Category;
import ru.smart.planet.web.Product;

import java.util.List;

/**
 * Сервис продукции
 * Created on 08.06.19.
 */
@Service
public class ProductService {

    @Autowired
    protected SpProductRepository repository;
    @Value("${absolute.url.couch.attachment}")
    protected String absoluteUrl;

    public Product getOne(String uid) {
        return Converter.convert(repository.findOne(uid), absoluteUrl);
    }

    public List<Product> getAll() {
        return Converter.convertsProd(repository.getAll(), absoluteUrl);
    }

    public List<Product> getByCategory(String category) {
        try {
            Category cat = Category.valueOf(category);
            return Converter.convertsProd(repository.getByCategory(cat), absoluteUrl);
        } catch (Exception e) {

        }
        return getAll();
    }

    public List<Product> getByGroup(String group) {
        return Converter.convertsProd(repository.getByGroup(group), absoluteUrl);
    }
}
