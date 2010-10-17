package resources;

import org.apache.cxf.jaxrs.ext.search.SearchCondition;
import org.apache.cxf.jaxrs.ext.search.SearchContext;
import sakila.Actor;
import sakila.Film;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import java.util.ArrayList;
import java.util.List;

/**
 * Sakila Restful Search Resource
 *
 * @author aruld@acm.org
 */
@Path("/sakila")
public class SakilaResource {

    @Context
    private SearchContext searchContext;

    List<Actor> actors = new ArrayList<Actor>();
    List<Film> films = new ArrayList<Film>();

    public SakilaResource() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Sakila");
        EntityManager em = emf.createEntityManager();
        @SuppressWarnings("unchecked")
        List<Actor> actorList = em.createNamedQuery("Actor.findAll").getResultList();
        for (Actor actor : actorList) {
            //break the cyclic graph mess to satisfy JAXB
            actor.setFilmActorCollection(null);
            actors.add(actor);
        }
        @SuppressWarnings("unchecked")
        List<Film> filmList = em.createNamedQuery("Film.findAll").getResultList();
        for (Film film : filmList) {
            //break the cyclic graph mess to satisfy JAXB
            film.setFilmActorCollection(null);
            film.setFilmCategoryCollection(null);
            film.setInventoryCollection(null);
            film.setLanguage(null);
            film.setLanguage1(null);
            films.add(film);
        }
    }

    @GET
    @Produces("application/xml")
    @Path("actors")
    public List<Actor> getActors() {
        return actors;
    }

    @GET
    @Produces("application/xml")
    @Path("searchActors")
    public List<Actor> searchActors() {
        SearchCondition<Actor> sc = searchContext.getCondition(Actor.class);

        if (sc == null) {
            throw new NotFoundException("Invalid search query.");
        }
        System.out.println(sc.toSQL("actor"));

        List<Actor> found = sc.findAll(actors);
        if (found.size() == 0) {
            throw new NotFoundException("No matching actor found.");
        }
        return found;
    }

    @GET
    @Produces("application/xml")
    @Path("films")
    public List<Film> getFilms() {
        return films;
    }

    @GET
    @Produces("application/xml")
    @Path("searchFilms")
    public List<Film> searchFilms() {
        SearchCondition<Film> sc = searchContext.getCondition(Film.class);

        if (sc == null) {
            throw new NotFoundException("Invalid search query.");
        }
        System.out.println(sc.toSQL("film"));

        List<Film> found = sc.findAll(films);
        if (found.size() == 0) {
            throw new NotFoundException("No matching film found.");
        }
        return found;
    }

}
