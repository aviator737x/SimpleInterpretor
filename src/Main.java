import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
    public static Funtions functions = new Funtions();

    public static void main(String[] args) throws IOException {
        try {
            InputStreamReader reader = new InputStreamReader(System.in);
            SyntaxAnalyzer program = new SyntaxAnalyzer();
            program.init(reader);
            program.parseFunctionDefinitionList(functions);
            Function mainFunction = new Function();
            mainFunction.parameters = new ArrayList<Identifier>();
            mainFunction.body = program.parseExpression(program.getExpression());
            mainFunction.number = functions.size() + 1;
            String name = "$main";
            functions.addFunction(name, mainFunction);
            System.out.println(functions.run());
        } catch (SyntaxException e) {
            System.out.println("SYNTAX ERROR");
        } catch (ParameterNotFoundException | FunctionNotFoundException | ArgumentNumberMismatch | RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }
}
