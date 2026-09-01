package by.sapra.ttrpg.forum.domain.valueObject;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DateInfo {
    @Column(name = "create_at")
    private Instant createAt;
    @Column(name = "update_at")
    private Instant updateAt;
}
