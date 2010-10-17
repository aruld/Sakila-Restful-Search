package client;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import sakila.Actor;
import sakila.Film;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

import static junit.framework.Assert.assertEquals;

/**
 * User: aruld
 * Date: 10/16/10
 * Time: 8:57 PM
 */
public class SakilaJpaTest {

    private List<Actor> actors;
    private List<Film> films;
    private EntityManager em;

    @Before
    @SuppressWarnings("unchecked")
    public void setUp() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Sakila");
        em = emf.createEntityManager();
        actors = em.createNamedQuery("Actor.findAll").getResultList();
        films = em.createNamedQuery("Film.findAll").getResultList();
    }

    @Test
    public void jpaQuery() {
        assertEquals(200, actors.size());
        assertEquals(1000, films.size());
    }

    @After
    public void tearDown() {
         em.close();
    }
}
