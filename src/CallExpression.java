import java.util.ArrayList;

public class CallExpression extends Expression {
    private ArrayList<Expression> argumentList = new ArrayList<Expression>();
    private String funcName;

    public void setFuncName(String funcName) {
        this.funcName = funcName;
    }

    public void setArgument(Expression expression) {
        argumentList.add(expression);
    }

    @Override
    public int eval(Variables variables) throws ParameterNotFoundException, FunctionNotFoundException, ArgumentNumberMismatch, RuntimeException {
        if (!Main.functions.contains(funcName)) {
            throw new FunctionNotFoundException("FUNCTION NOT FOUND " + funcName + ":");
        }
        ArrayList<Expression> evaluatedArgumentList = new ArrayList<Expression>();
        for (Expression expression : argumentList) {
            evaluatedArgumentList.add(new ConstantExpression(expression.eval(variables)));
        }
        return Main.functions.callFunction(funcName, evaluatedArgumentList);
    }
}

