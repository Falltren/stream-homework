package com.fallt.forkjoinpool;

import java.util.concurrent.RecursiveTask;

public class FactorialTask extends RecursiveTask<Integer> {

    private final int number;

    public FactorialTask(int number) {
        this.number = number;
    }

    @Override
    protected Integer compute() {
        if (number == 1) {
            return 1;
        }
        if (number < 1) {
            throw new IllegalArgumentException("The number can`t be less then 1");
        }
        FactorialTask newTask = new FactorialTask(number - 1);
        newTask.fork();
        return number * newTask.join();
    }

}
