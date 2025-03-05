package dat.services;

import dat.dtos.PoemDTO;
import dat.entities.Poem;

public class DTOMapper {

    public static Poem poemToEntity(PoemDTO poemDTO) {
        Poem poem = new Poem();
        poem.setId(poemDTO.getId());
        poem.setTitle(poemDTO.getTitle());
        poem.setShape(poemDTO.getShape());
        poem.setText(poemDTO.getText());
        return poem;
    }

    public static PoemDTO entityToPoemDTO(Poem poem) {
        PoemDTO poemDTO = new PoemDTO();
        poemDTO.setId(poem.getId());
        poemDTO.setTitle(poem.getTitle());
        poemDTO.setShape(poem.getShape());
        poemDTO.setText(poem.getText());
        return poemDTO;
    }
}
