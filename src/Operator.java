enum OpType {
    INCREMENT,
    DECREMENT,
    CLEAR,
}

public class Operator extends Node {

    Operator(Node parent, OpType op) {
        super(parent);
        operation = op;
    }

    @Override
    public void addChild(Node node) {
        if (children.size() > 1) {
            System.out.println("Adding failed: Operators can only have on child!");
        }
        else {
            System.out.println("Child added to operator node!");
            children.add(node);
        }
    }


    @Override
    public boolean isMaxChildren() {
        boolean isMaxChildren = false;
        if (children.size() > 0) {
            isMaxChildren = true;
        }
        return isMaxChildren;
    }

    @Override
    public void display() {
        System.out.print("Operator(" + operation + ")");
    }

    @Override
    public void execute(Environment env) {
        // Should only have one child
        Node child = children.get(0);
        switch(operation) {
        case CLEAR:
            System.out.println("CLEAR called");
            env.symbols.put(child.getValue(), 0);
            break;
        case INCREMENT:
            System.out.println("INCREMENT called on " + child.getValue());
            env.symbols.put(child.getValue(), env.symbols.get(child.getValue()) + 1);
            break;
        case DECREMENT:
            System.out.println("DECREMENT called");
            env.symbols.put(child.getValue(), env.symbols.get(child.getValue()) - 1);
            break;
        }

    }

    private OpType operation;
}
