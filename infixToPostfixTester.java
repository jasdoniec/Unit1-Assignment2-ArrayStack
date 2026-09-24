import java.util.Scanner;

public class infixToPostfixTester{
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        String output;

        while (true){
            System.out.println("Put In an expression");
            output = scanner.nextLine();
            if (output == "exit")
                break;

            if (!validExpression.isValid(output)){
                System.out.println("The Expression is not a valid expression");
            }

            System.out.println("The postfix expression is:");
            System.out.println(InfixToPostfix.convertToPostfix(output));

            scanner.nextLine();
        }
    }
}
