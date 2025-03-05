package dat.services;

import dat.config.HibernateConfig;
import dat.daos.PoemDAO;
import dat.dtos.ErrorMessage;
import dat.dtos.PoemDTO;
import dat.entities.Poem;
import io.javalin.Javalin;
import jakarta.persistence.EntityManagerFactory;

import java.util.ArrayList;
import java.util.List;

import static io.javalin.apibuilder.ApiBuilder.*;

public class API {
    public static void main(String[] args) {
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory();
        PoemDAO poemDAO = PoemDAO.getInstance(emf);

        Javalin app = Javalin.create(config -> {
            config.router.contextPath = "/api";
            config.router.apiBuilder(() -> {
                path("/poem", () -> {

                    get(ctx -> {
                        try {
                            List<Poem> poem = poemDAO.readAll();
                            List<PoemDTO> poemDTOList = new ArrayList<>();
                            for (Poem p : poem) {
                                PoemDTO poemDTO = DTOMapper.entityToPoemDTO(p);
                                poemDTOList.add(poemDTO);
                            }
                            ctx.json(poemDTOList);
                        } catch (Exception e) {
                            e.printStackTrace();
                            ErrorMessage error = new ErrorMessage("No poems found");
                            ctx.status(404).json(error);
                        }
                    });

                    get("/{id}", ctx -> {
                        try {
                            Long poemId = Long.parseLong(ctx.pathParam("id"));
                            Poem poem = poemDAO.read(poemId);

                            PoemDTO poemDTO = DTOMapper.entityToPoemDTO(poem);
                            ctx.json(poemDTO);
                        } catch (Exception e) {
                            e.printStackTrace();
                            ErrorMessage error = new ErrorMessage("No poems found");
                            ctx.status(404).json(error);
                        }
                    });

                    post("/", ctx -> {
                        Poem incomingPoem = ctx.bodyAsClass(Poem.class);
                        Poem returnedPoem = poemDAO.create(incomingPoem);
                        PoemDTO poemDTO = DTOMapper.entityToPoemDTO(returnedPoem);
                        ctx.json(poemDTO);
                    });

                    put("/{id}", ctx -> {
                        Long id = Long.parseLong(ctx.pathParam("id"));
                        Poem incomingPoem = ctx.bodyAsClass(Poem.class);
                        for (Poem poem : poemDAO.readAll()) {
                            if (poem.getId().equals(id)) {
                                incomingPoem.setId(id);
                                poemDAO.update(incomingPoem);
                            }
                        }
                        ctx.json(poemDAO.read(id));
                    });
                });
            });
        }).start(7070);
    }
}
