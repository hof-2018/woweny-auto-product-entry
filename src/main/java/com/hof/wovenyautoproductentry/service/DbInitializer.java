package com.hof.wovenyautoproductentry.service;

import com.hof.wovenyautoproductentry.domain.product.Product;
import com.hof.wovenyautoproductentry.repository.ProductRepository;
import com.hof.wovenyautoproductentry.util.CSVReader;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Component
public class DbInitializer {
    private static final String CSV_FILE_PATH = "export_2020-02-08.csv";
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
    void initialize(){
        String filePath = ClassLoader.getSystemResource(CSV_FILE_PATH).getPath();
        try {
            Iterable<CSVRecord> records = csvReader.read(filePath, CSV_SPLIT_BY);
            records.forEach( record -> {
                Product product = productMapper.csvRecordToProductEntity(record);
                productRepository.save(product);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
