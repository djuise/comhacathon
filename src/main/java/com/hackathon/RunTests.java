package com.hackathon;

import com.hackathon.runner.ParallelRunner;

/**
 * Hello world!
 *
 */
public class RunTests {
    public static void main(String[] args) {

        ParallelRunner.run(args);
//        String[] s = new String[]{"2", "tests.RunFirstTest", "tests.RunFirstTest2"};
//        Arguments arguments = new Arguments(s);


//        Runnable r = null;
//        try {
//            BaseTest clazz = (BaseTest) Class.forName("com.hackathon.tests.RunFirstTest").newInstance();
//            r = new Runner<>(clazz);
//        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
//            e.printStackTrace();
//        }
////        Runnable r = new Runner<>(new RunFirstTest());
//        Thread t = new Thread(r);
//        t.start();
////        Runnable r2 = new Runner<>(new RunFirstTest2());
////        Thread t2 = new Thread(r2);
////        t2.start();
//
//        try {
//            t.join();
////            t2.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }
}
