package at.kaismi.service;

import at.kaismi.domain.AssignedTasksUnit;
import at.kaismi.domain.Task;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.*;

@Service
public class RandomTasksAssigner implements TasksAssigner {

    private TaskValidator taskValidator;

    public RandomTasksAssigner(TaskValidator taskValidator) {
        this.taskValidator = taskValidator;
    }

    public Set<AssignedTasksUnit> assignTasks(Set<String> names, Set<Task> tasks) {
        Objects.requireNonNull(names);
        if (names.isEmpty()) {
            throw new IllegalArgumentException("Empty names.");
        }
        Objects.requireNonNull(tasks);
        tasks.forEach(t -> taskValidator.validateTask(t));

        int countNames = names.size();
        int sumWeights = tasks.stream().mapToInt(Task::getWeight).sum();
        int maxWeightForName = (sumWeights / countNames) + 1;

        LocalDate date = LocalDate.now();
        WeekFields weekFields = WeekFields.of(Locale.getDefault());
        int week = date.get(weekFields.weekOfWeekBasedYear());

        // TODO
        Date firstOfWeek = null;
        Date lastOfWeek = null;

        Map<String, AssignedTasksUnit> nameAssignedTasksUnitMap = new HashMap<>();

        List<String> namesList = new ArrayList<>(names);

        List<Task> taskList = new ArrayList<>(tasks);
        taskList.sort(
                (t1, t2) -> (t1.getWeight() < t2.getWeight()) ? 1 : ((t1.getWeight() == t2.getWeight()) ? 0 : -1));

        taskList.forEach(t -> {
            Collections.shuffle(namesList);
            for (String name : namesList) {
                AssignedTasksUnit assignedTasksUnit = nameAssignedTasksUnitMap.get(name);
                if (Objects.isNull(assignedTasksUnit)) {
                    assignedTasksUnit = new AssignedTasksUnit();
                    assignedTasksUnit.setWeek(week);
                    assignedTasksUnit.setName(name);
                    assignedTasksUnit.setValidFrom(firstOfWeek);
                    assignedTasksUnit.setValidTo(lastOfWeek);

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

        return new HashSet<>(nameAssignedTasksUnitMap.values());
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
