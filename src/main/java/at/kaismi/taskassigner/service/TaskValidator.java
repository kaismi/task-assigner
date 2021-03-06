package at.kaismi.taskassigner.service;

import at.kaismi.taskassigner.domain.Task;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component public class TaskValidator {

    public void validateTask(Task task) {
        Objects.requireNonNull(task);

        if (Objects.isNull(task.getName())) {
            throw new IllegalArgumentException("Task name must not be null.");
        }
    }
}
