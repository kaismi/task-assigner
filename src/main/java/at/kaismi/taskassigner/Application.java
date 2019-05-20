package at.kaismi.taskassigner;

import at.kaismi.taskassigner.domain.Task;
import at.kaismi.taskassigner.repository.NameRepository;
import at.kaismi.taskassigner.repository.TaskRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Set;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
            ObjectMapper mapper = new ObjectMapper();

            String jsonDir = null;
            if (args.length > 0) {
                jsonDir = args[0];
            }

            InputStream inputStreamNames = getInputStream(jsonDir, "names.json");
            NameRepository.names = mapper.readValue(inputStreamNames, new TypeReference<Set<String>>() {

            });

            InputStream inputStreamTasks = getInputStream(jsonDir, "tasks.json");
            TaskRepository.tasks = mapper.readValue(inputStreamTasks, new TypeReference<Set<Task>>() {

            });
        };
    }

    private InputStream getInputStream(String jsonDir, String resourceName) throws FileNotFoundException {
        InputStream inputStream;
        if (Objects.nonNull(jsonDir)) {
            inputStream = new FileInputStream(new File(jsonDir + "/" + resourceName));
        } else {
            inputStream = TypeReference.class.getResourceAsStream("/" + resourceName);
        }
        return inputStream;
    }
}
