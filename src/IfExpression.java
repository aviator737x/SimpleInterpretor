public class IfExpression extends Expression {
    private Expression expr1;
    private Expression expr2;
    private Expression expr3;

    public void setExpr1(Expression expr1) {
        this.expr1 = expr1;
    }

    public void setExpr2(Expression expr2) {
        this.expr2 = expr2;
    }

    public void setExpr3(Expression expr3) {
        this.expr3 = expr3;
    }

    @Override
    public int eval(Variables variables) throws ParameterNotFoundException, FunctionNotFoundException, ArgumentNumberMismatch, RuntimeException {
        return expr1.eval(variables) != 0 ? expr2.eval(variables) : expr3.eval(variables);
    }
}