package at.kaismi.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class AssignedTasksUnit {

    private String name;
    private int week;
    private Date validFrom;
    private Date validTo;
    private Set<Task> assignedTasks = new HashSet<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public Date getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(Date validFrom) {
        this.validFrom = validFrom;
    }

    public Date getValidTo() {
        return validTo;
    }

    public void setValidTo(Date validTo) {
        this.validTo = validTo;
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

    @Override public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        AssignedTasksUnit that = (AssignedTasksUnit)o;

        return name.equals(that.name);
    }

    @Override public int hashCode() {
        return name.hashCode();
    }

    @Override public String toString() {
        return "AssignedTasksUnit{" + "name='" + name + '\'' + ", week=" + week + ", validFrom=" + validFrom
                + ", validTo=" + validTo + ", assignedTasks=" + assignedTasks + '}';
    }
}
