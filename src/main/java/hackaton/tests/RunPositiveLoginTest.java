package hackaton.tests;

import hackaton.helpers.BaseTest;
import hackaton.runner.annotations.Test;
import hackaton.steps.MainPageSteps;
import hackaton.steps.SignInPageSteps;

import java.util.Arrays;

@SuppressWarnings({"unused", "using for tests"})
@Test
public class RunPositiveLoginTest extends BaseTest {
    public RunPositiveLoginTest() {
        super("PositiveLoginTest", Arrays.asList(MainPageSteps.class, SignInPageSteps.class));
    }
}
