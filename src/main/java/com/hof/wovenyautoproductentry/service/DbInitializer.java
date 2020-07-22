package com.hof.wovenyautoproductentry.service;

import com.hof.wovenyautoproductentry.constants.AmaraCsvHeaderConstants;
import com.hof.wovenyautoproductentry.domain.product.AmaraProduct;
import com.hof.wovenyautoproductentry.domain.product.Product;
import com.hof.wovenyautoproductentry.domain.product.ProductType;
import com.hof.wovenyautoproductentry.repository.AmaraProductRepository;
import com.hof.wovenyautoproductentry.repository.ProductRepository;
import com.hof.wovenyautoproductentry.util.CSVReader;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Component
public class DbInitializer {
    private static final String CSV_FILE_PATH = "export_2020-06-21.csv";
    private static final String AMARA_CSV_FILE_PATH = "AmaraChairishCsv.csv";
    private static final Character CSV_SPLIT_BY = ';';

    private final CSVReader csvReader;
    private final ProductMapper productMapper;
    private final AmaraProductMapper amaraProductMapper;
    private final ProductRepository productRepository;
    private final AmaraProductRepository amaraProductRepository;
    private final ProductValidator productValidator;

    public DbInitializer(CSVReader csvReader,
                         ProductMapper mapper,
                         AmaraProductMapper amaraProductMapper,
                         ProductRepository productRepository,
                         AmaraProductRepository amaraProductRepository,
                         ProductValidator productValidator) {
        this.csvReader = csvReader;
        this.productMapper = mapper;
        this.amaraProductMapper = amaraProductMapper;
        this.productRepository = productRepository;
        this.amaraProductRepository = amaraProductRepository;
        this.productValidator = productValidator;
    }

   // @PostConstruct
    public void initialize() {
        String filePath = ClassLoader.getSystemResource(CSV_FILE_PATH).getPath();
        try {
            Iterable<CSVRecord> records = csvReader.read(filePath, CSV_SPLIT_BY);
            records.forEach(record -> {
                Product product = productMapper.csvRecordToProductEntity(record);
                //if (Objects.nonNull(product.getProductType()) && product.getProductType().equals(ProductType.RUG))
                    //System.out.println(product);
                productRepository.save(product);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //@PostConstruct
    public void initializeAmaraProducts() {
        String filePath = ClassLoader.getSystemResource(AMARA_CSV_FILE_PATH).getPath();
        try {
            Iterable<CSVRecord> records = csvReader.read(filePath, CSV_SPLIT_BY);
            records.forEach(record -> {
                if (!record.get(AmaraCsvHeaderConstants.SKU_NUMBER).isEmpty()) {
                    AmaraProduct amaraProduct = amaraProductMapper.csvRecordToProductEntity(record);
                    System.out.println(amaraProduct.getSkuNumber());
                    amaraProductRepository.save(amaraProduct);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //@PostConstruct
    void generateMetaKeyword() {
        List<Product> products = productRepository.findByProductType(ProductType.PILLOW);
        products.forEach(product -> {
            productValidator.generateMetadataKeyword(product);
            System.out.println(product.getSkuNumber() + " --> " + product.getMetaKeyword());
            //productRepository.save(product);
        });
    }
}
