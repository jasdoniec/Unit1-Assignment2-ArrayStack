import java.util.Scanner;

public class PostfixEvaluator {
    public static int evaluate(String operation) throws PostfixException {
        LinkedStack<Integer> stack = new LinkedStack<>();

        Scanner scanner = new Scanner(operation);

        while (scanner.hasNext()) {
            if (scanner.hasNextInt()) {
                stack.push(scanner.nextInt());
                continue;
            }
            try{
            stack.push(calculate(stack.pop(), stack.pop(), scanner.next()));
            }catch (Exception e){
                throw new PostfixException("Stack Underflow");
            }
        }

        if (stack.size() != 1) {
            throw new PostfixException("Invalid Expression");
        }

        return stack.pop();
    }

    private static int calculate(int p1, int p2, String op) throws PostfixException {
        switch (op) {
            case "+":
                return p2 + p1;
            case "-":
                return p2 - p1;
            case "*":
                return p2 * p1;
            case "/":
                if (p1 == 0){
                    throw new PostfixException("division by 0");
                }
                return p2 / p1;
            case "%":
                if (p1 == 0){
                    throw new PostfixException("modulo by 0");
                }
                return p2 % p1;
            case "^":
                return (int) Math.pow(p2, p1);
        }
        throw new PostfixException("Invalid argument");
    }
}