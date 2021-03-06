package at.fhj.swd.postgres;/*
 * project    company
 * subproject simplest
 */

// Schadler, Reindl, Klug

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.AfterClass;
import org.junit.Test;

@org.junit.FixMethodOrder(org.junit.runners.MethodSorters.NAME_ASCENDING)
public class ProductsEntityTest {

    static EntityManagerFactory factory;
    static EntityManager manager;
    static EntityTransaction transaction;

    static final String persistenceUnitName = "products";

    static final int id = 158;
    static final String name = "Testproduct";

    @BeforeClass
    public static void setup() {
        factory = Persistence.createEntityManagerFactory(persistenceUnitName);
        assertNotNull(factory);
        manager = factory.createEntityManager();
        assertNotNull(manager);

        transaction = manager.getTransaction();
    }

    @AfterClass
    public static void teardown() {
        if (manager == null)
            return;

        manager.close();
        factory.close();
    }


    @Test
    public void create() {
        transaction.begin();
        ProductsEntity testProduct = new ProductsEntity(name, ProductBarcode.EAN128);
        assertNotNull(testProduct);
        manager.persist(testProduct);
        transaction.commit();

        System.out.println("Created and Persisted " + testProduct);

    }

    @Test
    public void modify() {
        ProductsEntity testProduct = manager.find(ProductsEntity.class, 1);
        assertNotNull(testProduct);
        System.out.println("Found " + testProduct);

        transaction.begin();
        testProduct.setProductName("Testprodukt-NEU");
        transaction.commit();

        testProduct = manager.find(ProductsEntity.class, 1);

        assertEquals("Testprodukt-NEU", testProduct.getProductName());
        System.out.println("Updated " + testProduct);
    }

    @Test
    public void remove() {
        ProductsEntity testProduct = manager.find(ProductsEntity.class, 1);
        assertNotNull(testProduct);

        transaction.begin();
        manager.remove(testProduct);
        transaction.commit();

        testProduct = manager.find(ProductsEntity.class, 1);
        assertNull(testProduct);

        System.out.println("Removed " + id);
    }
}
