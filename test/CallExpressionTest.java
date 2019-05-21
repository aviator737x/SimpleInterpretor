import junit.framework.Assert;
import junit.framework.TestCase;

import java.util.ArrayList;

public class CallExpressionTest extends TestCase {

    public void testEval() throws SyntaxException, FunctionNotFoundException, RuntimeException, ParameterNotFoundException, ArgumentNumberMismatch {
        SyntaxAnalyzer analyzer = new SyntaxAnalyzer();
        CallExpression callExpression = new CallExpression();
        ArrayList<String> funcs = new ArrayList<String>();
        funcs.add("f(x)={(x+1)}");
        analyzer.setFunctionDefinitionList(funcs);
        analyzer.setExpression("f(1)");
        analyzer.parseFunctionDefinitionList(Main.functions);
        callExpression.setArgument(analyzer.parseExpression("10"));
        callExpression.setFuncName("f");
        Assert.assertEquals(callExpression.eval(new Variables()), 11);
    }
}