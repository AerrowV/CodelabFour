package dat;

import io.javalin.Javalin;
import io.javalin.http.Handler;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import static io.javalin.apibuilder.ApiBuilder.*;

public class Main {
    public static void main(String[] args) {
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
