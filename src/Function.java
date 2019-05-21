import java.util.ArrayList;

public class Function {
    public ArrayList<Identifier> parameters;
    public Expression body;
    public Variables variables = new Variables();

    public int run(ArrayList<Expression> params) throws ArgumentNumberMismatch {
        if (params.size() == parameters.size()) {
            for (int i = 0; i < parameters.size(); i++) {
                variables.setVar(parameters.get(i).getName(), params.get(i).eval(variables));
            }
            return body.eval(variables);
        } else {
            throw new ArgumentNumberMismatch("ARGUMENT NUMBER MISMATCH ");
        }
    }
}
