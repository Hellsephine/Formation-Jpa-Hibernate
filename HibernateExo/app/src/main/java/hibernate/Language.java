package hibernate;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;

@Entity
@Table(name="language")
public class Language {
    @Id
    @Column(name="language_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer languageId;

    @Column(name="name")
    private String name;


    @Column(name="last_update")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastupDate;

    @OneToMany(mappedBy ="languageId")
    private Set<Film> filmsParLangue = new HashSet<Film>();

    public void addFilmParLangue(Film f) {filmsParLangue.add(f);}
    public Set<Film> getFilmParLangue() { return filmsParLangue; }

    //#region
    public Integer getLanguageId() {
        return languageId;
    }


    public void setLanguageId(Integer languageId) {
        this.languageId = languageId;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public Date getLastupDate() {
        return lastupDate;
    }


    public void setLastupDate(Date lastupDate) {
        this.lastupDate = lastupDate;
    }
    public Set<Film> getFilmsParLangue() {
        return filmsParLangue;
    }
    public void setFilmsParLangue(Set<Film> filmsParLangue) {
        this.filmsParLangue = filmsParLangue;
    }
    //#endregion

}
