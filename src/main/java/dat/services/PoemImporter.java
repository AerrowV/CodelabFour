package dat.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import dat.daos.PoemDAO;
import dat.dtos.PoemDTO;
import dat.entities.Poem;
import jakarta.persistence.EntityManagerFactory;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class PoemImporter {

    private static final File file = new File("src/main/resources/poem.json");

    private static final ObjectMapper objectMapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .registerModule(new JavaTimeModule());

    public static List<Poem> importPoemsFromJson(EntityManagerFactory emf) {
        try {
            List<PoemDTO> poemDTOs = objectMapper.readValue(file, new TypeReference<List<PoemDTO>>() {
            });

            PoemDAO poemDAO = PoemDAO.getInstance(emf);

            List<Poem> poems = poemDTOs
                    .stream()
                    .map(DTOMapper::poemToEntity)
                    .collect(Collectors.toList());

            for (Poem poem : poems) {
                poemDAO.create(poem);
            }

            System.out.println("Poems imported successfully!");
            return poems;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
