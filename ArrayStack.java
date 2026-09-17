
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
        size = 0;
    }

    // returns the logical size of the stack
    public int size()    
    {
        return size;
    }

    // tests if this stack is empty
    public boolean empty()
    {
        return size == 0;
    }

    private void isFull(){
        if (size == stack.length)
            expandArray();
    }

    private void isFourth(){
        if (stack.length/4 <= size){
            T[] newStack = (T[])new Object[size / 2];
        for (int i = 0; i < size; i ++){
            newStack[i] = stack[i];
        }

        stack = newStack;
        }
    }

    // looks at the object at the top of this stack
    // without removing it from the stack
    public T peek()
    {
        if (empty())
            throw new StackUnderflowException();


        return stack[size-1];
    }

    // removes the object at the top of this stack 
    // and returns that object as the value of this function
    public T pop()
    {
        if (empty())
            throw new StackUnderflowException();

        //if (!empty())
            //isFourth();

        return stack[--size];
    }

    // pushes an item onto the top of this stack
    public T push(T item)
    {

        isFull();

        stack [size] = item;

        size ++;

        return item;
    }

    private void expandArray(){
        T[] newStack = (T[])new Object[size * 2];
        for (int i = 0; i < size; i ++){
            newStack[i] = stack[i];
        }

        stack = newStack;
    }

    // removes all of the elements from this stack
    public void clear()
    {
        size = 0;
    }

    // returns the 1 based position where an object is on this stack
    // note: when the method ends, the stack is the same as it was at the start
    public int search(Object o)
    {
        for (int i = 0; i < size; i++){
            if (o.equals(stack[size - 1 - i])){
                return i+1;
            }
        }
        return -1;
    }
}
