package at.kaismi.taskassigner.web;

import at.kaismi.taskassigner.domain.Task;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Disabled // Remove if your service is running and start test to generate example data
public class LocalhostTestDataOneTimeTest {

    private final RestTemplate testRestTemplate = new RestTemplate();

    @Test public void createTestData() {
        createTasks();
        createNames();

        /*ResponseEntity<Set> assignedTasksUnitEntity = testRestTemplate
                .getForEntity("http://localhost:8080/tasks", Set.class);

        assertThat(assignedTasksUnitEntity.getStatusCode()).isEqualTo(HttpStatus.OK);*/
    }

    private void createNames() {
        Set<String> names = Stream.of("Claudia", "Michael").collect(Collectors.toSet());

        ResponseEntity<Set> namesEntity = testRestTemplate
                .postForEntity("http://localhost:8080/names", names, Set.class);

        // assertThat(namesEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    private void createTasks() {
        Set<Task> tasks = Stream
                .of(new Task("Staubsaugen", "Zweimal wöchentlich Staubsaugen in der ganzen Wohnung.", 3),
                        new Task("Katzenklo", "Einmal täglich Katzenklo leeren.", 1), //
                        new Task("Abendessen zubreiten", "Täglich das Abendessen zubereiten.", 4), //
                        new Task("Wäsche waschen + aufhängen", "Die Wäsche waschen und nach max. 1 Stunde aufhängen.",
                                2), //
                        new Task("Wäsche abnhehmen + bügeln", "Die Wäsche bei Bedarf abnehmen und bügeln.", 3), //
                        new Task("Abwaschen", "Den Abwasch bei Bedarf übernehmen.", 1), //
                        new Task("Mittagessen am WE", "Am Wochenende das Mittagessen zubereiten.", 3), //
                        new Task("Müll Entsorgung", "Den Müll runterbringen.", 1), //
                        new Task("Klo und Bad putzen", "Das Klo und das Bad putzen.", 3), //
                        new Task("Frühstück zubereiten", "Täglich das Frühstück zubereiten.", 2))
                .collect(Collectors.toSet());

        ResponseEntity<Set> tasksEntity = testRestTemplate
                .postForEntity("http://localhost:8080/tasks", tasks, Set.class);

        // assertThat(tasksEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }
}
