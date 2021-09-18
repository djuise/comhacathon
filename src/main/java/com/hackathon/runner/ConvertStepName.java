package com.hackathon.runner;

public class ConvertStepName {
    static String convertStepName(String text) {
        String textWithoutString = replaceStrings(text);

        return replaceNumbers(textWithoutString);
    }

    private static String replaceStrings(String text) {
        String firstQuote = " \"";
        String lastQuote = "\"";
        while (text.contains(firstQuote)) {
            int firstPosition = text.indexOf(firstQuote) + lastQuote.length();
            int lastPosition = text.indexOf(lastQuote, firstPosition + firstQuote.length());
            String firstPartText = text.substring(0, firstPosition);
            String lastPartText = text.substring(lastPosition + lastQuote.length());
            text = firstPartText + "{string}" + lastPartText;
        }

        return text;
    }

    private static String replaceNumbers(String text) {
        String textWithoutDoubles = text.replaceAll("\\d+[.]\\d+", "{double}");

        return textWithoutDoubles.replaceAll("\\d+", "{int}");
    }
}
