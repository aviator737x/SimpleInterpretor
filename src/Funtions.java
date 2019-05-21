import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Funtions {
    private Map<String, Function> funcs = new HashMap<String, Function>();

    public void addFunction(String name, Function def) {
        funcs.put(name, def);
    }

    public int callFunction(String name, ArrayList<Expression> params) throws ParameterNotFoundException, FunctionNotFoundException, ArgumentNumberMismatch, RuntimeException {
        try {
            Function func = new Function();
            func.parameters = funcs.get(name).parameters;
            func.body = funcs.get(name).body;
            return func.run(params);
        } catch (ParameterNotFoundException e) {
            throw new ParameterNotFoundException(e.getMessage() + funcs.get(name).number);
        } catch (FunctionNotFoundException e) {
            if (e.getMessage().endsWith(":")) {
                throw new FunctionNotFoundException(e.getMessage() + funcs.get(name).number);
            } else {
                throw e;
            }
        } catch (ArgumentNumberMismatch e) {
            throw new ArgumentNumberMismatch(e.getMessage() + name + ":" + funcs.get(name).number);
        } catch (RuntimeException e) {
            if (e.getMessage().endsWith(":"))
                throw new RuntimeException(e.getMessage() + funcs.get(name).number);
            throw e;
        }
    }

    public int run() throws ParameterNotFoundException, FunctionNotFoundException, ArgumentNumberMismatch, RuntimeException {
        try {
            return funcs.get("$main").run(new ArrayList<Expression>());
        } catch (ParameterNotFoundException e) {
            if (e.getMessage().endsWith(":"))
                throw new ParameterNotFoundException(e.getMessage() + funcs.get("$main").number);
            throw e;
        } catch (ArgumentNumberMismatch e) {
            if (e.getMessage().endsWith(":"))
                throw new ArgumentNumberMismatch(e.getMessage() + funcs.get("$main").number);
            throw e;
        } catch (RuntimeException e) {
            if (e.getMessage().endsWith(":"))
                throw new RuntimeException(e.getMessage() + funcs.get("$main").number);
            throw e;
        }
    }

    public int size() {
        return funcs.size();
    }

    public boolean contains(String name) {
        return funcs.containsKey(name);
    }
}

