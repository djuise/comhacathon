package com.hackathon.steps;

import com.hackathon.runner.Action;
import com.hackathon.runner.BaseTest;

public class ScTest extends BaseTest {

    @Action("test")
    public void test() {
        System.out.println("0000");
    }

    @Action("test1")
    public void test1() {
        System.out.println("11111");
    }

    @Action("test2")
    public void test2() {
        System.out.println("22222");
    }


}
