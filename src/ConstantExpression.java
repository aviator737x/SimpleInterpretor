public class ConstantExpression extends Expression {
    private int value;

    public ConstantExpression(int value) {
        this.value = value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public int eval(Variables variables) {
        return value;
    }
}
