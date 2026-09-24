/*
operand: concantenate
(:push on stack
):pop from stuck until (
operator: pop and concantenate all operators that have a higher or equal precedent and push operator on top
*/

import java.util.HashMap;
import java.util.Map;

public class InfixToPostfix{

    private static final Map<Character, Integer> operators;

    // 2. Initialize it inside a static block
    static {
        Map<Character, Integer> tempMap = new HashMap<>(); // Temp map to hold values
        tempMap.put('+', 0);
        tempMap.put('-', 0);
        tempMap.put('*', 1);
        tempMap.put('/', 1);
        tempMap.put('%', 1);
        tempMap.put('^', 2);

        operators = tempMap;
    }

    

    public static String convertToPostfix(String expression){
        StackInterface<Character> stack = new LinkedStack<Character>();
        StringBuilder output = new StringBuilder();
        char[] array = expression.toCharArray();

        boolean newop = true;

        for (char c : array){
            if (c == ' ' || c == '\t')
                continue;

            if (c == '('){
                if (!newop){
                    output.append(" ");
                    stack.push('*');
                }
               stack.push(c);
            }

            else if (c == ')'){
                while (true){
                    char newchar = stack.pop();
                    if (newchar == '(')
                        break;
                    output.append(" ");
                    output.append(newchar);
                }
            }

            else if (operators.containsKey(c)){
                newop = true;
                int precedent = operators.get(c);
                while(!stack.empty() && precedent != 2 && operators.containsKey(stack.peek()) && operators.get(stack.peek())>=precedent){
                    output.append(" ");
                    output.append(stack.pop());
                }
                stack.push(c);
            }
            else{
                if (newop && output.length() != 0){
                    output.append(" ");
                    newop = false;
                }
                output.append(c);
            }
       }

        while(!stack.empty()){
            output.append(" ");
            output.append(stack.pop());
        }

        return output.toString();
   }
}
