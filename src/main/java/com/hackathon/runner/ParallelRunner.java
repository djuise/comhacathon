package com.hackathon.runner;

import com.hackathon.helpers.BaseTest;

import java.util.ArrayList;
import java.util.List;

public class ParallelRunner {

    public static void run (String[] args) {
        ParallelRunner runner = new ParallelRunner();
        Arguments arguments = new Arguments(args);
        runner.run(arguments);
    }

    public void run(Arguments arguments) {
        List<Thread> threadList = new ArrayList<>();
        while (arguments.classList.size() > 0) {
            int countOfThreads = arguments.getThreadCount() < arguments.classList.size() ? arguments.getThreadCount() : arguments.classList.size();
            for (int i = 0; i < countOfThreads; countOfThreads--) {
                try {
                    String className = arguments.classList.get(i);
                    BaseTest clazz = (BaseTest) Class.forName(className).newInstance();
                    arguments.classList.remove(className);
                    Runnable runnable = new Runner<>(clazz);
                    Thread thread = new Thread(runnable);
                    threadList.add(thread);
                } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
                    e.printStackTrace();
                }
            }

            for (Thread thread: threadList)
                thread.start();

            try {
                for (Thread thread: threadList)
                    thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
