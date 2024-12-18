package com.ohgiraffers.section02;

import jakarta.persistence.*;

@Entity(name = "section02_meunu") /*이름*/
@Table(name = "tbl_menu") /*어느테이블이랑매핑*/
public class Menu {


    @Id // Pk
    @Column(name = "menu_code") // 실제 테이블 컬럼명
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본키 값을 db에서 설정하도록
    private int menuCode;

    @Column(name = "menu_name")
    private String menuName;

    @Column(name = "menu_price")
    private String menuPrice;

    @Column(name = "category_code")
    private String categoryCode;

    @Column(name = "orderable_status")
    private String orderableStatus;

    @Override
    public String toString() {
        return "Menu{" +
                "menuCode=" + menuCode +
                ", menuName='" + menuName + '\'' +
                ", menuPrice='" + menuPrice + '\'' +
                ", categoryCode='" + categoryCode + '\'' +
                ", orderableStatus='" + orderableStatus + '\'' +
                '}';
    }

    public Menu() {
    }

    public Menu(int menuCode, String menuName, String menuPrice, String categoryCode, String orderableStatus) {
        this.menuCode = menuCode;
        this.menuName = menuName;
        this.menuPrice = menuPrice;
        this.categoryCode = categoryCode;
        this.orderableStatus = orderableStatus;
    }

    public int getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(int menuCode) {
        this.menuCode = menuCode;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuPrice() {
        return menuPrice;
    }

    public void setMenuPrice(String menuPrice) {
        this.menuPrice = menuPrice;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getOrderableStatus() {
        return orderableStatus;
    }

    public void setOrderableStatus(String orderableStatus) {
        this.orderableStatus = orderableStatus;
    }
}
