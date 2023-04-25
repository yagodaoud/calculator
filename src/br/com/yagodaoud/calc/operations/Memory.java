package br.com.yagodaoud.calc.operations;

import java.util.ArrayList;
import java.util.List;

public class Memory {
    private enum CommandType {
        CLEAR, NUMBER, ADD, SUBTRACT, MULTIPLY, DIVIDE, EQUALS, MODULUS, COMMA;
    }

    private static final Memory instance = new Memory();
    private CommandType lastestOperation = null;
    private boolean replace = false;
    private String currentText = "";
    private String bufferText = "";
    private final List<MemoryObserver> observers = new ArrayList<>();

    private Memory() {
    }

    public static Memory getInstance() {
        return instance;
    }

    public void addObserver(MemoryObserver observer) {
        observers.add(observer);
    }

    public String getCurrentText() {
        return currentText.isEmpty() ? "0" : currentText;
    }

    public void processCommand(String value) {

        CommandType commandType = commandTypeDetect(value);

        if (commandType == null) {
            return;
        } else if (commandType == CommandType.CLEAR) {
            currentText = "";
            bufferText = "";
            replace = false;
            lastestOperation = null;
        } else if (commandType == CommandType.MODULUS && currentText.contains("-")) {
            currentText = currentText.substring(1);
        } else if (commandType == CommandType.MODULUS && !currentText.contains("-")) {
            currentText = "-" + currentText;
        } else if (commandType == CommandType.NUMBER || commandType == CommandType.COMMA) {
            currentText = replace ? value : currentText + value;
            replace = false;
        } else {
            replace = true;
            currentText = getOperationResult();
            bufferText = currentText;
            lastestOperation = commandType;
        }

        observers.forEach(o -> o.changedValue(getCurrentText()));
    }

    private String getOperationResult() {
        if (lastestOperation == null || lastestOperation == CommandType.EQUALS) {
            return currentText;
        }
        double bufferNumber = Double.parseDouble(bufferText.replace(",", "."));
        double currentNumber = Double.parseDouble(currentText.replace(",", "."));

        double result = 0;

        if (lastestOperation == CommandType.ADD) {
            result = bufferNumber + currentNumber;
        } else if (lastestOperation == CommandType.SUBTRACT) {
            result = bufferNumber - currentNumber;
        } else if (lastestOperation == CommandType.MULTIPLY) {
            result = bufferNumber * currentNumber;
        } else if (lastestOperation == CommandType.DIVIDE) {
            result = bufferNumber / currentNumber;
        }

        String stringResult = Double.toString(result).replace(".", ",");
        boolean integer = stringResult.endsWith(",0");
        return integer ? stringResult.replace(",0", "") : stringResult;
    }

    private CommandType commandTypeDetect(String text) {
        if (currentText.isEmpty() && text == "0") {
            return null;
        }
        try {
            Integer.parseInt(text);
            return CommandType.NUMBER;
        } catch (NumberFormatException e) {
            if ("AC".equals(text)) {
                return CommandType.CLEAR;
            } else if ("/".equals(text)) {
                return CommandType.DIVIDE;
            } else if ("*".equals(text)) {
                return CommandType.MULTIPLY;
            } else if ("-".equals(text)) {
                return CommandType.SUBTRACT;
            } else if ("+".equals(text)) {
                return CommandType.ADD;
            } else if ("=".equals(text)) {
                return CommandType.EQUALS;
            } else if ("±".equals(text)) {
                return CommandType.MODULUS;
            } else if (",".equals(text) && !currentText.contains(",")) {
                return CommandType.COMMA;
            }
            return null;
        }


    }
}
