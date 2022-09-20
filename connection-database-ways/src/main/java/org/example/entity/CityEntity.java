package org.example.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "city", schema = "public", catalog = "postgres")
public class CityEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "city_id", nullable = false)
    private int cityId;
    @Basic
    @Column(name = "city_name", nullable = false, length = 450)
    private String cityName;
    @Basic
    @Column(name = "country_id", nullable = false)
    private int countryId;
    @ManyToOne
    @JoinColumn(name = "country_id", referencedColumnName = "country_id", nullable = false, insertable = false, updatable = false)
    private CountryEntity countryByCountryId;
    @OneToMany(mappedBy = "cityByCityId")
    private Collection<StoreEntity> storesByCityId;

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CityEntity that = (CityEntity) o;
        return cityId == that.cityId && countryId == that.countryId && Objects.equals(cityName, that.cityName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cityId, cityName, countryId);
    }

    public CountryEntity getCountryByCountryId() {
        return countryByCountryId;
    }

    public void setCountryByCountryId(CountryEntity countryByCountryId) {
        this.countryByCountryId = countryByCountryId;
    }

    public Collection<StoreEntity> getStoresByCityId() {
        return storesByCityId;
    }

    public void setStoresByCityId(Collection<StoreEntity> storesByCityId) {
        this.storesByCityId = storesByCityId;
    }
}
