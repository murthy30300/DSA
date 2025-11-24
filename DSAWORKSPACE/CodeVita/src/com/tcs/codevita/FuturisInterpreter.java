package com.tcs.codevita;

import java.util.*;
import java.util.regex.*;

interface FuturisExpression {
    void interpret(Map<String, Integer> context);
}

class PrintExpression implements FuturisExpression {
    private String value;

    public PrintExpression(String value) {
        this.value = value;
    }

    @Override
    public void interpret(Map<String, Integer> context) {
        if (context.containsKey(value)) {
            System.out.println(context.get(value));
        } else {
            System.out.println(value);
        }
    }
}

class ForLoopExpression implements FuturisExpression {
    private String variable;
    private String start;
    private String end;
    private List<FuturisExpression> body;

    public ForLoopExpression(String variable, String start, String end, List<FuturisExpression> body) {
        this.variable = variable;
        this.start = start;
        this.end = end;
        this.body = body;
    }

    @Override
    public void interpret(Map<String, Integer> context) {
        int startVal = getValue(start, context);
        int endVal = getValue(end, context);
        for (int i = startVal; i <= endVal; i++) {
            context.put(variable, i);
            for (FuturisExpression expr : body) {
                expr.interpret(context);
            }
        }
    }

    private int getValue(String str, Map<String, Integer> context) {
        if (context.containsKey(str)) {
            return context.get(str);
        }
        return Integer.parseInt(str);
    }
}

class IfExpression implements FuturisExpression {
    private Condition condition;
    private List<FuturisExpression> thenBranch;
    private List<FuturisExpression> elseBranch;

    public IfExpression(Condition condition, List<FuturisExpression> thenBranch, List<FuturisExpression> elseBranch) {
        this.condition = condition;
        this.thenBranch = thenBranch;
        this.elseBranch = elseBranch;
    }

    @Override
    public void interpret(Map<String, Integer> context) {
        if (condition.evaluate(context)) {
            for (FuturisExpression expr : thenBranch) {
                expr.interpret(context);
            }
        } else if (elseBranch != null) {
            for (FuturisExpression expr : elseBranch) {
                expr.interpret(context);
            }
        }
    }
}

class Condition {
    private String left;
    private String operator;
    private String right;

    public Condition(String left, String operator, String right) {
        this.left = left;
        this.operator = operator;
        this.right = right;
    }

    public boolean evaluate(Map<String, Integer> context) {
        int leftVal = getValue(left, context);
        int rightVal = getValue(right, context);
        switch (operator) {
            case "==": return leftVal == rightVal;
            case "!=": return leftVal != rightVal;
            case "<": return leftVal < rightVal;
            case ">": return leftVal > rightVal;
            default: return false;
        }
    }

    private int getValue(String str, Map<String, Integer> context) {
        if (context.containsKey(str)) {
            return context.get(str);
        }
        return Integer.parseInt(str);
    }
}

class FuturisParser {
    private List<String> lines;
    private int currentLine;

    public FuturisParser(List<String> lines) {
        this.lines = lines;
        this.currentLine = 0;
    }

    public List<FuturisExpression> parse() {
        List<FuturisExpression> expressions = new ArrayList<>();
        while (currentLine < lines.size()) {
            String line = lines.get(currentLine).trim();
            if (line.isEmpty()) {
                currentLine++;
                continue;
            }
            if (line.startsWith("for")) {
                expressions.add(parseForLoop());
            } else if (line.startsWith("if")) {
                expressions.add(parseIfStatement());
            } else if (line.startsWith("print")) {
                expressions.add(parsePrint());
                currentLine++;
            } else {
                currentLine++;
            }
        }
        return expressions;
    }

    private ForLoopExpression parseForLoop() {
        String line = lines.get(currentLine).trim();
        String[] parts = line.split("\\s+");
        String variable = parts[1];
        String start = parts[2];
        String end = parts[3];
        currentLine++;
        List<FuturisExpression> body = parseBlock();
        return new ForLoopExpression(variable, start, end, body);
    }

    private IfExpression parseIfStatement() {
        String line = lines.get(currentLine).trim();
        String conditionStr = line.substring(2).trim();
        Condition condition = parseCondition(conditionStr);
        currentLine++;
        if (!lines.get(currentLine).trim().equals("Yes")) {
            throw new RuntimeException("Expected 'Yes' after if condition");
        }
        currentLine++;
        List<FuturisExpression> thenBranch = parseBlock();
        List<FuturisExpression> elseBranch = null;
        if (currentLine < lines.size() && lines.get(currentLine).trim().equals("No")) {
            currentLine++;
            elseBranch = parseBlock();
        }
        return new IfExpression(condition, thenBranch, elseBranch);
    }

    private Condition parseCondition(String conditionStr) {
        Pattern pattern = Pattern.compile("(\\w+)\\s*(==|!=|<|>)\\s*(\\w+)");
        Matcher matcher = pattern.matcher(conditionStr);
        if (matcher.find()) {
            String left = matcher.group(1);
            String operator = matcher.group(2);
            String right = matcher.group(3);
            return new Condition(left, operator, right);
        }
        throw new RuntimeException("Invalid condition: " + conditionStr);
    }

    private PrintExpression parsePrint() {
        String line = lines.get(currentLine).trim();
        String value = line.substring(5).trim();
        return new PrintExpression(value);
    }

    private List<FuturisExpression> parseBlock() {
        List<FuturisExpression> block = new ArrayList<>();
        while (currentLine < lines.size()) {
            String line = lines.get(currentLine).trim();
            if (line.equals("end")) {
                currentLine++;
                break;
            } else if (line.equals("Yes") || line.equals("No")) {
                break;
            } else if (line.startsWith("for")) {
                block.add(parseForLoop());
            } else if (line.startsWith("if")) {
                block.add(parseIfStatement());
            } else if (line.startsWith("print")) {
                block.add(parsePrint());
                currentLine++;
            } else {
                currentLine++;
            }
        }
        return block;
    }
}

public class FuturisInterpreter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> inputLines = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (!line.isEmpty()) {
                inputLines.add(line);
            }
        }
        int lastLine = inputLines.size() - 1;
        int secondLastLine = inputLines.size() - 2;
        String[] variables = inputLines.get(secondLastLine).split("\\s+");
        String[] values = inputLines.get(lastLine).split("\\s+");
        List<String> codeLines = inputLines.subList(0, secondLastLine);
        Map<String, Integer> context = new HashMap<>();
        for (int i = 0; i < variables.length; i++) {
            context.put(variables[i], Integer.parseInt(values[i]));
        }
        FuturisParser parser = new FuturisParser(codeLines);
        List<FuturisExpression> program = parser.parse();
        for (FuturisExpression expr : program) {
            expr.interpret(context);
        }
        scanner.close();
    }
}
