import java.util.Scanner;
public class validExpression{

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true){
            String input = scanner.nextLine();
            if (input.toLowerCase() == "q")
                break;

            if(isValid(input)){
                System.out.println("The Expression is correct");
            }else{
                System.out.println("The Expression is incorrect");
            }
        }
    }

    public static boolean isValid(String expression){
        char[] array = expression.toCharArray();
        StackInterface<Character> stack = new ArrayStack<Character>();

        for (int i = 0; i < array.length; i++){
            if (isOpen(array[i])){
                stack.push(array[i]);
            }
            if (isClose(array[i])){
                if (stack.empty())
                    return false; 
                if (!match(stack.pop(),array[i])){
                    return false;
                }
            }
        }

        return stack.empty();
    }

    private static boolean isOpen(char c){
        return c=='{'||c=='['||c=='(';
    }

    private static boolean isClose(char c){
        return c=='}'||c==']'||c==')';
    }

    private static boolean match(char a, char b){
        return (a=='{'&&b=='}')||(a=='['&&b==']')||(a=='('&&b==')');
    }
}