package client;

import org.apache.cxf.endpoint.Server;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.cxf.jaxrs.lifecycle.SingletonResourceProvider;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import resources.SakilaResource;
import sakila.Actor;
import sakila.Film;

import java.util.Collection;

import static junit.framework.Assert.assertEquals;

/**
 * User: aruld
 * Date: 10/16/10
 * Time: 11:53 PM
 */
public class SakilaSearchTest {

    private Server server;

    @Before
    public void setUp() {
        JAXRSServerFactoryBean sf = new JAXRSServerFactoryBean();
        sf.setResourceClasses(SakilaResource.class);
        sf.getInInterceptors().add(new LoggingInInterceptor());
        sf.getOutInterceptors().add(new LoggingOutInterceptor());
        sf.setResourceProvider(SakilaResource.class, new SingletonResourceProvider(new SakilaResource()));
        sf.setAddress("http://localhost:9000");
        server = sf.create();
    }

    @Test
    public void searchActors() {
        //http://localhost:9000/sakila/searchActors?_s=firstname==PENELOPE
        WebClient wc = WebClient.create("http://localhost:9000/sakila/searchActors?_s=firstname%3D%3DPENELOPE");
        Collection<? extends Actor> actors = wc.getCollection(Actor.class);
        assertEquals(4, actors.size());
    }

    @Test
    public void searchFilms() {
        //http://localhost:9000/sakila/searchFilms?_s=rating==PG;rentalduration!=0;title==SANTA*
        WebClient wc = WebClient.create("http://localhost:9000/sakila/searchFilms?_s=rating%3D%3DPG;rentalduration%21%3D0;title%3D%3DSANTA*");
        Collection<? extends Film> films = wc.getCollection(Film.class);
        assertEquals(1, films.size());
    }

    @After
    public void tearDown() {
        server.destroy();
    }
}
