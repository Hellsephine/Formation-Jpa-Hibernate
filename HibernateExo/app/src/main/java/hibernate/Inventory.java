package hibernate;

import java.util.Date;

import jakarta.persistence.*;


@Entity
@Table(name="inventory")
@NamedQuery(name ="Inventory.content", query = "SELECT i FROM Inventory i")
public class Inventory {

    @Id
    @Column(name="inventory_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer inventoryId;
    
    @ManyToOne
    @JoinColumn(name="film_id")
    private Film filmId;

    //A utiliser quand on aura besoin de Store
    //@ManyToOne
    //@JoinColumn(name="store_id")
    //private Integer storeId;

    @Column(name = "store_id")
    private Integer storeId;

    @Column(name="last_update")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdate;

    public Integer getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(Integer inventoryId) {
        this.inventoryId = inventoryId;
    }

    public Film getFilmId() {
        return filmId;
    }

    public void setFilmId(Film filmId) {
        this.filmId = filmId;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
    
    
}
