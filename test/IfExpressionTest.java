import junit.framework.Assert;
import junit.framework.TestCase;

public class IfExpressionTest extends TestCase {

    public void testEval() throws SyntaxException {
        SyntaxAnalyzer analyzer = new SyntaxAnalyzer();
        BinaryExpression expression1 = (BinaryExpression) analyzer.parseExpression("(10>2)");
        ConstantExpression expression2 = (ConstantExpression) analyzer.parseExpression("3");
        ConstantExpression expression3 = (ConstantExpression) analyzer.parseExpression("4");
        IfExpression expression = new IfExpression();
        expression.setExpr1(expression1);
        expression.setExpr2(expression2);
        expression.setExpr3(expression3);
        Assert.assertEquals(expression.eval(new Variables()), 3);
    }
}