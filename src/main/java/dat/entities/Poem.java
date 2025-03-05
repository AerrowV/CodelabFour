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
    private String content;
    private String genre;

    public Poem(String title, String content, String genre) {
        this.title = title;
        this.content = content;
        this.genre = genre;
    }
}
