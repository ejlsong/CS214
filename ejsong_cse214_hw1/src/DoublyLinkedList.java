//Eric Song
//112294760
//CSE214
//Professor Barron
//Homework 1

public class DoublyLinkedList<T extends Comparable<T>> implements LinkedList<T> {

    // proper formatting is important for automated testing of your code
    // Format example:
    // head=y tail=a\ny->z->a->null\na->z->y->null
    // When printed:
    // head=y tail=a
    // y->z->a->null
    // a->z->y->null


    Node<T> head;
    Node<T> tail;
    Node<T> cursor;

    /**
     * This constructs the DoublyLinkedList.
     * THe head, tail, and cursor are set to null.
     */
    public DoublyLinkedList() {
        head = null;
        tail = null;
        cursor = null;
    }

    /**
     * This method creates a new head for the Doubly Linked List.
     * The method creates a new node that will later be implemented as the new head of the Doubly Linked List.
     * The current head is set as the next link for the new node. The head is then changed to the new node.
     * If the Doubly Linked List was previously empty, then the new node will also be designated as the tail.
     *
     * @param h the generic type the newly created head created will have
     */
    public void addHead(T h) {

        Node<T> node2 = new Node(h);


        node2.setNext(head);
        head = node2;

        if (tail == null) {
            tail = head;
        }
        cursor = head;


    }

    /**
     * This method creates a new tail for the Doubly Linked List.
     * This method creates a new node that will later be set as the new tail of the Doubly Linked List.
     * The current tail is set as the previous node for the new node. The new node is then changed to the tail.
     * If the Doubly Linked List was previously empty, then the new node will also be designated as the head.
     *
     * @param t the generic type that the newly created tail will have
     */

    public void addTail(T t) {
        Node node2 = new Node(t);
        node2.setPrev(tail);

        tail = node2;
        if (head == null) {
            head = tail;
        }

        cursor = tail;

    }

    /**
     * This method deletes the current head of the Doubly Linked List.
     * If there is no head to remove, the method returns null.
     * A temporary node is set as the head in order to return the head's element at the end of the method.
     * The head is set to the next node that the about to be deleted head is linked to.
     * The newly set head will set its previous node, the original head, to null, effectively removing the head from the Doubly Linked List.
     * The element of the removed head is then returned.
     *
     * @return the element of the head that was deleted from the Doubly Linked List
     * @throws java.util.NoSuchElementException
     */

    public T removeHead() throws java.util.NoSuchElementException {
        if (head == null) {
            return null;
        }


        Node<T> temp = head;
        head = head.next;
        head.setPrev(null);


        return temp.getData();


    }

    /**
     * This method removes the current tail of the Doubly Linked List.
     * If there is nothing in the list, the method returns null.
     * A temporary node is set to the tail so that the tail's element may be returned at the end of the method.
     * The tail is changed to the previous node of the tail that is about to be removed.
     * The newly set tail will set its next node, the original tail, as null, effectively removing the tail from the Doubly Linked List.
     * The element of the removed tail is returned.
     *
     * @return the element of the tail that was deleted
     * @throws java.util.NoSuchElementException
     */

    public T removeTail() throws java.util.NoSuchElementException {
        if (tail == null) {
            return null;
        }


        Node<T> temp = tail;
        tail = tail.prev;
        tail.setNext(null);

        return temp.getData();

    }

    /**
     * This method returns the current head of the Doubly Linked List.
     *
     * @return the current head of the Doubly Linked List
     */

    public Node<T> getHead() {
        return head;

    }

    /**
     * This method changes the current head to a new head according to the node in the method parameter.
     *
     * @param head the node that will be set as the new head of the Doubly Linked List
     */

    public void setHead(Node<T> head) {
        this.head = head;

    }

    /**
     * This method returns the current tail of the Doubly Linked List.
     *
     * @return the current tail of the Doubly Linked List
     */

    public Node<T> getTail() {

        return tail;
    }

    /**
     * This method changes the current tail to a new tail according to the node in the method parameter.
     *
     * @param tail the node that will be set as the new tail of the Doubly Linked List
     */

    public void setTail(Node<T> tail) {
        this.tail = tail;
    }

    /**
     * This method returns the node reference of a specific index of the Doubly Linked List.
     * If the Doubly Linked List is empty, the method returns null.
     * The method creates a counter to keep track of which element the pointer is currently on.
     * If the counter is equal to the index, the method will return the node that the pointer is currently on.
     * If the counter is not equal to tne index yet, the pointer will keep moving through the Doubly Linked List.
     * If the index is never reached and the pointer becomes null, the method will return null.
     *
     * @param index the index of the element that is needed to be returned
     * @return the node at the specified index in the method parameter
     * @throws java.util.NoSuchElementException
     */

    public Node<T> get(int index) throws java.util.NoSuchElementException { //is ok

        Node<T> pointer = head;
        if (head == null) {
            cursor = null;
            return null;
        }
        int count = 0;
        while (pointer != null) {
            if (count == index) {
                return pointer;
            }
            count++;
            pointer = pointer.next;
        }


        return null;
    }

    /**
     * This method adds a new element to the Doubly Linked List at a specific index.
     * The pointer is set to the head of the Doubly Linked List, and a node named 'added' with the element that is to be added is created.
     * If the Doubly Linked LIst is empty, the head and tail are both set to the added node.
     * If the index added to is 0, links are established between the head and the added node, and then the added node is set as the head.
     * If the element at the index is null, implying the node will be added after the tail, links are established between the added node and the tail, and then the added node is set as the tail.
     * If added anywhere else, a for loop will be used to traverse the pointer through the Doubly Linked List.
     * Once the index is reached, the node the pointer is on and the added node will establish links and the added node will finally be added to the Doubly Linked LIst.
     *
     * @param index   the index at which the new element will be added to in the Doubly Linked List
     * @param element the element that will be added to the Doubly Linked List
     * @throws java.util.NoSuchElementException
     */

    public void add(int index, T element) throws java.util.NoSuchElementException {
        Node<T> pointer = this.head;
        Node<T> added = new Node<T>(element);


        if (head == null) {
            head = added;
            tail = added;
        } else if (index == 0) {
            added.next = head;
            head.prev = added;
            head = added;
        } else if (this.get(index) == null) {
            added.prev = tail;
            tail.next = added;
            tail = added;
        } else {
            for (int i = 0; i < index; i++) {
                pointer = pointer.next;
            }
            added.next = pointer.next;
            pointer.next = added;
            added.prev = pointer;
            added.next.prev = added;
        }


    }

    /**
     * This method removes an element from the Doubly Linked List given the specific index the element is at.
     * A pointer node is created at the head for traversing the Doubly Linked List. The node to be deleted is not specified yet.
     * If there is nothing in the Doubly Linked List, the method will return null.
     * If the index is 0, the current head is set as the deleted node and the node after the designated deleted node will become the new head.
     * The for loop traverse the pointer throughout the Doubly Linked List
     * If the pointer or its next link is null, the method returns null
     * The deleted node is set as the node next to the pointer.
     * The 'after' node is the node two nodes to the right of the pointer. It is then set as the next node from the pointer.
     * The deleted element is returned.
     *
     * @param index the index that specifies which element should be removed from the Doubly Linked List
     * @return the element that will be removed from the Doubly Linked List
     * @throws java.util.NoSuchElementException
     */
    public T remove(int index) throws java.util.NoSuchElementException {
        Node<T> pointer = head;
        Node<T> prev = null;
        Node<T> deleted = null;
        if (head == null) {
            return null;
        }
        if (index == 0) {
            deleted = head;
            head = pointer.next;

            return deleted.getData();
        }
        for (int i = 0; pointer != null && i < index - 1; i++) {
            pointer = pointer.next;
        }
        if (pointer == null || pointer.next == null) {
            return null;
        }
        deleted = pointer.next;
        Node after = pointer.next.next;
        pointer.setNext(after);
        after.setPrev(pointer);


        return deleted.getData();
    }

    /**
     * This method returns a clone of the Doubly Linked List performing it.
     * A new LinkedList is created to store the contents.
     * The pointer points to the tail; the method will be working backwards.
     * The head of the newly cloned list is set to the original head.
     * New heads are set as the pointer traverses backwards through the original list, adding elements to the front of the cloned list.
     * The newly cloned list's head and tail are set and is returned.
     *
     * @return the clone of the original Doubly Linked List
     */
    public LinkedList<T> clone() {

        LinkedList<T> x = new DoublyLinkedList<>();
        Node<T> pointer = tail;
        x.setHead(this.head);
        while (pointer != null) {
            x.setHead(pointer);
            pointer = pointer.prev;

        }
        x.setHead(head);
        x.setTail(tail);


        return x;
    }

    /**
     * This method combines two LinkedLists together, with the tail of one attached to the head of the other.
     * The second list is concatenated with the first list, turning them into one list.
     * The list's head and tail are set, and the head and tail of the list being combined is set to null.
     *
     * @param list the list that will be combined with the list performing the method
     */

    public void concatenate(LinkedList<T> list) {

        this.tail.setNext(list.getHead());
        list.getHead().setPrev(tail);
        tail = list.getTail();
        list.setTail(null);
        list.setHead(null);


    }

    /**
     * This method removes all elements greater than a specified element from a Doubly Linked List.
     * If the list is empty, the method does nothing. There is nothing to filter.
     * The pointer is used to travers through the Doubly Linked List.
     * If the head's element is larger than the target, the head is effectively removed.
     * If the tail's element is larger than the target, the tail is effectively removed.
     * Otherwise, nodes with elements larger than the target that are not the head or the tail have their connections servered with their previous and next nodes.
     *
     * @param target elements larger the target will be removed from the Doubly Linked List
     */

    public void filter(T target) {
        Node<T> pointer = head;

        if (head == null) {
            return;
        }


        while (pointer.next != null) {
            if (pointer.getData().compareTo(target) > 0 && pointer == head) {
                this.setHead(head.next);
                head.prev = null;
            } else if (pointer.next.getData().compareTo(target) > 0) {
                pointer.setNext(pointer.next.next);
                pointer.next.setPrev(pointer);

            } else if (pointer.getData().compareTo(target) > 0 && pointer == tail) {
                this.setTail(tail.prev);
            }
            pointer = pointer.next;


        }


    }

    /**
     * This method inserts an element in a Doubly Linked List that is already sorted.
     * The element is inserted after an element is is greater than.
     * A pointer is used to traverse through the list.
     * If the list is empty, the element is inserted and is the head and the tail.
     * If the head's element is greater than the target, the target is inserted in front of the head and is made the new head.
     * If the target is the greatest element, it is made the new tail.
     * Otherwise, the element is inserted in the middle of the list as normal.
     *
     * @param element the element that is to be inserted into the Doubly Linked List
     */
    public void sortedAdd(T element) {
        Node<T> newNode = new Node<T>(element);
        Node<T> pointer = head;
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else if (head.getData().compareTo(newNode.getData()) >= 0) {
            newNode.next = head;
            newNode.next.prev = newNode;
            head = newNode;
        } else {
            while (pointer.next != null && pointer.next.getData().compareTo(newNode.getData()) < 0) {
                pointer = pointer.next;
            }
            newNode.next = pointer.next;
            if (pointer.next != null) {
                newNode.next.prev = newNode;
            }
            pointer.next = newNode;
            newNode.prev = pointer;

        }
        if (newNode.next == null) {
            tail = newNode;
        }

        System.out.println(element + " successfully added.");

    }

    /**
     * This method sorts a LinkedList in ascending order.
     * A new DoublyLinkedList is created to hold the elements.
     * A pointer is set to the head.
     * If working as intended, the sortedAdd method would repeatedly input elements in the appropriate spot until the pointer indicates that there are no more elements to sort.
     * The sorted LinkedList is the returned.
     *
     * @return the sorted LinkedList
     */

    public LinkedList<T> sort() { //fix
        DoublyLinkedList<T> x = new DoublyLinkedList<T>();
        Node<T> pointer = head;
        x.addHead(pointer.getData());
        while (pointer != null) {
            x.sortedAdd(pointer.getData());
            pointer = pointer.next;
        }


        return x;

    }

    /**
     * This method removes all the duplicates in a Doubly Linked List.
     * A pointer is used to traverse through the Doubly Linked List.
     * If the element of the pointer is equal to the element of the next node, the next node's connection is severed from the list.
     * Otherwise, the pointer continues to move on.
     */
    public void removeDuplicates() {
        Node<T> pointer = head;
        while (pointer.next != null) {
            if (pointer.getData().compareTo(pointer.next.getData()) == 0) {
                pointer.setNext(pointer.next.next);
                pointer.next.setPrev(pointer);
            } else {
                pointer = pointer.next;
            }
        }

    }

    /**
     * This method merges two sorted Linked Lists.
     * A new Doubly Linked List is created to hold the contents.
     * The integer k is used to add new elements to the combined list.
     * While both lists still have elements to contribute, the if loops check the elements if the are greater or less than each other.
     * New elements are added to the tail of the combined list.
     * If one list runs out of elements, the one of the other while loops will append the rest of the remaining list's elements.
     * The list with both of the two lists' elements is then returned.
     *
     * @param list the list that will be merged with the list performing the method
     * @return the combined list with both lists' elements
     */

    public LinkedList<T> merge(LinkedList<T> list) { //mostly works
        DoublyLinkedList x = new DoublyLinkedList<T>();
        int k = 0;

        while (this.head != null && list.getHead() != null) {
            if (head.getData().compareTo(list.getHead().getData()) < 0) {
                x.add(k, head.getData());


                head = head.next;
            } else {
                x.add(k, list.getHead().getData());

                list.setHead(list.getHead().getNext());
            }
            k++;
        }

        while (head != null) {
            x.add(k, head.getData());
            head = head.next;
            k++;
        }
        while (list.getHead() != null) {
            x.add(k, list.getHead().getData());
            list.setHead(list.getHead().getNext());
            k++;
        }


        return x;
    }

    /**
     * This method tells the head and tail of the Linked List and returns the list and its reverse in order to check if its links are correct.
     * A pointer is used to traverse the list, and an empty string is created to append the list's contents.
     * After the pointer goes one way, the string empties again and goes to a new line.
     * The pointer then goes the other way, appending the list's elements in reverse order.
     * @return the Linked List and the reverse of the Linked List
     */
    public String toString() {
        String s = "";

        s += "head=" + (head == null ? "null" : head.getData()) + " ";
        s += "tail=" + (tail == null ? "null" : tail.getData());
        s += "\n";
        Node<T> nodePtr = head;
        while (nodePtr != null) {
            s += nodePtr.getData() + "->";
            nodePtr = nodePtr.getNext();
        }
        s += "null";
        s += "\n";
        nodePtr = tail;
        while (nodePtr != null) {
            s += nodePtr.getData() + "->";
            nodePtr = nodePtr.getPrev();
        }
        s += "null";
        return s;
    }

    public static void main(String[] args) {
        LinkedList<Integer> myList = new DoublyLinkedList<>();
        LinkedList<Integer> list2 = new DoublyLinkedList<>();
        //test addHead() and add(): works
        myList.addHead(1);
        //System.out.println(myList.toString());
        myList.add(1, 2);
        myList.add(2, 3);
        myList.add(3, 3);
        myList.add(4, 4);
        myList.add(5, 4);
        myList.add(6, 5);
        //myList.add(7, 6);

        //test remove(): works
        //myList.remove(3);
        //System.out.println(myList.toString());

        //System.out.println(myList.toString());


        //test sorts: fix
        //concatenate: works
        list2.add(1, 2);
        list2.add(2, 6);
        list2.add(3, 4);
        list2.add(4, 2);
        //list2.add(5, 6);
        //list2.add(6, 4);
        //list2.add(7, 8);
        //list2.add(8, 8);
        //myList.concatenate(list2);
        //System.out.println(myList.toString());

        //test removeDuplicates: works
        //myList.removeDuplicates();
        //System.out.println(myList.toString());


        //Test clone(): works
        //System.out.println("Cloned List: ");
        //System.out.println(myList.clone().toString());

        //Test filter(): works
        //myList.filter(4);
        //System.out.println(myList.toString());

        //Test sortedAdd(): works
        //myList.sortedAdd(3);
        //System.out.println(myList.toString());

        //Test sort(): fix
        list2.sort();
        System.out.println(list2.toString());
        //Test merge(): 95% works, except for 1 number at end
        //System.out.println(myList.merge(list2).toString());
    }

}