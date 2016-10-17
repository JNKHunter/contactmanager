package tech.eats.art.contactmanager;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;
import tech.eats.art.contactmanager.model.Contact;

/**
 * Created by John on 10/16/16.
 */
public class Application {

    //public static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        final ServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        return new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }

    public static void main(String[] args) {
        Contact contact = new Contact.ContactBuilder("John", "Hunter")
                .withEmail("test@test.com")
                .withPhone(1234567l)
                .build();

        System.out.println(contact);
    }
}
