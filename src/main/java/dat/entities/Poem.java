package dat.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Poem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;

    @Column(length = 5000)
    private String shape;
    private String text;

    public Poem(String title, String shape, String text) {
        this.title = title;
        this.shape = shape;
        this.text = text;
    }
}
