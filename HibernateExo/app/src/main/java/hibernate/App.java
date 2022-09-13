package hibernate;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import org.hibernate.Transaction;

public class App {

    static Session session;
    public static void main(String[] args) {
        
        init();

        FindAddress();

        session.close();
    }

    public static void init(){
        Configuration configuration = new Configuration().configure();

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        session = sessionFactory.openSession();
        
    }

    public static void getInformation(){
       
        Country c1 = session.getReference(Country.class,3);
        Actor a1 = session.getReference(Actor.class, 4);
        Language l1 = session.getReference(Language.class, 7);
        Film f1 = session.getReference(Film.class, 1001);

        System.out.println(c1.getCountry());
        System.out.println(a1.getFirst_name()+" "+a1.getLast_name());
        System.out.println(l1.getName()+" "+l1.getLanguageId());
        System.out.println(f1.getLanguageId().getLanguageId());
    }

    /* 
    public static void getInformationLongRequest(){

        Category cat3 = session.getReference(Category.class, 1);
        Iterator<Film> i = cat3.getFilms().iterator();
        while(i.hasNext()){
            //System.out.println(i.next().getTitle());
            Iterator<Actor> a = i.next().getActors().iterator();
            while(a.hasNext()){
                Actor rst = a.next();
                System.out.println(rst.getFirst_name()+" "+rst.getLast_name());
            }
        }
    }
    */

    /* 
    public static void getInformationInnerJoin(){

        Film f2 = session.getReference(Film.class, 4);
        Set<Category> cat1 = f2.getCategory();
        for (Category c : cat1 )
        System.out.println(c.getCategoryName()+" "+c.getCategoryId()+" "+f2.getTitle());
    }
    */

    public static void insertInformation(){
        Language l = new Language();
        l.setName("Occitan");
        l.setLastupDate(new Date());

        l = session.getReference(Language.class,7);
        Film f = new Film();
        f.setDescription("Film sur les Sudiste");
        f.setTitle("L'occitanie");
        f.setRelease_year(2022);
        f.setLanguageId(l);
        f.setRentalRate(0.5f);
        f.setRating("PG");
        f.setLength(114);
        f.setRentalDuration(1f);
        f.setReplacementCost(2f);
        f.setLastupDate(new Date());
        f.setSpecial_features("trailers");


        Transaction tx = session.beginTransaction();
        session.persist(l);
        session.persist(f);
        tx.commit();
    }

    public static void TestFA(){

        Film f1 = session.getReference(Film.class, 4);
        f1.getFilmActors().forEach((e) -> {
            System.out.println(e.getActor().getLast_name()+"-"+e.getFilm().getTitle());
        });

        Transaction tx = session.beginTransaction();
        tx.commit();
    }

    public static void TestFC(){

        Film f1 = session.getReference(Film.class, 1);
        f1.getFilmCategory().forEach((r) -> {
            System.out.println(r.getCaterogy().getCategoryName()+"-"+r.getFilm().getTitle());
        });

        Transaction tx = session.beginTransaction();
        tx.commit();
    }

    //Exemple de Query
    public static void Query(){

        TypedQuery<Film> myQuery = session.createQuery("select f from Film f where f.title like :title", Film.class);

        myQuery.setParameter("title","N%");

        List<Film> film = myQuery.getResultList();

        System.out.println(film.size());
    }
    //Exemple de NamedQuery
    public static void NamedQuery(){

        TypedQuery<Film> myQuery = session.createNamedQuery("Film.moreThanTwoHour", Film.class);

        myQuery.setParameter("length","120");

        List<Film> film = myQuery.getResultList();

        System.out.println(film.size());
    }
    //Exemple de NativeQuery
    public static void NativeQuery(){

        TypedQuery<Film> myQuery = session.createNativeQuery("SELECT film.*,language.name FROM film  INNER JOIN language  on film.language_id = language.language_id WHERE replacement_cost > :cost", Film.class);

        myQuery.setParameter("cost","10");

        List<Film> film = myQuery.getResultList();

        System.out.println(film.size());
    }
    //Exemple de criteria
    public static void CriteriaByFilmIn2006(){

        // builder
        CriteriaBuilder builder = session.getCriteriaBuilder();
        // préparation
        CriteriaQuery<Film> criteria = builder.createQuery(Film.class);
        // selection de l'objet root
        Root<Film> root = criteria.from(Film.class);

        // création de la requete
        criteria.select(root);
        criteria.where(builder.equal(root.get("release_year"), "2006"));

        List<Film> film = session.createQuery(criteria).getResultList();

        System.out.println(film.size());

    }

    public static void CriteriaAllActors(){

        // builder
        CriteriaBuilder builder = session.getCriteriaBuilder();
        // préparation
        CriteriaQuery<Actor> criteria = builder.createQuery(Actor.class);
        // selection de l'objet root
        Root<Actor> root = criteria.from(Actor.class);

        // création de la requete
        criteria.select(root);

        List<Actor> actor = session.createQuery(criteria).getResultList();

        actor.forEach((actors)->{

            System.out.println(actors.getFirst_name()+" "+actors.getLast_name());

        });

        System.out.println(actor.size());
    }
    //Exo
    public static void LanguageStock(){//Query
        TypedQuery<Language> myQuery = session.createQuery("select l from Language l", Language.class);

        List<Language> language = myQuery.getResultList();

        language.forEach((languages)->{

            System.out.println(languages.getName());

        });
    }

    public static void ListAddressAndDistrict(){//Query
        TypedQuery<Address> myQuery = session.createQuery("select a from Address a", Address.class);

        List<Address> address = myQuery.getResultList();

        address.forEach((addresses)->{

            System.out.println(addresses.getAddress()+" "+addresses.getAddress2()+" "+addresses.getDistrict());

        });
    }

    public static void ContentInventory(){//NamedQuery
        TypedQuery<Inventory> myQuery = session.createNamedQuery("Inventory.content", Inventory.class);

        List<Inventory> inventory = myQuery.getResultList();

        System.out.println(inventory.size());


        //pour afficher le contenue de la table
        //inventory.forEach((inventorys)->{
        //    System.out.println(inventorys.getInventoryId()+" "+inventorys.getStoreId()+" "+inventorys.getFilmId()+" "+inventorys.getLastUpdate());
        //});
    }

    public static void FirstNameCustomer(){//Criteria

        // builder
        CriteriaBuilder builder = session.getCriteriaBuilder();
        // préparation
        CriteriaQuery<Customer> criteria = builder.createQuery(Customer.class);
        // selection de l'objet root
        Root<Customer> root = criteria.from(Customer.class);

        // création de la requete
        criteria.select(root);

        List<Customer> firstName = session.createQuery(criteria).getResultList();

        System.out.println(firstName.size());
    }

    public static void FilmIdByYears(){//Criteria

        // builder
        CriteriaBuilder builder = session.getCriteriaBuilder();
        // préparation
        CriteriaQuery<Film> criteria = builder.createQuery(Film.class);
        // selection de l'objet root
        Root<Film> root = criteria.from(Film.class);

        // création de la requete
        criteria.select(root);

        List<Film> film = session.createQuery(criteria).getResultList();

        film.forEach((films)->{

            System.out.println(films.getFilmId()+" "+films.getTitle()+" "+films.getRelease_year());

        });
    }

    public static void FilmLength(){//NamedQuery

        TypedQuery<Film> myQuery = session.createNamedQuery("Film.between50And90", Film.class);

        List<Film> film = myQuery.getResultList();

        film.forEach((films)->{

            System.out.println(films.getFilmId()+" "+films.getTitle()+" "+films.getLength());

        });
    }

    public static void FindAddress(){//Criteria

        // builder
        CriteriaBuilder builder = session.getCriteriaBuilder();
        // préparation
        CriteriaQuery<Address> criteria = builder.createQuery(Address.class);
        // selection de l'objet root
        Root<Address> root = criteria.from(Address.class);

        // création de la requete
        criteria.select(root);
        criteria.where(builder.like(root.get("address"), "%Mysore Drive%"));

        List<Address> address = session.createQuery(criteria).getResultList();

        address.forEach((ad)->{

            System.out.println(ad.getAddressId()+" "+ad.getAddress());

        });
    }
}
