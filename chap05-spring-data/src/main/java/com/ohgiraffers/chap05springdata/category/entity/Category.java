package com.ohgiraffers.chap05springdata.category.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tbl_category")
public class Category {

    @Id
    @Column(name = "Category_code")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryCode;

    @Column(name = "category_name")
    private String categoryName;

    @Override
    public String toString() {
        return "Category{" +
                "categoryCode=" + categoryCode +
                ", categoryName='" + categoryName + '\'' +
                '}';
    }

    public Integer getCategoryCode() {
        return this.categoryCode;
    }

    public void setCategoryCode(Integer categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Category() {
    }

    public Category(String categoryName, int categoryCode) {
        this.categoryName = categoryName;
        this.categoryCode = categoryCode;
    }
}
