public class BinaryExpression extends Expression {
    private Expression leftOperand;
    private Expression rightOperand;
    private char operation;

    public void setLeftOperand(Expression leftOperand) {
        this.leftOperand = leftOperand;
    }

    public void setRightOperand(Expression rightOperand) {
        this.rightOperand = rightOperand;
    }

    public void setOperation(char operation) {
        this.operation = operation;
    }

    private int sum(Variables variables) throws ParameterNotFoundException, FunctionNotFoundException, ArgumentNumberMismatch, RuntimeException {
        return leftOperand.eval(variables) + rightOperand.eval(variables);
    }

    private int sub(Variables variables) throws ParameterNotFoundException, FunctionNotFoundException, ArgumentNumberMismatch, RuntimeException {
        return leftOperand.eval(variables) - rightOperand.eval(variables);
    }

    private int mul(Variables variables) throws ParameterNotFoundException, FunctionNotFoundException, ArgumentNumberMismatch, RuntimeException {
        return leftOperand.eval(variables) * rightOperand.eval(variables);
    }

    private int div(Variables variables) throws ParameterNotFoundException, FunctionNotFoundException, ArgumentNumberMismatch, RuntimeException {
        return leftOperand.eval(variables) / rightOperand.eval(variables);
    }

    private int mod(Variables variables) throws ParameterNotFoundException, FunctionNotFoundException, ArgumentNumberMismatch, RuntimeException {
        return leftOperand.eval(variables) % rightOperand.eval(variables);
    }

    private int less(Variables variables) throws ParameterNotFoundException, FunctionNotFoundException, ArgumentNumberMismatch, RuntimeException {
        return leftOperand.eval(variables) < rightOperand.eval(variables) ? 1 : 0;
    }

    private int more(Variables variables) throws ParameterNotFoundException, FunctionNotFoundException, ArgumentNumberMismatch, RuntimeException {
        return leftOperand.eval(variables) > rightOperand.eval(variables) ? 1 : 0;
    }

    private int assign(Variables variables) throws ParameterNotFoundException, FunctionNotFoundException, ArgumentNumberMismatch, RuntimeException {
        if (!(leftOperand instanceof Identifier)) {
            return 0;
        }
        variables.setVar(((Identifier) leftOperand).getName(), rightOperand.eval(variables));
        return 1;
    }

    @Override
    public int eval(Variables variables) throws ParameterNotFoundException, FunctionNotFoundException, ArgumentNumberMismatch, RuntimeException {
        try {
            switch (operation) {
                case '+':
                    return sum(variables);
                case '-':
                    return sub(variables);
                case '*':
                    return mul(variables);
                case '/':
                    return div(variables);
                case '%':
                    return mod(variables);
                case '<':
                    return less(variables);
                case '>':
                    return more(variables);
                case '=':
                    return assign(variables);
                default:
                    return 0;
            }
        } catch (java.lang.RuntimeException e) {
            String left = (leftOperand instanceof Identifier) ?
                    ((Identifier) leftOperand).getName() : String.valueOf(leftOperand.eval(variables));
            String right = (rightOperand instanceof Identifier) ?
                    ((Identifier) rightOperand).getName() : String.valueOf(rightOperand.eval(variables));
            throw new RuntimeException("RUNTIME ERROR (" + left + operation + right + "):");
        }
    }
}
