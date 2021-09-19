package hackaton.runner;


import hackaton.Logger.Logger;
import hackaton.helpers.BaseTest;

import java.util.ArrayList;
import java.util.List;

public class ParallelRunner {

    private Logger logger = new Logger();
    private List<Runner> runnerList = new ArrayList<>();
    private int status = 0;

    public static void run (String[] args) {
        ParallelRunner runner = new ParallelRunner();
        Arguments arguments = new Arguments(args);
        runner.run(arguments);
    }

    private void run(Arguments arguments) {
        List<Thread> threadList = new ArrayList<>();
        while (arguments.classList.size() > 0) {
            int countOfThreads = arguments.getThreadCount() < arguments.classList.size() ? arguments.getThreadCount() : arguments.classList.size();
            for (int i = 0; i < countOfThreads; countOfThreads--) {
                try {
                    String className = arguments.classList.get(i);
                    arguments.classList.remove(className);
                    BaseTest clazz = (BaseTest) Class.forName(className).newInstance();
                    Runner runner = new Runner<>(clazz);
                    runnerList.add(runner);
                    Thread thread = new Thread(runner);
                    threadList.add(thread);
                } catch (Exception e) {
                    status = 1;
                    logger.error("Test failed: " + arguments.classList.get(i));
                    e.printStackTrace();
                }
            }

            for (Thread thread: threadList)
                thread.start();

            try {
                for (Thread thread: threadList)
                    thread.join();
            } catch (InterruptedException e) {
                status = 1;
                e.printStackTrace();
            }
        }
        for (Runner runner: runnerList)
            if (runner.status == 1)
                status = runner.status;

        System.exit(status);
    }
}
