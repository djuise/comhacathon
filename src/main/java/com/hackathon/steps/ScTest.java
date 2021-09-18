package com.hackathon.steps;

import com.hackathon.runner.Action;
import com.hackathon.runner.BaseTest;

public class ScTest extends BaseTest {

    @Action("test {string} {int} {double}")
    public void test(String a, int c, double b) {
        System.out.println("-----------0---------");
        System.out.println(a);
        System.out.println(c);
        System.out.println(b);
        System.out.println("-----------0---------");
    }

    @Action("test {int} {int}")
    public void test1(int a, int b) {
        System.out.println("-----------1---------");
        System.out.println(a);
        System.out.println(b);
        System.out.println("-----------1---------");
    }

    @Action("test {double}")
    public void test2(double d) {
        System.out.println("-----------2---------");
        System.out.println(d);
        System.out.println("-----------2---------");
    }


}
