import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class SyntaxAnalyzer {
    private ArrayList<String> functionDefinitionList = new ArrayList<String>();
    private String expression = "";

    public void init(InputStreamReader reader) throws IOException {
        StringBuilder newLine = new StringBuilder();
        while (true) {
            char c = (char) reader.read();
            if (c == '\n') {
                break;
            }
            newLine.append(c);
        }
        String readLine = newLine.toString();
        if (readLine.matches("^[A-Za-z]\\(.*\\)=\\{.*\\}")) {
            functionDefinitionList.add(readLine);
            init(reader);
        } else {
            expression = readLine;
        }
    }

    public ArrayList<String> getFunctionDefinitionList() {
        return functionDefinitionList;
    }

    public String getExpression() {
        return expression;
    }

    public void parseFunctionDefinitionList(Funtions funtions) throws SyntaxException {
        int j = 1;
        for (String funtion : functionDefinitionList) {
            int openRoundBracketInd = funtion.indexOf("(");
            String name = funtion.substring(0, openRoundBracketInd);
            int closeRoundInd = funtion.indexOf(")");
            String[] params = funtion.substring(openRoundBracketInd + 1, closeRoundInd).split(",");
            ArrayList<Identifier> parameters = new ArrayList<Identifier>();
            for (String param : params) {
                parameters.add((Identifier) parseExpression(param));
            }
            Expression body = parseExpression(funtion.substring(closeRoundInd + 3, funtion.length() - 1));
            Function functionToAdd = new Function();
            functionToAdd.parameters = parameters;
            functionToAdd.body = body;
            functionToAdd.number = j;
            j++;
            funtions.addFunction(name, functionToAdd);
        }
    }

    public Expression parseExpression(String expression) throws SyntaxException {
        if (expression.matches("^[A-Za-z]+$")) {
            return new Identifier(expression, null);
        } else if (expression.matches("-{0,1}[0-9]+$")) {
            return parseConstantExpression(expression);
        } else if (expression.startsWith("(") && expression.endsWith(")")) {
            return parseBinaryExpression(expression);
        } else if (expression.matches("^[A-Za-z]+\\(.*\\)$")) {
            return parseCallExpression(expression);
        } else if (expression.matches("^\\[.+\\]?\\{.*\\}:\\{.*\\}$")) {
            return parseIfExpression(expression);
        }
        throw new SyntaxException();
    }

    private Expression parseConstantExpression(String expression) {
        int coeff = expression.startsWith("-") ? -1 : 1;
        int number = 0;
        for (int i = 0; i < expression.length(); i++) {
            number += ((int) (expression.charAt(i) - '0')) * ((int) Math.pow(10, (expression.length() - i - 1)));
        }
        return new ConstantExpression(coeff * number);
    }

    private Expression parseBinaryExpression(String expression) throws SyntaxException {
        char op = 'a';
        int roundBrackets = 0;
        int squareBrackets = 0;
        int curlyBrackets = 0;
        for (int i = 1; i < expression.length(); i++) {
            if (expression.charAt(i) == '(') {
                roundBrackets++;
            } else if (expression.charAt(i) == ')') {
                roundBrackets--;
            } else if (expression.charAt(i) == '[') {
                squareBrackets++;
            } else if (expression.charAt(i) == ']') {
                squareBrackets--;
            } else if (expression.charAt(i) == '{') {
                curlyBrackets++;
            } else if (expression.charAt(i) == '}') {
                curlyBrackets--;
            } else {
                if (roundBrackets != 0 || squareBrackets != 0 || curlyBrackets != 0) {
                    continue;
                }
                char c = expression.charAt(i);
                if (c == '+' || c == '-' || c == '*' || c == '/' || c == '%' || c == '<' || c == '>' || c == '=') {
                    BinaryExpression result = new BinaryExpression();
                    result.setOperation(c);
                    result.setLeftOperand(parseExpression(expression.substring(1, i)));
                    result.setRightOperand(parseExpression(expression.substring(i+1, expression.length() - 1)));
                    return result;
                }
            }
        }
        throw new SyntaxException();
    }

    private Expression parseCallExpression(String expression) throws SyntaxException {
        CallExpression result = new CallExpression();
        int openBracketIndex = expression.indexOf("(");
        result.setFuncName(expression.substring(0, openBracketIndex));
        String[] arguments = expression.substring(openBracketIndex + 1, expression.length() - 1).split(",");
        for (String arg : arguments) {
            result.setArgument(parseExpression(arg));
        }
        return result;
    }

    private Expression parseIfExpression(String expression) throws SyntaxException {
        int countSquareBrackets = 0;
        int closeSquareInd = 0;
        for (int i = 0; i < expression.length(); i++) {
            if (expression.charAt(i) == '[') {
                countSquareBrackets++;
            } else {
                if (expression.charAt(i) == ']' && countSquareBrackets != 1) {
                    countSquareBrackets--;
                } else if (expression.charAt(i) == ']') {
                    closeSquareInd = i;
                    break;
                }
            }
        }
        IfExpression result = new IfExpression();
        result.setExpr1(parseExpression(expression.substring(1, closeSquareInd)));
        int countRoundBrackets = 0;
        int closeRoundInd = 0;
        for (int i = closeSquareInd + 3; i < expression.length(); i++) {
            if (expression.charAt(i) == '{') {
                countRoundBrackets++;
            } else {
                if (expression.charAt(i) == '}' && countRoundBrackets != 0) {
                    countRoundBrackets--;
                } else if (expression.charAt(i) == '}'){
                    closeRoundInd = i;
                    break;
                }
            }
        }
        result.setExpr2(parseExpression(expression.substring(closeSquareInd + 3, closeRoundInd)));
        result.setExpr3(parseExpression(expression.substring(closeRoundInd + 3, expression.length() - 1)));
        return result;
    }

    public void setFunctionDefinitionList(ArrayList<String> functionDefinitionList) {
        this.functionDefinitionList = functionDefinitionList;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }
}
