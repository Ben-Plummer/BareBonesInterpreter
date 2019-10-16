
// We could parameterise this with Generics
public class Literal extends Node {

    Literal(Node parent, int integer) {
        super(parent);
        value = integer;
    }

    @Override
    public boolean isMaxChildren() {
        return true;
    }

    @Override
    public void display() {
        System.out.print("Literal(" + value + ")");
    }

    @Override
    public String getValue() {
        System.out.println("Get value of literal (" + value + ")");
        return Integer.toString(value);
    }

    private int value;
}
