package at.kaismi.web;

import at.kaismi.domain.AssignedTasksUnit;
import at.kaismi.domain.Task;
import at.kaismi.repository.NameRepository;
import at.kaismi.repository.TaskRepository;
import at.kaismi.service.TasksAssigner;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;
import java.util.Set;

@RequestMapping("/tasks")
@RestController
public class TasksRestController {

    private TasksAssigner randomTasksAssigner;

    public TasksRestController(TasksAssigner randomTasksAssigner) {
        this.randomTasksAssigner = randomTasksAssigner;
    }

    @RequestMapping(path = "assign/random", method = RequestMethod.GET)
    public Set<AssignedTasksUnit> getRandomAssignedTasksUnits() {
        if (Objects.isNull(TaskRepository.tasks)) {
            throw new IllegalStateException("No tasks.");
        }

        if (Objects.isNull(NameRepository.names)) {
            throw new IllegalStateException("No names.");
        }

        return randomTasksAssigner.assignTasks(NameRepository.names, TaskRepository.tasks);
    }

    @RequestMapping(method = RequestMethod.GET)
    public Set<Task> getTasks() {
        return TaskRepository.tasks;
    }
}
