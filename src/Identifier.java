public class Identifier extends Expression {
    private String name;

    public Identifier(String name) {
        this.name = name;
    }

    @Override
    public int eval() {
        throw new UnsupportedOperationException();
    }

    public String getName() {
        return name;
    }
}
