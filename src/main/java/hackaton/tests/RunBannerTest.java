package hackaton.tests;

import hackaton.helpers.BaseTest;
import hackaton.runner.annotations.Test;
import hackaton.steps.MainPageSteps;
import hackaton.steps.SignInPageSteps;

import java.util.Arrays;

@SuppressWarnings({"unused", "using for tests"})
@Test
public class RunBannerTest extends BaseTest {
    public RunBannerTest() {
        super("BannersAreDisplayedAndClickable", Arrays.asList(MainPageSteps.class, SignInPageSteps.class));
    }
}
