package hackaton.helpers;

import lombok.Getter;

import java.util.List;

public class BaseTest extends TestConfig {
    @Getter
    private String scenario;
    @Getter
    private List<Class> classesList;

    public BaseTest(String scenario, List<Class> classesList) {
        this.scenario = scenario;
        this.classesList = classesList;
    }

    public BaseTest(String scenario, List<Class> classesList, String folder) {
        this.scenario = folder + scenario;
        this.classesList = classesList;
    }
}
