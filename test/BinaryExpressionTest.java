import junit.framework.Assert;
import junit.framework.TestCase;

public class BinaryExpressionTest extends TestCase {

    public void testEval() throws SyntaxException {
        SyntaxAnalyzer analyzer = new SyntaxAnalyzer();
        BinaryExpression expression = new BinaryExpression();
        expression.setLeftOperand(analyzer.parseExpression("2"));
        expression.setRightOperand(analyzer.parseExpression("2"));
        expression.setOperation('+');
        Assert.assertEquals(expression.eval(), 4);
        expression.setOperation('-');
        Assert.assertEquals(expression.eval(), 0);
        expression.setOperation('*');
        Assert.assertEquals(expression.eval(), 4);
        expression.setOperation('/');
        Assert.assertEquals(expression.eval(), 1);
        expression.setOperation('%');
        Assert.assertEquals(expression.eval(), 0);
        expression.setOperation('<');
        Assert.assertEquals(expression.eval(), 0);
        expression.setOperation('>');
        Assert.assertEquals(expression.eval(), 0);
    }
}