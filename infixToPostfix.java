/*
operand: concantenate
(:push on stack
):pop from stuck until (
operator: pop and concantenate all operators that have a higher or equal precedent and push operator on top
*/

import java.util.HashMap;
import java.util.Map;

public class infixToPostfix{

    private static final Map<Character, Integer> operators;

    // 2. Initialize it inside a static block
    static {
        Map<Character, Integer> tempMap = new HashMap<>(); // Temp map to hold values
        tempMap.put('+', 0);
        tempMap.put('-', 0);
        tempMap.put('*', 1);
        tempMap.put('/', 1);
        tempMap.put('^', 2);

        operators = tempMap;
    }

    

    public static String switchInfixToPostfix(String expression){
        StackInterface<Character> stack = new LinkedStack<Character>();
        StringBuilder output = new StringBuilder();
        char[] array = expression.toCharArray();

        for (char c : array){
            if (operators.contains(c))
       }

        return output.toString();
   }
}