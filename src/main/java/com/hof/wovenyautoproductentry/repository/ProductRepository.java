package com.hof.wovenyautoproductentry.repository;

import com.hof.wovenyautoproductentry.domain.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
