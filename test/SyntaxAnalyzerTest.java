import junit.framework.Assert;
import junit.framework.TestCase;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class SyntaxAnalyzerTest extends TestCase {

    public void testParseFunctionDefinitionList() {
        SyntaxAnalyzer analyzer = new SyntaxAnalyzer();
        ArrayList<String> functions = new ArrayList<String>();
        functions.add("f\\(x\\)=\\{\\(x+1\\)\\}");
        functions.add("g\\(x\\)=\\{\\(x+1\\)\\}");
        analyzer.setFunctionDefinitionList(functions);
        analyzer.parseFunctionDefinitionList();
    }

    public void testParseExpression() {
        SyntaxAnalyzer analyzer1 = new SyntaxAnalyzer();
        analyzer1.setExpression("(2+2)");
        Assert.assertTrue(analyzer1.parseExpression(analyzer1.getExpression()) instanceof BinaryExpression);
        SyntaxAnalyzer analyzer2 = new SyntaxAnalyzer();
        analyzer2.setExpression("2");
        Assert.assertTrue(analyzer2.parseExpression(analyzer2.getExpression()) instanceof ConstantExpression);
        SyntaxAnalyzer analyzer3 = new SyntaxAnalyzer();
        analyzer3.setExpression("var");
        Assert.assertTrue(analyzer3.parseExpression(analyzer3.getExpression()) instanceof Identifier);
        SyntaxAnalyzer analyzer4 = new SyntaxAnalyzer();
        analyzer4.setExpression("[(x>0)]?{1}:{0}");
        Assert.assertTrue(analyzer1.parseExpression(analyzer4.getExpression()) instanceof IfExpression);
        SyntaxAnalyzer analyzer5 = new SyntaxAnalyzer();
        analyzer5.setExpression("g(10)");
        Assert.assertTrue(analyzer5.parseExpression(analyzer5.getExpression()) instanceof CallExpression);
    }
}