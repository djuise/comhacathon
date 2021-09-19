package hackaton.runner;

abstract public class SeleniumConfiguration {

    private static SeleniumConfiguration seleniumConfiguration = null;

    public SeleniumConfiguration() {
        if (seleniumConfiguration == null) {
            seleniumConfiguration = this;
        }
    }

    @SuppressWarnings({"unused", "ussing by childs"})
    abstract public void setUp();
    @SuppressWarnings({"unused", "ussing by childs"})
    abstract public void tearDown();

}
