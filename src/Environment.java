import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Environment {
    public HashMap<String, Integer> symbols = new HashMap<String, Integer>();

    public void display() {
        Iterator<?> it = symbols.entrySet().iterator();

        while(it.hasNext()) {
            Map.Entry<String, Integer> pair = (Map.Entry<String, Integer>)it.next();

            System.out.println(pair.getKey() + " = " + pair.getValue());
            it.remove();
        }
    }
}
