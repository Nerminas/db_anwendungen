package at.fhj.swd.postgres;

import javax.persistence.*;

@Entity
@Table(name = "sight", schema = "ue03", catalog = "swd_ws18_13")
public class SightEntity {
    private int id;
    private String name;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 30)
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

        SightEntity that = (SightEntity) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    private CityEntity sightToCity;

    @OneToOne
    public CityEntity getSightToCity() {
        return sightToCity;
    }

    public void setSightToCity(CityEntity sightToCity) {
        this.sightToCity = sightToCity;
    }
}
