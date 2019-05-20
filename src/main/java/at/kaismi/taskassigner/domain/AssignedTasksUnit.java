package at.kaismi.taskassigner.domain;

import com.google.common.collect.Sets;

import java.util.Set;

public class AssignedTasksUnit {

    private String name;
    private Set<Task> assignedTasks = Sets.newHashSet();

    public AssignedTasksUnit() {
    }

    public AssignedTasksUnit(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Task> getAssignedTasks() {
        return assignedTasks;
    }

    public void setAssignedTasks(Set<Task> assignedTasks) {
        this.assignedTasks = assignedTasks;
    }

    public int getActualWeight() {
        return assignedTasks.stream().mapToInt(Task::getWeight).sum();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        AssignedTasksUnit that = (AssignedTasksUnit) o;

        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
