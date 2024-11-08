package com.astrophysics.workflow;

public interface Step {
    void execute() throws StepExecutionException;
}
