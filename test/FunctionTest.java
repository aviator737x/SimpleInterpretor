import junit.framework.Assert;
import junit.framework.TestCase;

import java.util.ArrayList;

public class FunctionTest extends TestCase {

    public void testRun() throws SyntaxException, ArgumentNumberMismatch, RuntimeException, ParameterNotFoundException, FunctionNotFoundException {
        SyntaxAnalyzer analyzer = new SyntaxAnalyzer();
        Variables variables = new Variables();
        variables.setVar("x", 4);
        Function function = new Function();
        function.variables = variables;
        function.body = analyzer.parseExpression("(x+y)");
        ArrayList<Identifier> parameters = new ArrayList<Identifier>();
        parameters.add((Identifier) analyzer.parseExpression("y"));
        function.parameters = parameters;
        ArrayList<Expression> params = new ArrayList<Expression>();
        params.add(analyzer.parseExpression("(2+2)"));
        Assert.assertEquals(function.run(params), 8);
    }
}