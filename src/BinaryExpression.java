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

    private int sum() {
        return leftOperand.eval() + rightOperand.eval();
    }

    private int sub() {
        return leftOperand.eval() - rightOperand.eval();
    }

    private int mul() {
        return leftOperand.eval() * rightOperand.eval();
    }

    private int div() {
        return leftOperand.eval() / rightOperand.eval();
    }

    private int mod() {
        return leftOperand.eval() % rightOperand.eval();
    }

    private int less() {
        return leftOperand.eval() < rightOperand.eval() ? 1 : 0;
    }

    private int more() {
        return leftOperand.eval() > rightOperand.eval() ? 1 : 0;
    }

    private int assign() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int eval() {
        switch (operation) {
            case '+':
                return sum();
            case '-':
                return sub();
            case '*':
                return mul();
            case '/':
                return div();
            case '%':
                return mod();
            case '<':
                return less();
            case '>':
                return more();
            case '=':
                return assign();
            default:
                return 0;
        }
    }
}
