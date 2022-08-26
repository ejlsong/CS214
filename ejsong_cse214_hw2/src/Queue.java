//Eric Song
//112294760
//CSE214
//HW2

import java.util.LinkedList;

public class Queue<T> {

//fix stuff

    protected LinkedList<T> list;
    T front;
    T end;
    int length = 0;

    /**
     * Constructor for Queue.
     * Sets the front and end to null and creates a new LinkedList for list.
     */
    public Queue() {
        front = null;
        end = null;
        list = new LinkedList<>();
    }

    /**
     * This method puts a specified element at the end of a Queue.
     * If the Queue is empty, the front and end of the list is set as the element and is added to the Queue.
     * Otherwise, the element is added to the end of the Queue, and the front and end are the first and last elements of the Queue.
     * Length is incremented by one.
     * @param element the element that is being enqueued onto the queue
     */

    public void enqueue(T element) {
        if(list == null){
            list.addFirst(element);
            front = list.getFirst();
            end = list.getFirst();
            length++;
        }
        else{
            list.addLast(element);
            front = list.getFirst();
            end = list.getLast();
            length++;
        }
    }

    /**
     * This method removes the front of the Queue and returns it.
     * If the Queue is empty, the method returns null.
     * If the Queue only has one element in it, the element is dequeued and both the front and end are set to null.
     * Length decreases by 1.
     * The dequeued element is returned.
     * @return the element that is removed from the Queue
     */
    public T dequeue() {   //fix
        if(list == null){
            return null;
        }
        else if(length == 1){
            T dequeued = list.getLast();
            list.remove(front);
            length--;
            front = null;
            end = null;

            return dequeued;
        }
        else {
            T dequeued = list.getFirst();
            list.remove(front);
            front = list.getFirst();
            length--;



            return dequeued;
        }



    }

    /**
     * This method determines if the Queue is empty or not.
     * The method returns true if the LinkedList associated with the Queue has no elements.
     * Otherwise, the method returns true.
     * @return if the Queue is empty or not
     */

    public boolean isEmpty() {
        if(list.isEmpty() == true){
            return true;
        }
        return false;
    }

    /**
     * This method returns the front of the Queue, which is the element that is next to be dequeued.
     * @return the front of the Queue
     */
    public T peek() {
        return front;
    }

    /**
     * This method returns the value of the instance variable that stores the length of the Queue.
     * @return the length of the queue
     */
    public int length() {
        return length;
    }

}