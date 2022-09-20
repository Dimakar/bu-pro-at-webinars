package org.example.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "users", schema = "public", catalog = "postgres")
public class UsersEntity {

    @Id
    @Column(name = "user_id", nullable = false)
    private int userId;
    @Basic
    @Column(name = "name", nullable = false, length = 250)
    private String name;
    @OneToMany(mappedBy = "usersByUserId")
    private Collection<SaleEntity> salesByUserId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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
        UsersEntity that = (UsersEntity) o;
        return userId == that.userId && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, name);
    }

    public Collection<SaleEntity> getSalesByUserId() {
        return salesByUserId;
    }

    public void setSalesByUserId(Collection<SaleEntity> salesByUserId) {
        this.salesByUserId = salesByUserId;
    }
}
