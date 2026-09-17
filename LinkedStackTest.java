
/**
 * This class tests the LinkedStack class
 *
 * @author Mr. G
 * @version August 22, 2017
 */
public class LinkedStackTest extends StackTest
{
    public static void main(String [] args)
    {
        testInterface();
        if(testStack("LinkedStack"))
            System.out.println("Fantabulous!  You have completed the LinkedStack assignment");
        else
            System.out.println("\nBummer, you have not finished the LinkedStack assignment.  Try again.");
    }

}