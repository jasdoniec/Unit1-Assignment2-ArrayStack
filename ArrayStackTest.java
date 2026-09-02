
/**
 * This class tests the ArrayStack class
 *
 * @author Mr. G
 * @version August 22, 2017
 */
public class ArrayStackTest extends StackTest
{
    public static void main(String [] args)
    {
        testInterface();
        if(testStack("ArrayStack"))
            System.out.println("Stupendous!  You have completed the ArrayStack assignment");
        else
            System.out.println("\nBummer, you have not finished the ArrayStack assignment.  Try again.");
    }

}
