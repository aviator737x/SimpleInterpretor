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
    public int eval() {
        throw new UnsupportedOperationException();
    }
}

