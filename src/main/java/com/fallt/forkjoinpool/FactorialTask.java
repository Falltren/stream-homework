package com.fallt.forkjoinpool;

import java.util.concurrent.RecursiveTask;

public class FactorialTask extends RecursiveTask<Integer> {

    private final int number;

    public FactorialTask(int number) {
        this.number = number;
    }

    @Override
    protected Integer compute() {
        if (number == 1 || number == 0) {
            return 1;
        }
        if (number < 0) {
            throw new IllegalArgumentException("The number can`t be less then 0");
        }
        FactorialTask newTask = new FactorialTask(number - 1);
        newTask.fork();
        return number * newTask.join();
    }

}
