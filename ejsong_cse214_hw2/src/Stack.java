//Eric Song
//112294760
//CSE214
//HW2

import java.util.LinkedList;
import java.util.NoSuchElementException;

public class Stack<T> {



    protected LinkedList<T> list;
    T top;
    int length = 0;

    /**
     * Constructor for Stack.
     * Sets top to null and creates a new LinkedList for list.
     */
    public Stack() {
        top = null;
        list = new LinkedList<>();

    }

    /**
     * Pushes a specified element onto a stack.
     * The top is set to the element pushed and the element is added to the top of the stack.
     * Length is incremented by one.
     * @param element the generic type that will be pushed onto the stack
     */
    public void push(T element) {

            top = element;
            list.addFirst(element);
            length++;

    }

    /**
     * This method pops the top of the stack from the stack.
     * If the list is empty, the method returns null.
     * A generic type temp is created to reference the popped element to return.
     * If the list only has 1 element, the element at the top is popped and then the top is set to null.
     * Otherwise, the element at the top is popped and the top is set to the element that was under the previous top.
     * Length is decreased by one.
     * @return the element that has been popped from the stack
     * @throws NoSuchElementException
     */

    public T pop() throws NoSuchElementException{
        if(list == null){
            return null;
        }
        else if(length == 1){
            T temp = list.getFirst();
            list.remove(top);
            length--;
            top = null;

            return temp;
        }
        else {
            T temp = list.getFirst();
            list.remove(top);
            length--;
            top = list.getFirst();

            return temp;
        }


    }

    /**
     * This method returns the top of the stack.
     * The method returns the top element of the LinkedList that is associated with this stack.
     * @return the top of the stack
     */
    public T peek() {
        return list.getFirst();
    }

    /**
     * This method returns whether the stack is empty or not.
     * If the LinkedList that represents the elements of the stack is empty, the method returns true.
     * Otherwise, the method returns false.
     * @return true if the stack is empty, false if it is not empty
     */
    public boolean isEmpty() {
        if(list == null){
            return list.isEmpty();
        }
        return list.isEmpty();
    }

    /**
     * This method returns the length of the stack.
     * The method returns the instance variable of type int that stores the value for the size of the stack.
     * @return the length of the stack
     */
    public int length() {
        return length;
    }

}