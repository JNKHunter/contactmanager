package tech.eats.art.contactmanager;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;
import tech.eats.art.contactmanager.model.Contact;

import java.util.List;

/**
 * Created by John on 10/16/16.
 */
public class Application {

    public static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        final ServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        return new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }

    public static void main(String[] args) {
        Contact contact = new Contact.ContactBuilder("John", "Hunter")
                .withEmail("test@test.com")
                .withPhone(1234567l)
                .build();

        save(contact);

        for(Contact c : fetchAllContacts()){
            System.out.println(c);
        }

    }

    @SuppressWarnings("unchecked")
    private static List<Contact> fetchAllContacts(){
        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(Contact.class);
        List<Contact> contacts = criteria.list();

        session.close();

        return contacts;
    }

    private static void save(Contact contact) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(contact);
        session.getTransaction().commit();
        session.close();
    }
}
