package at.kaismi.repository;

import at.kaismi.domain.Task;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository public class TaskRepository {

    public static Set<Task> tasks;
}
