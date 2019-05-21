public class Identifier extends Expression {
    private String name;
    private Integer value;

    public Identifier(String name, Integer value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public int eval(Variables variables) throws ParameterNotFoundException {
        return variables.getVar(name);
    }

    public String getName() {
        return name;
    }
}