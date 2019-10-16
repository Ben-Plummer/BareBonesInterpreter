enum ConditionType {
    NOT,
}

public class Condition extends Node {

    Condition(Node par, ConditionType condType) {
        super(par);
        condition = condType;
    }

    @Override
    public boolean isMaxChildren() {
        boolean isMaxChildren = false;
        if (children.size() >= 2) {
            isMaxChildren = true;
        }
        return isMaxChildren;
    }

    @Override
    public void display() {
        System.out.print("Condition(" + condition + ")");
    }

    @Override
    public Boolean evaluateCondition(Environment env) {
        Boolean outcome = false;
        switch(condition) {
        case NOT:
            if (env.symbols.get(children.get(0).getValue()) != Integer.valueOf(children.get(1).getValue())) {
                outcome = true;
            }
            break;
        }
        System.out.println("Evaluating condition");
        return outcome;
    }

    ConditionType condition;
}
