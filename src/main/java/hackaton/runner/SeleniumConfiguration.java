package hackaton.runner;

abstract public class SeleniumConfiguration {

    private static SeleniumConfiguration seleniumConfiguration = null;

    public SeleniumConfiguration() {
        if (seleniumConfiguration == null) {
            seleniumConfiguration = this;
        }
    }

    abstract public void setUp();
    abstract public void tearDown();

}
