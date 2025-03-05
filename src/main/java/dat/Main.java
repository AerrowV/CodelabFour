package dat;

import dat.config.HibernateConfig;
import dat.daos.PoemDAO;
import io.javalin.Javalin;
import io.javalin.http.Handler;
import jakarta.persistence.EntityManagerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static dat.controllers.PoemController.createPoemDatabaseFromJson;

public class Main {

    private static final EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory();
    private static final PoemDAO POEM_DAO = PoemDAO.getInstance(emf);

    public static void main(String[] args) {
    }
}
