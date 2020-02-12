package com.hof.wovenyautoproductentry.repository;

import com.hof.wovenyautoproductentry.domain.product.Product;
import com.hof.wovenyautoproductentry.domain.product.ProductStatus;
import com.hof.wovenyautoproductentry.domain.product.ProductType;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findBySkuNumber(String skuNumber);

    @Query(value = "select p from product p where sku_number > :firstSkuNUmber and sku_number < :lastSkuNumber", nativeQuery = true)
    List<Product> findNotUploadedProductsForChairish(@Param("firstSkuNUmber") String firstSkuNUmber, @Param("lastSkuNumber") String lastSkuNumber);
    List<Product> findAllBySkuNumberGreaterThanAndSkuNumberIsLessThan(String firstSkuNUmber, String lastSkuNumber );

    @Query(value = "select p\n" +
            "from product p\n" +
            "where p.product_type = 'RUG' and p.quantity = 1\n" +
            "and p.status = 1 and p.stock_status <> 'Out Of Stock'\n" +
            "and p.is_uploaded_etsy = false order by p.id", nativeQuery = true)
    List<Product> getProductsForEtsyWithLimit(Pageable pageable);

    List<Product> findByProductTypeAndQuantityAndStatusAndStockStatusNotAndIsUploadedEtsy(Pageable pageable, ProductType productType, Integer quantity, ProductStatus status, String stockStatus, boolean uploadedEtsy);
}
