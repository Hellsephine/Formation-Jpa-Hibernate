package hibernate;

import java.util.Date;
import jakarta.persistence.*;

@Entity
@Table(name="country")
public class Country {
    @Id
    @Column(name="country_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer countryId;

    @Column(name="country")
    private String country;

    @Column(name="last_update")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastupDate;

    

    //#region GET & SET
    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Date getLastupDate() {
        return lastupDate;
    }

    public void setLastupDate(Date lastupDate) {
        this.lastupDate = lastupDate;
    }
    //#endregion
}
