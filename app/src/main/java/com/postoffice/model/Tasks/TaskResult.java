package com.postoffice.model.Tasks;

import java.util.List;

public class TaskResult {
    private boolean success;
    private List<Task> tasks;

    public boolean getSuccess() { return success; }
    public void setSuccess(boolean value) { this.success = value; }

    public List<Task> getTasks() { return tasks; }
    public void setTasks(List<Task> value) { this.tasks = value; }
}
