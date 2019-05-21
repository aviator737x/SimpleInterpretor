import junit.framework.Assert;
import junit.framework.TestCase;

public class ConstantExpressionTest extends TestCase {

    public void testEval() {
        ConstantExpression expression = new ConstantExpression(2);
        Assert.assertEquals(expression.eval(new Variables()), 2);
    }
}