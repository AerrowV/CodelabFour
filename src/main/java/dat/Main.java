package dat;

import dat.config.HibernateConfig;
import dat.daos.PoemDAO;
import dat.dtos.PoemDTO;
import dat.entities.Poem;
import dat.services.PoemImporter;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

public class Main {

    private static final EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory();
    private static final PoemDAO POEM_DAO = PoemDAO.getInstance(emf);

    public static void main(String[] args) {

        List<Poem> importedPoems = PoemImporter.importPoemsFromJson(emf);
        System.out.println(importedPoems);

    }
}