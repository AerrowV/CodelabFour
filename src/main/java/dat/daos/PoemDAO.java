package dat.daos;

import dat.entities.Poem;
import dat.exceptions.ApiException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

import static PoemDAO.emf;

public class PoemDAO implements IDAO<Poem, Long>  {


    private static EntityManagerFactory emf;
    private static PoemDAO instance = null;

    private PoemDAO() {}

    public static PoemDAO getInstance(EntityManagerFactory _emf) {
        if (emf == null) {
            emf = _emf;
            instance = new PoemDAO();
        }
        return instance;
    }


    @Override
    public Poem create(Poem poem) {
        try(EntityManager em = emf.createEntityManager()) {
        em.getTransaction().begin();
        em.persist(poem);
        em.getTransaction().commit();

        }catch(Exception e) {
        new ApiException(400, "poem was not created");
    }
        return poem;
}


    @Override
    public List<Poem> readAll() {
        try(EntityManager _em = emf.createEntityManager()) {
            return _em.createQuery("SELECT s from Poem s", Poem.class).getResultList();
        }
    }



    @Override
    public Poem read(Long id) {
        try(EntityManager _em = emf.createEntityManager()) {
            return _em.find(Poem.class, id);
        }
    }


    @Override
    public Poem update(Poem poem) {
        try(EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.merge(poem);
            em.getTransaction().commit();

        }catch (Exception e) {
            new ApiException(400,"poem wasnt updated");
        }
        return poem;
    }

    @Override
    public void delete(Long id) {
        try(EntityManager _em = emf.createEntityManager()) {
            _em.getTransaction().begin();
            _em.remove(read(id));
            _em.getTransaction().commit();
        }catch (Exception e) {
            new ApiException(400,"poem wasnt deleted");
        }

    }
}
