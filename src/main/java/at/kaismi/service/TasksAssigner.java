package at.kaismi.service;

import at.kaismi.domain.AssignedTasksUnit;
import at.kaismi.domain.Task;

import java.util.Set;

public interface TasksAssigner {

    Set<AssignedTasksUnit> assignTasks(Set<String> names, Set<Task> tasks);
}
