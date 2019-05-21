import java.util.HashMap;
import java.util.Map;

public class Variables {
    private Map<String, Integer> vars = new HashMap<String, Integer>();

    public int getVar(String name) throws ParameterNotFoundException {
        try {
            return vars.get(name);
        } catch (Exception e) {
            throw new ParameterNotFoundException("PARAMETER NOT FOUND " + name + ":");
        }
    }

    public int setVar(String name, int value) {
        vars.put(name, value);
        return 1;
    }

}