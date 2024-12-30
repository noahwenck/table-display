package com.edge.table.entity;

import jakarta.persistence.*;

/**
 * Entity holding the data of a single product type
 */
@Entity(name = "product")
public class Product {

    private int id;
    private String manufacturer;
    private String typeName;
    private int typeId;
    private String styleName;
    private String styleId;
    private Integer colorNumber;
    private String colorName;
    private String size; // Not sure if this is the best type - wanted length x width, but not sure how 12' Roll Goods would fit into that

    public Product(String manufacturer,
                   String typeName,
                   int typeId,
                   String styleName,
                   String colorName,
                   String size) {
        this.manufacturer = manufacturer;
        this.typeName = typeName;
        this.typeId = typeId;
        this.styleName = styleName;
        this.colorName = colorName;
        this.size = size;
    }

    public Product(String manufacturer,
                   String typeName,
                   int typeId,
                   String styleName,
                   String styleId,
                   Integer colorNumber,
                   String colorName,
                   String size) {
        this(manufacturer, typeName, typeId, styleName, colorName, size);
        this.styleId = styleId;
        this.colorNumber = colorNumber;
    }

    public Product() {
        // Empty for JPA
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRODUCT_ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "MFR", nullable = false)
    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    @Column(name = "TYPE_NAME", nullable = false)
    public String getTypeName() {
        return typeName;
    }

    @Column(name = "TYPE_NAME", nullable = false)
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public int getTypeId() {
        return typeId;
    }

    @Column(name  = "TYPE_ID", nullable = false)
    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getStyleName() {
        return styleName;
    }

    @Column(name = "STYLE_NAME", nullable = false)
    public void setStyleName(String styleName) {
        this.styleName = styleName;
    }

    public String getStyleId() {
        return styleId;
    }

    @Column(name = "STYLE_ID")
    public void setStyleId(String styleId) {
        this.styleId = styleId;
    }

    @Column(name = "COLOR_NUMBER")
    public Integer getColorNumber() {
        return colorNumber;
    }

    public void setColorNumber(Integer colorNumber) {
        this.colorNumber = colorNumber;
    }

    @Column(name = "COLOR_NAME")
    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }

    @Column(name = "SIZE")
    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", manufacturer='" + manufacturer + '\'' +
                ", typeName='" + typeName + '\'' +
                ", typeId=" + typeId +
                ", styleName='" + styleName + '\'' +
                ", styleId='" + styleId + '\'' +
                ", colorNumber=" + colorNumber +
                ", colorName='" + colorName + '\'' +
                ", size='" + size + '\'' +
                '}';
    }
}
