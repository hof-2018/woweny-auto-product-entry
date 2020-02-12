package com.hof.wovenyautoproductentry.service;

import com.hof.wovenyautoproductentry.domain.product.Product;
import com.hof.wovenyautoproductentry.repository.ProductRepository;
import com.hof.wovenyautoproductentry.util.CSVReader;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;

@Component
public class DbInitializer {
    private static final Logger logger = LoggerFactory.getLogger(DbInitializer.class);

    private static final String CSV_FILE_PATH = "export_2020-02-09.csv";
    private static final String DESCRIPTIONS_FILE_PATH = "descriptions.csv";
    private static final Character CSV_SPLIT_BY = ';';

    private final CSVReader csvReader;
    private final ProductMapper productMapper;
    private final ProductRepository productRepository;

    public DbInitializer(CSVReader csvReader, ProductMapper mapper, ProductRepository productRepository) {
        this.csvReader = csvReader;
        this.productMapper = mapper;
        this.productRepository = productRepository;
    }

    //@PostConstruct
    void initialize() {
        String filePath = ClassLoader.getSystemResource(CSV_FILE_PATH).getPath();
        try {
            Iterable<CSVRecord> records = csvReader.read(filePath, CSV_SPLIT_BY);
            records.forEach(record -> {
                Product product = productMapper.csvRecordToProductEntity(record);
                productRepository.save(product);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //@PostConstruct
    public void descriptionInitialize() {
        String filePath = ClassLoader.getSystemResource(DESCRIPTIONS_FILE_PATH).getPath();
        try {
            Iterable<CSVRecord> records = csvReader.read(filePath, CSV_SPLIT_BY);
            records.forEach(record -> {
                Optional<Product> productOptional = productRepository.findBySkuNumber(record.get("model"));
                if (productOptional.isPresent()) {
                    Product product = productOptional.get();
                    product.setDescription(record.get("description"));
                    productRepository.save(product);
                } else {
                    logger.info("Item: {} not found in db", record.get("model"));
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
