package com.edge.table.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query("SELECT p FROM product p WHERE p.manufacturer = :manufacturer")
    List<Product> findByManufacturer(String manufacturer);

    @Query("SELECT p FROM product p WHERE p.typeName = :typeName")
    List<Product> findByTypeName(String typeName);

    @Query("SELECT p FROM product p WHERE p.typeId = :typeId")
    List<Product> findByTypeId(int typeId);

    @Query("SELECT p FROM product p WHERE p.styleName = :styleName")
    List<Product> findByStyleName(String styleName);

    @Query("SELECT p FROM product p WHERE p.styleId = :styleId")
    List<Product> findByStyleId(String styleId);

    @Query("SELECT p FROM product p WHERE p.colorNumber = :colorNumber")
    List<Product> findByColorNumber(int colorNumber);

    @Query("SELECT p FROM product p WHERE p.colorName = :colorName")
    List<Product> findByColorName(String colorName);

    @Query("SELECT p FROM product p WHERE p.size = :size")
    List<Product> findBySize(String size);
}
