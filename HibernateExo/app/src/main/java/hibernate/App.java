package hibernate;

import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.Transaction;

public class App {

    static Session session;
    public static void main(String[] args) {
        
        init();

       //TestFA();
        TestFC();

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
}
