//Eric Song
//112294760
//CSE214
//Professor Barron
//Homework 1
public class Node<T> {
    T data;
    Node<T> next;
    Node<T> prev;


    public Node(T d) {
        data = d;
        next = null;
        prev = null;
    }

    public T getData() {
        return data;
    }

    public void setData(T ndata) {
        data = ndata;
    }

    public Node<T> getNext() {
        return next;
    }

    public void setNext(Node newLink) {
        next = newLink;
    }

    public Node<T> getPrev() {
        return prev;
    }

    public void setPrev(Node newLink) {
        prev = newLink;
    }


}