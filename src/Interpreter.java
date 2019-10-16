import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;




public class Interpreter {

    public static void evaluate(String path) {
        String contents = readSourceFile(path);

        List<Token> tokens = lexer(contents);

        Node program = new Node(null);
        Node currentNode = program;
        // Naive parser (assumes correct program for now)
        for (Token token : tokens) {
            token.display();

            Node newNode;
            switch(token.getType()) {
            case OPERATOR:
                OpType opType = null;
                switch (token.getValue()) {
                case "incr":  opType = OpType.INCREMENT; break;
                case "decr":  opType = OpType.DECREMENT; break;
                case "clear": opType = OpType.CLEAR;     break;
                }
                newNode = new Operator(currentNode, opType);
                currentNode.addChild(newNode);
                currentNode = newNode;
                break;
            case KEYWORD:
                KeywordType keyword = null;
                switch (token.getValue()) {
                case "while": keyword = KeywordType.WHILE; break;
                case "do":    keyword = KeywordType.DO;    break;
                case "end":   keyword = KeywordType.END;   break;
                }
                if (keyword == KeywordType.END) {
                    currentNode = currentNode.getParent().getParent();
                }
                newNode = new Keyword(currentNode, keyword);
                currentNode.addChild(newNode);
                currentNode = newNode;
                break;
            case CONDITION:
                ConditionType condition = null;
                switch(token.getValue()) {
                case "not": condition = ConditionType.NOT; break;
                }
                // Add current node as child of new node
                newNode = new Condition(currentNode, condition);
                newNode.addChild(currentNode.popChild());
                currentNode.addChild(newNode);

                currentNode = newNode;
                break;
            case LITERAL:
                int parsed = Integer.parseInt(token.getValue());
                newNode = new Literal(currentNode, parsed);
                currentNode.addChild(newNode);
                currentNode = newNode;
                break;
            case SYMBOL:
                newNode = new Symbol(currentNode, token.getValue());
                currentNode.addChild(newNode);
                currentNode = newNode;
                break;
            default:
                break;
            }

            while (currentNode.isMaxChildren()) {
                System.out.println("Finding node with space for children!");
                currentNode = currentNode.getParent();
            }
        }

        System.out.println("\n");
        program.printChildren("");

        System.out.println("\n");

        // Evaluator

        Environment env = new Environment();
        program.execute(env);

        env.display();
    }

    private static String readSourceFile(String path) {
        FileReader fileReader = null;
        Scanner scanner = null;

        try {
            fileReader = new FileReader(path);
            scanner = new Scanner(fileReader);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Could not find source path: " + path);
        }

        String contents = "";
        while (scanner.hasNextLine()) {
            contents += scanner.nextLine();
        }

        try {
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        scanner.close();

        return contents;
    }

    private static List<Token> lexer(String source) {
        List<String> words = Arrays.asList(source.split("\\s+|;"));
        words = words.stream().filter(s -> s.length() > 0).collect(Collectors.toList());

        List<Token> tokens = new ArrayList<Token>();

        for (String word : words) {

            if (OPERATORS.contains(word)) {
                tokens.add(new Token(TokenType.OPERATOR, word));
            }
            else if (KEYWORDS.contains(word)) {
                tokens.add(new Token(TokenType.KEYWORD, word));
            }
            else if(CONDITION.contains(word)) {
                tokens.add(new Token(TokenType.CONDITION, word));
            }
            else if (word.matches("[0-9]")) {
                tokens.add(new Token(TokenType.LITERAL, word));
            }
            else {
                tokens.add(new Token(TokenType.SYMBOL, word));
            }
        }

        return tokens;
    }



    private static final List<String> OPERATORS = Arrays.asList("incr", "decr", "clear");
    private static final List<String> KEYWORDS = Arrays.asList("end", "while", "do");
    private static final List<String> CONDITION = Arrays.asList("not");
}
