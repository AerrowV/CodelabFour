package dat;

import dat.config.HibernateConfig;
import dat.daos.PoemDAO;
import dat.dtos.PoemDTO;
import dat.entities.Poem;
import dat.services.PoemImporter;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

import io.javalin.Javalin;
import io.javalin.http.Handler;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import static io.javalin.apibuilder.ApiBuilder.*;

public class Main {

    private static final EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory();
    private static final PoemDAO POEM_DAO = PoemDAO.getInstance(emf);

    public static void main(String[] args) {
//        List<Poem> importedPoems = PoemImporter.importPoemsFromJson(emf);
//        System.out.println(importedPoems);


        Javalin app = Javalin.create().start(7070);

        app.get("/api/data", getJsonData);
    }

    private static Handler getJsonData = ctx -> {
        String filePath = "src/main/resources/poem.json";
        try {
            String jsonContent = new String(Files.readAllBytes(Paths.get(filePath)));
            ctx.contentType("application/json");
            ctx.result(jsonContent);
        } catch (IOException e) {
            ctx.status(500).result("Error reading JSON file");
        }
    };


    }
