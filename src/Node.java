import java.util.ArrayList;

public class Node {

    Node(Node par) {
        parent = par;
    }

    public void addChild(Node node) {
        children.add(node);
        System.out.println("Child added!");
    }

    public void execute() {
        for (Node child : children) {
            child.execute();
        }
    }

    public boolean isMaxChildren() {
        return false;
    }

    // TEMP?
    public void printChildren(String indent) {
        for (Node child : children) {

            System.out.print("\n" + indent);
            child.display();
            String childIndent = indent + "    ";
            child.printChildren(childIndent);
        }
    }

    public void display() {
        System.out.print("Node()");
    }

    public Node getParent() {
        return parent;
    }

    public Node popChild() {
        return children.remove(children.size() - 1);
    }

    public ArrayList<Node> getChildren() {
        return children;
    }

    public void execute(Environment env) {
        System.out.println("Executing program");
        for (Node child : children) {
            child.execute(env);
        }
    }

    public Boolean evaluateCondition(Environment env) {
        return false;
    }


    public String getValue() {
        System.out.println("No value for this node");

        return "";
    }

    ArrayList<Node> children = new ArrayList<Node>();
    Node parent;
}
