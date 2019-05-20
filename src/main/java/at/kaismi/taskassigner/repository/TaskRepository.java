package at.kaismi.taskassigner.repository;

import at.kaismi.taskassigner.domain.Task;
import com.google.common.collect.Sets;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public class TaskRepository {

    public static Set<Task> tasks = Sets.newHashSet();
}
