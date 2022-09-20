package org.example.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "order_status", schema = "public", catalog = "postgres")
public class OrderStatusEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "order_status_id", nullable = false, length = 200)
    private String orderStatusId;
    @Basic
    @Column(name = "update_at", nullable = true)
    private Timestamp updateAt;
    @Basic
    @Column(name = "sale_id", nullable = false, length = 200)
    private String saleId;
    @Basic
    @Column(name = "status_name_id", nullable = false)
    private int statusNameId;
    @ManyToOne
    @JoinColumn(name = "sale_id", referencedColumnName = "sale_id", nullable = false, insertable = false, updatable = false)
    private SaleEntity saleBySaleId;
    @ManyToOne
    @JoinColumn(name = "status_name_id", referencedColumnName = "status_name_id", nullable = false, insertable = false, updatable = false)
    private StatusNameEntity statusNameByStatusNameId;

    public String getOrderStatusId() {
        return orderStatusId;
    }

    public void setOrderStatusId(String orderStatusId) {
        this.orderStatusId = orderStatusId;
    }

    public Timestamp getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Timestamp updateAt) {
        this.updateAt = updateAt;
    }

    public String getSaleId() {
        return saleId;
    }

    public void setSaleId(String saleId) {
        this.saleId = saleId;
    }

    public int getStatusNameId() {
        return statusNameId;
    }

    public void setStatusNameId(int statusNameId) {
        this.statusNameId = statusNameId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderStatusEntity that = (OrderStatusEntity) o;
        return statusNameId == that.statusNameId && Objects.equals(orderStatusId, that.orderStatusId) && Objects.equals(updateAt, that.updateAt) && Objects.equals(saleId, that.saleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderStatusId, updateAt, saleId, statusNameId);
    }

    public SaleEntity getSaleBySaleId() {
        return saleBySaleId;
    }

    public void setSaleBySaleId(SaleEntity saleBySaleId) {
        this.saleBySaleId = saleBySaleId;
    }

    public StatusNameEntity getStatusNameByStatusNameId() {
        return statusNameByStatusNameId;
    }

    public void setStatusNameByStatusNameId(StatusNameEntity statusNameByStatusNameId) {
        this.statusNameByStatusNameId = statusNameByStatusNameId;
    }
}
