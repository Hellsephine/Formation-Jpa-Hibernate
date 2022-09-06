package hibernate;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class FilmActorPK implements Serializable {

    @Column(name="film_id")
    private Integer filmId;

    @Column(name="actor_id")
    private Integer actorId;

    //#region GET & SET
    public Integer getFilmId() {
        return filmId;
    }

    public void setFilmId(Integer filmId) {
        this.filmId = filmId;
    }

    public Integer getActorId() {
        return actorId;
    }

    public void setActorId(Integer actorId) {
        this.actorId = actorId;
    }
    //#endregion

}
