package dat.dtos;

import lombok.Data;

@Data
public class PoemDTO {
    private Long id;
    private String title;
    private String shape;
    private String text;
}
