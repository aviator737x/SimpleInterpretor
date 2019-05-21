public abstract class Expression {

    public abstract int eval(Variables variables) throws ParameterNotFoundException, FunctionNotFoundException, ArgumentNumberMismatch, RuntimeException;
}