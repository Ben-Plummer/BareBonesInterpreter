
enum TokenType {
        OPERATOR,
        KEYWORD,
        CONDITION,
        LITERAL,
        SYMBOL,
        UNDEFINED,
    }

public class Token {

    private TokenType type;
    private String value = "undetermined";

    Token(TokenType t, String v) {
        type = t;
        value = v;
    }

    public void display() {
        System.out.println("(" + type + ", " + value + ")");
    }

    public TokenType getType() {
        return type;
    }

    public String getValue() {
        return value;
    }


}
