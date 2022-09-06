package hibernate;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class FilmCategoryPK implements Serializable {

    @Column(name="film_id")
    private Integer filmId;

    @Column(name="category_id")
    private Integer categoryId;

    //#region GET & SET
    public Integer getFilmId() {
        return filmId;
    }

    public void setFilmId(Integer filmId) {
        this.filmId = filmId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }
    //#endregion
      
}


