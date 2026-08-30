package by.sapra.ttrpg.forum;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class TtrpgForumAppApplicationTests {

    @Test
    void contextLoads() {
    }

}
