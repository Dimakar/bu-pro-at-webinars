package org.example.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "store", schema = "public", catalog = "postgres")
public class StoreEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "store_id", nullable = false)
    private int storeId;
    @Basic
    @Column(name = "name", nullable = false, length = 250)
    private String name;
    @Basic
    @Column(name = "city_id", nullable = false)
    private int cityId;
    @OneToMany(mappedBy = "storeByStoreId")
    private Collection<SaleEntity> salesByStoreId;
    @ManyToOne
    @JoinColumn(name = "city_id", referencedColumnName = "city_id", nullable = false, insertable = false, updatable = false)
    private CityEntity cityByCityId;

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StoreEntity that = (StoreEntity) o;
        return storeId == that.storeId && cityId == that.cityId && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(storeId, name, cityId);
    }

    public Collection<SaleEntity> getSalesByStoreId() {
        return salesByStoreId;
    }

    public void setSalesByStoreId(Collection<SaleEntity> salesByStoreId) {
        this.salesByStoreId = salesByStoreId;
    }

    public CityEntity getCityByCityId() {
        return cityByCityId;
    }

    public void setCityByCityId(CityEntity cityByCityId) {
        this.cityByCityId = cityByCityId;
    }
}
