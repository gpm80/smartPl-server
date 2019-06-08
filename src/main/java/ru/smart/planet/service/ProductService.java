package ru.smart.planet.service;

import org.apache.commons.io.FileUtils;
import org.ektorp.AttachmentInputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.multipart.MultipartFile;
import ru.smart.planet.domain.SpProduct;
import ru.smart.planet.repository.SpProductRepository;
import ru.smart.planet.service.converter.Converter;
import ru.smart.planet.web.Category;
import ru.smart.planet.web.Product;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

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

    public Boolean save(Product product, MultipartFile multipartFile) {
        SpProduct save = repository.save(Converter.convert(product));
        if (multipartFile != null) {
            try (InputStream inputStream = multipartFile.getInputStream()) {
                File tempFile = File.createTempFile("file-", ".recipe", new File(System.getProperty("java.io.tmpdir")));
                FileUtils.copyInputStreamToFile(inputStream, tempFile);
                AttachmentInputStream attach = new AttachmentInputStream(UUID.randomUUID().toString(), new FileInputStream(tempFile), MimeTypeUtils.IMAGE_JPEG_VALUE);
                repository.attachFile(save.getId(), save.getRevision(), attach);
                return true;
            } catch (Exception e) {
                return false;
            }
        }
        return true;
    }

    public Boolean save(Product product) {
        return save(product, null);
    }


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
