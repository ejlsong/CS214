//Eric Song
//112294760
//CSE214
//HW2
public class MinStack<T extends Comparable<T>> extends Stack<T> {


    Stack<T> stack;

    /**
     * Constructor for MinStack.
     * Creates a new Stack for stack.
     */
    public MinStack() {
        stack = new Stack<>(); //stack used to store min values
    }

    public T getMin() {
        return stack.peek();
    }



    @Override
    public void push(T element){
        if(list.isEmpty()){
            list.addFirst(element);
            stack.push(element); //push the element as default min
        }
        else{
            list.addFirst(element);
            if(element.compareTo(getMin()) < 0){
                stack.push(element); //if element is smaller than min, element is pushed onto the min stack
            }
        }


    }



    @Override
    public T pop(){
        if(list.isEmpty()){
            return null;
        }
        else if(length() == 1){
            T popped = list.pop();
            stack.pop();


            return popped;
        }
        else{
            T popped = list.pop();

            if(popped.compareTo(stack.peek()) == 0){
                stack.pop(); //if the popped element was the minimum, pop the value from the stack
            }
            return popped;

        }



    }


}