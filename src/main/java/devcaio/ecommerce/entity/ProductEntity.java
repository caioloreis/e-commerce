package devcaio.ecommerce.entity;


import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table (name = "tb_prducts")
public class ProductEntity {

    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long produtcId;

    @Column (name = "product_name")
    private String name;

    @Column (name = "price")
    private BigDecimal price;

    public ProductEntity() {
    }

    public Long getProdutcId() {
        return produtcId;
    }

    public void setProdutcId(Long produtcId) {
        this.produtcId = produtcId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
