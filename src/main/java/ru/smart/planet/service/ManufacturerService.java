package ru.smart.planet.service;

import org.apache.commons.io.FileUtils;
import org.ektorp.AttachmentInputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.multipart.MultipartFile;
import ru.smart.planet.domain.SpProduct;
import ru.smart.planet.repository.SpManufacturerRepository;
import ru.smart.planet.service.converter.Converter;
import ru.smart.planet.web.Manufacturer;
import ru.smart.planet.web.Product;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

/**
 * Created on 08.06.19.
 */
@Service
public class ManufacturerService {

    @Autowired
    protected SpManufacturerRepository repository;
    @Value("${absolute.url.couch.attachment}")
    protected String absoluteUrl;


    public Manufacturer getOne(String uid) {
        return Converter.convert(repository.findOne(uid), absoluteUrl);
    }

    public List<Manufacturer> getAll() {
        return Converter.convertsManuf(repository.getAll(), absoluteUrl);
    }

//    public Boolean save(Manufacturer product, MultipartFile multipartFile) {
//        SpProduct save = repository.save(Converter.convert(product));
//        if (multipartFile != null) {
//            try (InputStream inputStream = multipartFile.getInputStream()) {
//                File tempFile = File.createTempFile("file-", ".product", new File(System.getProperty("java.io.tmpdir")));
//                FileUtils.copyInputStreamToFile(inputStream, tempFile);
//                AttachmentInputStream attach = new AttachmentInputStream(UUID.randomUUID().toString(), new FileInputStream(tempFile), MimeTypeUtils.IMAGE_JPEG_VALUE);
//                repository.attachFile(save.getId(), save.getRevision(), attach);
//                return true;
//            } catch (Exception e) {
//                return false;
//            }
//        }
//        return true;
//    }
}
