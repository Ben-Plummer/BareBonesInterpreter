
public class Main {

    public static void main(String[] args) {
        if (args.length > 0) {
            Interpreter.evaluate(args[0]);
        }
        else {
            System.out.println("Please add a source file to interpret");
        }
    }
}
