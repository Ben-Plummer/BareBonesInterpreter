enum KeywordType {
    WHILE,
    DO,
    END,
}

public class Keyword extends Node {

    Keyword(Node parent, KeywordType type) {
        super(parent);
        keyword = type;
    }

    @Override
    public boolean isMaxChildren() {
        boolean isMaxChildren = false;

        int maxChildren;
        switch(keyword) {
        case WHILE: maxChildren = 2; break;
        default:    maxChildren = 0; break;
        }
        if (keyword == KeywordType.DO) {
            return false;
        }
        else if (keyword == KeywordType.END) {
            return true;
        }
        if (children.size() >= maxChildren) {
            isMaxChildren = true;
        }
        return isMaxChildren;
    }

    @Override
    public void display() {
        System.out.print("Keyword(" + keyword + ")");
    }

    @Override
    public void execute(Environment env) {

        display();

        switch(keyword) {
        case WHILE:
            Node condition = children.get(0);
            Node doBlock = children.get(1);

            for (Node doChild : doBlock.getChildren()) {
                doChild.display();
            }

            System.out.println("WHILE called");
            while (condition.evaluateCondition(env) == true) {
                System.out.println("Condition true!");
                for (Node doChild : doBlock.getChildren()) {
                    doChild.execute(env);
                }
            }
            System.out.println("WHILE finished");

            break;
        case DO:
            for (Node child : children) {
                child.execute();
            }
            System.out.println("Entering DO block");
            break;
        case END:
            System.out.println("END");
            break;
        }

    }


    KeywordType keyword;
}
