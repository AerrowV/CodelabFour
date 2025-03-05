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
}
