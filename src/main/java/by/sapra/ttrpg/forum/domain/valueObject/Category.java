package by.sapra.ttrpg.forum.domain.valueObject;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "category", schema = "forum")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
}
