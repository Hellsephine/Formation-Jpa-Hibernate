package hibernate;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;

@Entity
@Table(name="category")
public class Category {

    @Id
    @Column(name="category_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId;

    @OneToMany(mappedBy = "category")
    private Set<FilmCategory> fcat = new HashSet<FilmCategory>();

    public Set<FilmCategory> getFilmCategory(){ return fcat;}
    public void addFilmCategory(FilmCategory fc){ fcat.add(fc);}

    @Column(name="name")
    private String categoryName;

    @Column(name="last_update")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastupDate;

    /* 
    @ManyToMany(mappedBy = "categories")
    private Set<Film> films;
    */

    //#region GET & SET
    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Date getLastupDate() {
        return lastupDate;
    }

    public void setLastupDate(Date lastupDate) {
        this.lastupDate = lastupDate;
    }
    public Set<FilmCategory> getFcat() {
        return fcat;
    }
    public void setFcat(Set<FilmCategory> fcat) {
        this.fcat = fcat;
    }

    /* 
    public Set<Film> getFilms() {
        return films;
    }
    */
    
    //#endregion

}

