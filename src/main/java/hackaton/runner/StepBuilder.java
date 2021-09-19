package hackaton.runner;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class StepBuilder {

    private Step step = new Step();
    private String text;

    Step getStepObj(String text) {
        this.text = text;
        replaceStrings();
        replaceDoubles();
        replaceInts();
        step.setName(this.text);

        return step;
    }

    private void replaceStrings() {
        String firstQuote = " \"";
        String lastQuote = "\"";
        while (text.contains(firstQuote)) {
            int firstPosition = text.indexOf(firstQuote) + lastQuote.length();
            int lastPosition = text.indexOf(lastQuote, firstPosition + firstQuote.length());

            String firstPartText = text.substring(0, firstPosition);
            String lastPartText = text.substring(lastPosition + lastQuote.length());
            String value = text.substring(firstPosition + 1, lastPosition);
            step.addStringVar(value);
            text = firstPartText + "{string}" + lastPartText;
        }
    }

    private void replaceDoubles() {
        Pattern p = Pattern.compile("(\\d+(?:\\.\\d+))");
        Matcher m = p.matcher(text);
        while(m.find()) {
            String value = m.group(0);
            step.addDoubleVar(Double.parseDouble(value));
            text = text.replace(value, "{double}");
        }
    }

    private void replaceInts() {
        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(text);
        while(m.find()) {
            String value = m.group(0);
            step.addIntegerVar(Integer.parseInt(value));
            text = text.replace(value, "{int}");
        }
    }
}
