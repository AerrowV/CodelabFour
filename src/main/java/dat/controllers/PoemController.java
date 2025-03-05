package dat.controllers;

import dat.config.HibernateConfig;
import dat.entities.Poem;
import dat.services.PoemImporter;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

public class PoemController {

    private static final EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory();

    public static void createPoemDatabaseFromJson() {
        List<Poem> importedPoems = PoemImporter.importPoemsFromJson(emf);
        System.out.println(importedPoems);
    }
}
