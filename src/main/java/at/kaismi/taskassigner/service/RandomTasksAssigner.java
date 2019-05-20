package at.kaismi.taskassigner.service;

import at.kaismi.taskassigner.domain.AssignedTasksUnit;
import at.kaismi.taskassigner.domain.Task;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RandomTasksAssigner implements TasksAssigner {

    private TaskValidator taskValidator;

    public RandomTasksAssigner(TaskValidator taskValidator) {
        this.taskValidator = taskValidator;
    }

    public Set<AssignedTasksUnit> assignTasks(Set<String> names, Set<Task> tasks) {
        validateNames(names);
        validateTasks(tasks);

        int countNames = names.size();
        int sumWeights = tasks.stream().mapToInt(Task::getWeight).sum();
        int maxWeightForName = (sumWeights / countNames) + 1;

        Map<String, AssignedTasksUnit> nameAssignedTasksUnitMap = Maps.newHashMap();
        List<String> namesList = Lists.newArrayList(names);

        List<Task> taskList = Lists.newArrayList(tasks);
        taskList.sort(
                (t1, t2) -> (t1.getWeight() < t2.getWeight()) ? 1 : ((t1.getWeight() == t2.getWeight()) ? 0 : -1));

        taskList.forEach(t -> {
            Collections.shuffle(namesList);
            for (String name : namesList) {
                AssignedTasksUnit assignedTasksUnit = nameAssignedTasksUnitMap.get(name);
                if (Objects.isNull(assignedTasksUnit)) {
                    assignedTasksUnit = new AssignedTasksUnit(name);
                    assignedTasksUnit.getAssignedTasks().add(t);

                    nameAssignedTasksUnitMap.put(name, assignedTasksUnit);
                    break;
                } else {
                    if (hasEveryNameTasksAssigned(namesList, nameAssignedTasksUnitMap)
                            && assignedTasksUnit.getActualWeight() < maxWeightForName) {
                        assignedTasksUnit.getAssignedTasks().add(t);
                        break;
                    }
                }
            }
        });

        return Sets.newHashSet(nameAssignedTasksUnitMap.values());
    }

    private void validateTasks(Set<Task> tasks) {
        Objects.requireNonNull(tasks);
        if (tasks.isEmpty()) {
            throw new IllegalArgumentException("Empty tasks.");
        }
        tasks.forEach(t -> taskValidator.validateTask(t));
    }

    private void validateNames(Set<String> names) {
        Objects.requireNonNull(names);
        if (names.isEmpty()) {
            throw new IllegalArgumentException("Empty names.");
        }
    }

    private boolean hasEveryNameTasksAssigned(List<String> namesList,
                                              Map<String, AssignedTasksUnit> nameAssignedTasksUnitMap) {
        for (String n : namesList) {
            AssignedTasksUnit assignedTasksUnit = nameAssignedTasksUnitMap.get(n);
            if (Objects.isNull(assignedTasksUnit)) {
                return false;
            }
        }

        return true;
    }
}
