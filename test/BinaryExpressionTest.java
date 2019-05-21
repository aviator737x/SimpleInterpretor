import junit.framework.Assert;
import junit.framework.TestCase;

public class BinaryExpressionTest extends TestCase {

    public void testEval() throws SyntaxException, FunctionNotFoundException, RuntimeException, ParameterNotFoundException, ArgumentNumberMismatch {
        SyntaxAnalyzer analyzer = new SyntaxAnalyzer();
        BinaryExpression expression = new BinaryExpression();
        expression.setLeftOperand(analyzer.parseExpression("2"));
        expression.setRightOperand(analyzer.parseExpression("2"));
        expression.setOperation('+');
        Assert.assertEquals(expression.eval(new Variables()), 4);
        expression.setOperation('-');
        Assert.assertEquals(expression.eval(new Variables()), 0);
        expression.setOperation('*');
        Assert.assertEquals(expression.eval(new Variables()), 4);
        expression.setOperation('/');
        Assert.assertEquals(expression.eval(new Variables()), 1);
        expression.setOperation('%');
        Assert.assertEquals(expression.eval(new Variables()), 0);
        expression.setOperation('<');
        Assert.assertEquals(expression.eval(new Variables()), 0);
        expression.setOperation('>');
        Assert.assertEquals(expression.eval(new Variables()), 0);
    }
}