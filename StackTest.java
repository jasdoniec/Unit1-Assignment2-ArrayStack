import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * This class tests contains the common methods that will test
 * either implementation of the the Stack class
 * 
 * @author Mr. G
 * @version August 22, 2017
 */

public abstract class StackTest<T>
{
    private static Boolean failed = false;
    private static ArrayList<String> methodNames = new ArrayList<String>();
    private static StackInterface<Integer> stack;
    private static String message;
    private static String methodName;
    private static Field fieldLog, fieldName, fieldSize;
    private static Class<?> c;
    private static Integer returnData;    
    private static String version = "3.0";  // 8/14/19

    protected static boolean testInterface()
    {
        // make sure that StackInterface<T> is untouched
        try 
        {
            c = Class.forName("StackInterface");
            Member[] methods = c.getMethods();
            for (Member method : methods)
                methodNames.add(((Method)method).toGenericString());
        }
        catch(ClassNotFoundException e)
        {
            failure("Epic Failure: missing interface StackInterface");
            return false;
        }

        methodName = "public abstract void StackInterface.clear()";
        if(!methodNames.contains(methodName))
            failure("Missing interface method: " + methodName);

        methodName = "public abstract int StackInterface.size()";
        if(!methodNames.contains(methodName))
            failure("Missing interface method: " + methodName);

        methodName = "public abstract T StackInterface.pop() throws StackUnderflowException";
        if(!methodNames.contains(methodName))
            failure("Missing interface method: " + methodName);

        methodName = "public abstract T StackInterface.push(T)";
        if(!methodNames.contains(methodName))
            failure("Missing interface method: " + methodName);

        methodName = "public abstract T StackInterface.peek() throws StackUnderflowException";
        if(!methodNames.contains(methodName))
            failure("Missing interface method: " + methodName);

        methodName = "public abstract boolean StackInterface.empty()";
        if(!methodNames.contains(methodName))
            failure("Missing interface method: " + methodName);

        methodName = "public abstract int StackInterface.search(java.lang.Object)";
        if(!methodNames.contains(methodName))
            failure("Missing interface method: " + methodName);

        if(failed)
        {
            System.out.println("\nCorrupted interface StackInterface");
        }

        return !failed;
    }

    protected static boolean testStack(String type)
    {
        //********** Generic Stack Class Test **************************************
        System.out.println("-> " + type + " project tester version " + version + " <-\n");
        try 
        {
            if(type.equals("ArrayStack"))
                stack = new ArrayStack<Integer>();
            else
                stack = new LinkedStack<Integer>();
            c = Class.forName(type);
        }
        catch(ClassNotFoundException e)
        {
            failure("Epic Failure: missing " + type + " class");
            return false;
        }
        catch(NoClassDefFoundError e)
        {
            failure("Epic Failure: missing " + type + " class");
            return false;
        }
        catch (NoSuchMethodError e)
        {
            failure("missing constructor " + type + "()");
            return false;
        }        

        //make sure that ArrayStack implements StackInterface
        if(!(stack instanceof StackInterface))
        {
            failure("" + type + " does not implement StackInterface");
            return false;
        }
        display(type + " implements StackInterface test");

        boolean properInstanceVariable = false;
        Field[] fields = c.getDeclaredFields();
        for(Field field : fields)
        {
            if(!failed && field.getModifiers() != 2)
            {
                failure(type + " instance variables must be private");
                return false;
            }
            // check of ArrayStack has an instance array of Objects
            if(type.equals("ArrayStack") && field.getType().toString().equals("class [Ljava.lang.Object;"))
            {
                display(type + " has an instance array of Objects");                
                properInstanceVariable = true;
            }
            // check if LinkedStack has an LLNode instance variable
            if(type.equals("LinkedStack") && field.getType().toString().equals("class LLNode"))
            {
                display(type + " has an LLNode instance variable");                
                properInstanceVariable = true;
            }
        }
        if(!properInstanceVariable)
        {
            failure(type + " does not have the required instance variable type");               
            return false;
        }

        //validate size method
        if (stack.size() != 0)
        {
            failure("size variable was not initialized to zero");
            return false;
        }

        //validate empty method
        if (!stack.empty())
        {
            failure("incorrect empty method");
            return false;
        }

        // test push(element) method
        try 
        {
            returnData = stack.push(new Integer(1));
            if(returnData == null || returnData.intValue() != 1)
            {
                failure("push(element) method is not correct");
                return false;
            }
        }
        catch (ArrayIndexOutOfBoundsException e) 
        {
            failure("push(element) method throws an ArrayIndexOutOfBoundsException");
            return false;
        }

        // validate size method
        if (stack.size() == 0)
        {
            failure("push method does not increment size variable");
            return false;
        }
        if (stack.size() != 1)
        {
            failure("push method does not properly increment size variable");
            return false;
        }

        //validate empty method
        if (stack.empty())
        {
            failure("incorrect empty method");
            return false;
        }

        // test peek method
        try 
        {
            returnData = stack.peek();
            if(returnData == null || returnData.intValue() != 1)
            {
                failure("peek method is not correct");
                return false;
            }
        }
        catch (ArrayIndexOutOfBoundsException e)
        {
            failure("peek method throws ArrayIndexOutOfBoundsException");
            return false;
        }

        if (stack.size() != 1)
        {
            failure("peek method should not change size");
            return false;
        }

        // test pop method
        try 
        {
            returnData = stack.pop();
            if(returnData == null || returnData.intValue() != 1)
            {
                failure("pop method is not correct");
                return false;
            }
        }
        catch (ArrayIndexOutOfBoundsException e)
        {
            failure("pop method throws ArrayIndexOutOfBoundsException");
            return false;

        }

        // validate size method
        if (stack.size() == 1)
        {
            failure("pop method does not decrement size variable");
            return false;
        }

        if (stack.size() != 0)
        {
            failure("pop method does not properly decrement size variable");
            return false;
        }

        //validate empty method
        if (!stack.empty())
        {
            failure("incorrect empty method");
            return false;
        }

        //validate pop throwing StackUnderflowException
        try
        {
            if(stack.pop() != null)
            {
                failure("pop method is not correct");
                return false;
            }
        }
        catch (StackUnderflowException e)
        {
            display("pop method correctly throws StackUnderflowException");
        }
        catch (ArrayIndexOutOfBoundsException | NullPointerException e)
        {
            failure("pop method does not correctly throw StackUnderflowException");
            return false;
        }

        //validate peek throwing StackUnderflowException
        try
        {
            if(stack.peek() != null)
            {
                failure("peek method is not correct");
                return false;
            }
        }
        catch (StackUnderflowException e)
        {
            display("peek method correctly throws StackUnderflowException");
        }
        catch (ArrayIndexOutOfBoundsException | NullPointerException e)
        {
            failure("peek method does not correctly throw StackUnderflowException");
            return false;
        }

        for(int i = 10; i <= 1000; i += 10)
        {
            try
            {
                stack.push(new Integer(i));
            }
            catch (ArrayIndexOutOfBoundsException e)
            {
                failure("array does not dynamically expand");
                break;
            }
        }
        if (failed)
            return false;

        // validate size method
        if (stack.size() != 100)
        {
            failure("incorrect size value");
            return false;
        }

        //validate empty method
        if (stack.empty())
        {
            failure("incorrect empty method");
            return false;
        }

        int x = stack.peek().intValue();
        if(x != 1000)
            failure("push and/or peek methods are incorrect");

        while(x > 10)
        {
            if(x != stack.pop().intValue())
                failure("push and/or pop methods are incorrect");
            x -= 10;
            if(x != stack.peek().intValue())
                failure("push and/or peek methods are incorrect");
        }

        // validate size method
        if (stack.size() != 1)
        {
            failure("incorrect size value");
            return false;
        }

        //validate empty method
        if (stack.empty())
        {
            failure("incorrect empty method");
            return false;
        }

        // test peek method
        if(stack.peek().intValue() != 10)
            failure("peek method is not correct");

        // test pop method
        if(stack.pop().intValue() != 10)
            failure("pop method is not correct");

        // validate size method
        if (stack.size() != 0)
        {
            failure("incorrect size value");
            return false;
        }

        //validate empty method
        if (!stack.empty())
        {
            failure("incorrect empty method");
            return false;
        }

        //validate pop throwing StackUnderflowException
        try
        {
            if(stack.pop() != null)
                failure("pop method is not correct");
        }
        catch (StackUnderflowException e)
        {
            display("pop method again correctly throws StackUnderflowException");
        }
        catch (ArrayIndexOutOfBoundsException e)
        {
            failure("pop method does not correctly throw StackUnderflowException");
        }

        //validate peek throwing StackUnderflowException
        try
        {
            if(stack.peek() != null)
                failure("peek method is not correct");
        }
        catch (StackUnderflowException e)
        {
            display("peek method again correctly throws StackUnderflowException");
        }
        catch (ArrayIndexOutOfBoundsException e)
        {
            failure("peek method does not correctly throw StackUnderflowException");
        }

        if(!failed)
        {
            display("size method is correct");
            display("empty method is correct");
            display("push method is correct");
            display("peek method is correct");
            display("pop method is correct");
        }

        for(int i = 10; i > 0; i--)
        {
            stack.push(new Integer(i));
        }

        if(stack.search(new Integer(3)) != 3)
        {
            failure("search method is incorrect");
            return false;
        }

        if(stack.search(new Integer(8)) != 8)
            failure("search method is incorrect");

        if(stack.search(new Integer(1)) != 1)
            failure("search method is incorrect");

        if(stack.search(new Integer(10)) != 10)
            failure("search method is incorrect");

        if(stack.search(new Integer(12)) != -1)
            failure("search method is incorrect");

        for(int i = 1; i <= 10; i++)
        {
            if (stack.pop().intValue() != i)
            {
                failure("search method did not restore stack to original state");
                break;
            }
        }
        if(!failed)
            display("search method is correct");

        for(int i = 0; i < 500; i++)
            stack.push(new Integer(i));

        stack.clear();
        
        //validate pop throwing StackUnderflowException
        try
        {
            if(stack.pop() != null)
            {
                failure("clear method is incorrect");
                return false;
            }
        }
        catch (StackUnderflowException e)
        {
        }
        
        // validate size method
        if (stack.size() != 0)
        {
            failure("clear method does not reset size value");
            return false;
        }

        //validate empty method
        if (!stack.empty())
        {
            failure("incorrect empty method");
            return false;
        }

        if(!failed)
            display("clear method is correct");

        if(!failed)
            System.out.println("Congratulations!  Your " + type + " class is correct\n");
        return !failed;
    }

    private static void failure(String str)
    {
        System.out.println("*** Failed: " + str);
        failed = true;
    }

    private static void display(String str)
    {
        System.out.println("Passed: " + str);
    }
}