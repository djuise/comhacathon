package hackaton.tests;

import hackaton.helpers.BaseTest;
import hackaton.runner.annotations.Test;
import hackaton.steps.MainPageSteps;
import hackaton.steps.SignInPageSteps;

import java.util.Arrays;

@SuppressWarnings({"unused", "using for tests"})
@Test
public class RunNegativeLoginTest extends BaseTest {
    public RunNegativeLoginTest() {
        super("NegativeLoginTest", Arrays.asList(MainPageSteps.class, SignInPageSteps.class));
    }
}
