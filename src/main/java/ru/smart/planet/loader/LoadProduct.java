package ru.smart.planet.loader;

import org.apache.commons.io.FileUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;
import ru.smart.planet.web.BioStatus;
import ru.smart.planet.web.Category;
import ru.smart.planet.web.Manufacturer;
import ru.smart.planet.web.Product;

import java.io.File;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Petr Gusarov on 29.03.19.
 */
public class LoadProduct {

    private static final String[] SOURCE = new String[]{
            "http://localhost:8085", "/home/mihalich/DigiStream/SP-image/smart/"
    };
//    private static final String[] SOURCE = new String[]{
//            "http://93.171.217.252/planet-server", "/home/mihalich/DigiStream/SP-image/smart/"
//    };
//    private static final String[] SOURCE = new String[]{
//        "http://93.171.217.252/shopping-server/rest", "/home/gpm/Priv/java/android/ЧеКупил/RecipesProduction/"
//    };
//    private static final String[] SOURCE = new String[]{
//        "http://93.171.217.252/shopping-test/rest", "/home/gpm/Priv/java/android/ЧеКупил/RecipesTest/"
//    };


    public static void main(String[] args) throws InterruptedException {

        LoadProduct loaded = new LoadProduct();
//        loaded.generate().forEach(loaded::sendToServer);
//        loaded.sendToServer();
        loaded.load(SOURCE[1]);
//      TimeUnit.SECONDS.sleep(1);
    }

//    private List<Product> generate() {
//        ArrayList<Product> list = new ArrayList<>();
//
//        Manufacturer manufacturer = new Manufacturer();
//        manufacturer.setTitle("Зеленый урожай");
//        manufacturer.setUid(UUID.randomUUID().toString());
//
//        Product p = new Product();
//        p.setTitle("Огурцы");
//        p.setManufacturer(manufacturer);
//        p.setDescription("Вкусные и пупырчатые");
//        p.setFullDescription("Огурчики с грядки");
//        p.setGroup("Огурцы");
//        p.setCategory(Category.VEGETABLE);
//        p.setBioStatus(BioStatus.GREEN);
//        p.setSrcImage("/home/mihalich/DigiStream/SP-image/огурцы.jpg");
//        list.add(p);
//
//        p = new Product();
//        p.setTitle("Помидоры");
//        p.setManufacturer(manufacturer);
//        p.setDescription("Розовые");
//        p.setFullDescription("С навозиком");
//        p.setGroup("Помидоры");
//        p.setCategory(Category.VEGETABLE);
//        p.setBioStatus(BioStatus.YELLOW);
//        p.setSrcImage("/home/mihalich/DigiStream/SP-image/помидоры.jpg");
//        list.add(p);
//        return list;
//    }

    /**
     * Загрузчик всех папок в корне
     *
     * @param parentDir
     */
    private void load(String parentDir) {
        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setTitle("Зеленый урожай");
        manufacturer.setLat("-27.47093");
        manufacturer.setLng("153.0235");
        manufacturer.setUid(UUID.randomUUID().toString());
        File dir = new File(parentDir);
        File[] files = dir.listFiles((dir1, name) -> !name.startsWith("_"));
        assert files != null;
        Stream.of(files).filter(File::isDirectory)
                .forEach(v -> recipeProcess(v, manufacturer));
    }

    /**
     * '
     * Обработка
     *
     * @param dir
     * @return
     */
    private boolean recipeProcess(File dir, Manufacturer manufacturer) {
        File[] files = dir.listFiles();
        assert files != null;
        List<File> images = Stream.of(files).filter(file -> file.getName().toLowerCase().endsWith("jpg"))
                .collect(Collectors.toList());
        File productFile = new File(dir, "info.txt");
        if (!productFile.exists()) {
            throw new RuntimeException(productFile.getAbsolutePath() + " not found");
        }
        try {
            String[] blocks = FileUtils
                    .readFileToString(productFile, "utf-8")
                    .split("---");
            if (blocks.length < 6) {
                throw new IllegalArgumentException((productFile.getAbsolutePath() + " unsupported format"));
            }
            Product product = new Product();
            product.setManufacturer(manufacturer);
            product.setTitle(blocks[0].replaceAll("\\n", " ").trim());
            product.setGroup(blocks[1].replaceAll("\\n", " ").trim());
            product.setCategory(Category.valueOf(blocks[2].replaceAll("\\n", " ").trim()));
            product.setBioStatus(BioStatus.valueOf(blocks[3].replaceAll("\\n", " ").trim()));
            product.setDescription(blocks[4].replaceAll("\\n", " ").trim());
            product.setFullDescription(blocks[5].replaceAll("\\n", " ").trim());
            if (!images.isEmpty()) {
                product.setSrcImage(images.get(0).getAbsolutePath());
            }
            sendToServer(product);
            return dir.renameTo(new File(dir.getParent(), "_" + dir.getName()));
        } catch (Exception e) {
            throw new RuntimeException(dir.toString(), e);
        }
    }


    private void sendToServer(Product product) {
        File images = product.getSrcImage() != null ? new File(product.getSrcImage()) : null;
        if (images != null) {
            LinkedMultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
            map.add("product", product);
            map.add("file", new FileSystemResource(images));
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            HttpEntity<LinkedMultiValueMap<String, Object>> httpEntity = new HttpEntity<>(map, headers);
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<Boolean> response = restTemplate.exchange(SOURCE[0] + "/product/saveFile", HttpMethod.POST, httpEntity, Boolean.class);
            if (response.getStatusCode().is2xxSuccessful() && response.getBody()) {
                System.out.println(product.getTitle() + " - success");
            } else {
                System.err.println("ERROR - " + product.getTitle());
            }
        } else {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<Boolean> response = restTemplate.exchange(SOURCE[0] + "/product/save", HttpMethod.POST, new HttpEntity<>(product, headers), Boolean.class);
            if (response.getStatusCode().is2xxSuccessful() && response.getBody()) {
                System.out.println(product.getTitle() + " - success");
            } else {
                System.err.println("ERROR - " + product.getTitle());
            }
        }
    }

}
