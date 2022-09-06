package hibernate;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;

@Entity
@Table(name="actor")
public class Actor {
    @Id
    @Column(name="actor_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer actorId;

    @OneToMany(mappedBy = "actor")
    private Set<FilmActor> factors = new HashSet<FilmActor>();

    public Set<FilmActor> getFilmActors(){ return factors;}
    public void addFilmActors(FilmActor fa){ factors.add(fa);}

    //@ManyToMany(mappedBy = "actors")
    //private Set<Film> films;

    @Column(name="first_name")
    private String first_name;

    @Column(name="last_name")
    private String last_name;

    @Column(name="last_update")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastupDate;

   

    //#region GET & SET

    public Integer getActorId() {
        return actorId;
    }

    public void setActorId(Integer actorId) {
        this.actorId = actorId;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public Date getLastupDate() {
        return lastupDate;
    }

    public void setLastupDate(Date lastupDate) {
        this.lastupDate = lastupDate;
    }

    /* 
    public Set<Film> getFilms() {
        return films;
    }

    public void setFilms(Set<Film> films) {
        this.films = films;
    }
    */

    //#endregion
  
}
    