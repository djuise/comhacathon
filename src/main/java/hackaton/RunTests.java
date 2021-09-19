package hackaton;


import hackaton.runner.ParallelRunner;

/**
 * Hello world!
 *
 */
public class RunTests {
    public static void main(String[] args) {
        // args should contains value of threads count and class names (with own/full package name)
        // mvn clean install exec:java -Dexec.mainClass=hackaton.RunTests -Dexec.args="2 tests.RunNegativeLoginTest tests.RunNegativeLoginTest"
        ParallelRunner.run(args);

    }
}
