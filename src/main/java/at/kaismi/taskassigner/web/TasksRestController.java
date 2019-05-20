package at.kaismi.taskassigner.web;

import at.kaismi.taskassigner.domain.AssignedTasksUnit;
import at.kaismi.taskassigner.domain.Task;
import at.kaismi.taskassigner.repository.NameRepository;
import at.kaismi.taskassigner.repository.TaskRepository;
import at.kaismi.taskassigner.service.TasksAssigner;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
        return randomTasksAssigner.assignTasks(NameRepository.names, TaskRepository.tasks);
    }

    @RequestMapping(method = RequestMethod.GET)
    public Set<Task> getTasks() {
        return TaskRepository.tasks;
    }
}
