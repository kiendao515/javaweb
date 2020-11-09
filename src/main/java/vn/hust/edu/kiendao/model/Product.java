package vn.hust.edu.kiendao.model;

import java.sql.Date;

public class Product {
    private int id;
    private String name;
    private double price;
    private String imageUrl;
    private String specification;
    private String intro;
    private boolean soldOut;
    private int guarantee;
    private int bought;
    private Date createDate;
    private int promotion;
    private boolean deleted;
    private int menuId;


    public Product() {
    }

    public Product(int id, String name, double price, String imageUrl, String specification, String intro, boolean soldOut,
                   int guarantee, int bought, Date createDate, int promotion, boolean deleted, int menuId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
        this.specification = specification;
        this.intro = intro;
        this.soldOut = soldOut;
        this.guarantee = guarantee;
        this.bought = bought;
        this.createDate = createDate;
        this.promotion = promotion;
        this.deleted = deleted;
        this.menuId = menuId;
    }

    public Product(String name, double price, String imageUrl, String specification, String intro, boolean soldOut,
                   int guarantee, int bought, Date createDate, int promotion, boolean deleted, int menuId) {
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
        this.specification = specification;
        this.intro = intro;
        this.soldOut = soldOut;
        this.guarantee = guarantee;
        this.bought = bought;
        this.createDate = createDate;
        this.promotion = promotion;
        this.deleted = deleted;
        this.menuId = menuId;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", imageUrl='" + imageUrl + '\'' +
                ", specification='" + specification + '\'' +
                ", intro='" + intro + '\'' +
                ", soldOut=" + soldOut +
                ", guarantee=" + guarantee +
                ", bought=" + bought +
                ", createDate=" + createDate +
                ", promotion=" + promotion +
                ", deleted=" + deleted +
                ", menuId=" + menuId +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public boolean isSoldOut() {
        return soldOut;
    }

    public void setSoldOut(boolean soldOut) {
        this.soldOut = soldOut;
    }

    public int getGuarantee() {
        return guarantee;
    }

    public void setGuarantee(int guarantee) {
        this.guarantee = guarantee;
    }

    public int getBought() {
        return bought;
    }

    public void setBought(int bought) {
        this.bought = bought;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public int getPromotion() {
        return promotion;
    }

    public void setPromotion(int promotion) {
        this.promotion = promotion;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }
}
