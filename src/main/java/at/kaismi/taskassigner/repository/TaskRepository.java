package at.kaismi.taskassigner.repository;

import at.kaismi.taskassigner.domain.Task;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;

@Repository
public class TaskRepository {

    public static Set<Task> tasks = new HashSet<>();
}
