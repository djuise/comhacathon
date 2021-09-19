package hackaton.Logger;

import java.util.Calendar;

public class Logger {
    private final Calendar calendar = Calendar.getInstance();

    private final String[] type =  {"ERROR", "WARN", "INFO"};

    private String getClassName() {
        return getClass().getSimpleName();
    }

    public void error(String message) {
        format(type[0],message);
    }
    public void warn(String message) {
        format(type[1],message);
    }

    public void info(String message) {
        format(type[2],message);
    }



    private void format(String type, String message) {

        String log = "[" +
                type +
                "] " +
                calendar.getTime() +
                " " + getClassName() +
                " - " +
                message;
        System.out.println(log);
    }
}
