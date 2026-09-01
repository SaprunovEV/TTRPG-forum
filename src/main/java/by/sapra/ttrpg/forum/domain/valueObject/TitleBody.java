package by.sapra.ttrpg.forum.domain.valueObject;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Embeddable
@Data
public class TitleBody {
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "content", columnDefinition = "TEXT")
    private String content;
    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 50)
    private TopicStatus status = TopicStatus.ACTIVE;
}
