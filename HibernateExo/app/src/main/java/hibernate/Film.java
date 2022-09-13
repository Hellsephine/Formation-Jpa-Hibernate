
package hibernate;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;

@Entity
@Table(name="film")
@NamedQuery(name = "Film.moreThanTwoHour", query = "SELECT f FROM Film f WHERE f.length > :length")
@NamedQuery(name = "Film.between50And90", query = "SELECT f FROM Film f WHERE f.length BETWEEN 50 and 90")
public class Film {

    @Id
    @Column(name="film_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer filmId;

    @OneToMany(mappedBy = "film")
    private Set<FilmActor> factors = new HashSet<FilmActor>();

    public Set<FilmActor> getFilmActors(){ return factors;}//GETTER
    public void addFilmActors(FilmActor fa){ factors.add(fa);}//ADDER


    @OneToMany(mappedBy = "film")
    private Set<FilmCategory> fcat = new HashSet<FilmCategory>();

    public Set<FilmCategory> getFilmCategory(){ return fcat;}
    public void addFilmCategory(FilmCategory fc){ fcat.add(fc);}
   
    /* 
    @ManyToMany
    @JoinTable(
        name = "film_category",
        joinColumns = @JoinColumn(name = "film_id"),
        inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories;
    */

    /* 
    @ManyToMany
    @JoinTable(
        name = "film_actor",
        joinColumns = @JoinColumn(name = "film_id"),
        inverseJoinColumns = @JoinColumn(name = "actor_id"))
    private Set<Actor> actors;
    */

    @Column(name="title")
    private String title;

    @Column(name="description")
    private String description;

    @Column(name="release_year")
    private Integer release_year;

    @ManyToOne
    @JoinColumn(name="language_id")
    private Language languageId;

    @ManyToOne
    @JoinColumn(name="original_language_id", nullable = true) 
    private Language original_languageId;

    @Column(name="rental_duration")
    private Float rentalDuration;

    @Column(name="rental_rate")
    private Float rentalRate;

    @Column(name="length")
    private Integer length;

    @Column(name="replacement_cost")
    private Float replacementCost;

    @Column(name="rating")
    private String rating;

    @Column(name="special_features")
    private String special_features;

    @Column(name="last_update")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastupDate;

    //#region GET & SET

    public Integer getFilmId() {
        return filmId;
    }

    public void setFilmId(Integer filmId) {
        this.filmId = filmId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getRelease_year() {
        return release_year;
    }

    public void setRelease_year(Integer release_year) {
        this.release_year = release_year;
    }

    public Language getLanguageId() {
        return languageId;
    }

    public void setLanguageId(Language languageId) {
        this.languageId = languageId;
    }

    public Language getOriginal_languageId() {
        return original_languageId;
    }

    public void setOriginal_languageId(Language original_languageId) {
        this.original_languageId = original_languageId;
    }

    public Float getRentalDuration() {
        return rentalDuration;
    }

    public void setRentalDuration(Float rentalDuration) {
        this.rentalDuration = rentalDuration;
    }

    public Float getRentalRate() {
        return rentalRate;
    }

    public void setRentalRate(Float rentalRate) {
        this.rentalRate = rentalRate;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Float getReplacementCost() {
        return replacementCost;
    }

    public void setReplacementCost(Float replacementCost) {
        this.replacementCost = replacementCost;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getSpecial_features() {
        return special_features;
    }

    public void setSpecial_features(String special_features) {
        this.special_features = special_features;
    }

    public Date getLastupDate() {
        return lastupDate;
    }

    public void setLastupDate(Date lastupDate) {
        this.lastupDate = lastupDate;
    }

    /* 
    public Set<Category> getCategory() {
        return categories;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }
    */

    /* 
    public Set<Actor> getActors() {
        return actors;
    }

    public void setActors(Set<Actor> actors) {
        this.actors = actors;
    }
    */
    

    public Set<FilmActor> getFactors() {
        return factors;
    }
    public void setFactors(Set<FilmActor> factors) {
        this.factors = factors;
    }
    public Set<FilmCategory> getFcat() {
        return fcat;
    }
    public void setFcat(Set<FilmCategory> fcat) {
        this.fcat = fcat;
    }

    

    
   //#endregion
  
}