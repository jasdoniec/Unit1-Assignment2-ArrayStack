
/**
 * This ArrayStack object represents a Stack ADT implemented as
 * an array using the StackInterface
 * 
 * @author  
 * @version 
 */
public class ArrayStack<T> implements StackInterface<T>
{
    private int size;
    private T[] stack;

    public ArrayStack()
    {
        // cannot create a generic array object, so has to be cast
        // from an Object back into the generic in order to compile
        stack = (T[]) new Object[1];
    }

    // returns the logical size of the stack
    public int size()    
    {
        return -1;
    }

    // tests if this stack is empty
    public boolean empty()
    {
        return false;
    }

    // looks at the object at the top of this stack
    // without removing it from the stack
    public T peek()
    {
        return null;
    }

    // removes the object at the top of this stack 
    // and returns that object as the value of this function
    public T pop()
    {
        return null;
    }

    // pushes an item onto the top of this stack
    public T push(T item)
    {
        return null;
    }

    // removes all of the elements from this stack
    public void clear()
    {
    }

    // returns the 1 based position where an object is on this stack
    // note: when the method ends, the stack is the same as it was at the start
    public int search(Object o)
    {
        return -2;
    }
}
