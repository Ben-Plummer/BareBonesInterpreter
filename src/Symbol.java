// Can we some how template this class?
public class Symbol extends Node {

    Symbol(Node parent, String sym) {
        super(parent);
        symbol = sym;
        System.out.println("Symbol '" + sym + "' created!");
    }

    public void addChild() {
        System.out.println("We cannot add children to Symbols!");
    }

    @Override
    public boolean isMaxChildren() {
        return true;
    }

    @Override
    public void display() {
        System.out.print("Symbol(" + symbol +")");
    }

    @Override
    public String getValue() {
        System.out.println("Referencing symbol (" + symbol + ")");
        return symbol;
    }

    private String symbol;
}
