package hackaton;


import hackaton.runner.ParallelRunner;

/**
 * Hello world!
 *
 */
public class RunTests {
    public static void main(String[] args) {

        // args should contains value of threads count and class names (with own/full package name)
        // For example {"2", "tests.RunBannerTest", "tests.RunNegativeLoginTest"}
        // Not works with maven yet
//        ParallelRunner.run(args);

        // For debuging
        String[] s = new String[]{"2", "tests.RunBannerTest", "tests.RunNegativeLoginTest"};
        ParallelRunner.run(s);
    }
}
