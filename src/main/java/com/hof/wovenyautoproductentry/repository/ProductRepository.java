package com.hof.wovenyautoproductentry.repository;

import com.hof.wovenyautoproductentry.domain.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "select p from product p where sku_number > :firstSkuNUmber and sku_number < :lastSkuNumber", nativeQuery = true)
    List<Product> findNotUploadedProductsForChairish(@Param("firstSkuNUmber") String firstSkuNUmber, @Param("lastSkuNumber") String lastSkuNumber);
    List<Product> findAllBySkuNumberGreaterThanAndSkuNumberIsLessThan(String firstSkuNUmber, String lastSkuNumber );
}
