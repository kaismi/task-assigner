package at.kaismi.taskassigner.service;

import at.kaismi.taskassigner.domain.AssignedTasksUnit;
import at.kaismi.taskassigner.domain.Task;

import java.util.Set;

public interface TasksAssigner {

    Set<AssignedTasksUnit> assignTasks(Set<String> names, Set<Task> tasks);
}
