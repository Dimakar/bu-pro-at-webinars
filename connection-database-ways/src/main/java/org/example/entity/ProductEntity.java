package org.example.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "product", schema = "public", catalog = "postgres")
public class ProductEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "product_id", nullable = false)
    private int productId;
    @Basic
    @Column(name = "name", nullable = false, length = 250)
    private String name;
    @OneToMany(mappedBy = "productByProductId")
    private Collection<SaleEntity> salesByProductId;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductEntity that = (ProductEntity) o;
        return productId == that.productId && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, name);
    }

    public Collection<SaleEntity> getSalesByProductId() {
        return salesByProductId;
    }

    public void setSalesByProductId(Collection<SaleEntity> salesByProductId) {
        this.salesByProductId = salesByProductId;
    }
}
