//Eric Song
//112294760
//CSE214
//Homework 5

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Stack;
import java.util.ListIterator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

class Graph<T> {
    //instance variables
    int capacity;
    HashMap<T, Vertex> adjList;
    int numVertices;
    int numEdges;

    /**
     * The no argument constructor for the Graph class.
     * The constructor sets all of the instance variables of type int to 0.
     * Creates a new HashMap to store the graph's vertices and the vertices' neighbors.
     */
    public Graph() {
        numVertices = 0;
        numEdges = 0;
        capacity = 0;
        adjList = new HashMap<T, Vertex>();
    }


    /**
     * The single argument constructor for the Graph class.
     * It does the same thing as the no argument constructor, except it will also read a .gv or .dot file to create the graph based on its contents.
     * A scanner is created in order to read the file's contents. the nextLine() file is used to skip the first line, which does not contain any graph data.
     * The loop runs while the file still has a next line and the next line isn't the closing bracket.
     * The line currently being read is trimmed of white spaces and is split by the String "->".
     * The resulting array from the split should only contain two elements. An edge is created from the first element of the array to the second element.
     * Repeat until the Scanner reaches the closing bracket.
     * @param filename the name of the file that will be read to create the graph
     * @throws FileNotFoundException throws an exception of the specified file does not exist
     */
    public Graph(String filename) throws FileNotFoundException {
        numVertices = 0;
        numEdges = 0;
        capacity = 0;
        adjList = new HashMap<T, Vertex>();

        Scanner scan = new Scanner(new File(filename));   //read the file
        scan.nextLine();   // skip digraph{
        while (scan.hasNextLine() && !scan.nextLine().equals("}")) {   //don't read }
            String line = scan.nextLine();
            line = line.trim();   //trim spaces
            String[] split = line.split("->");   //get labels
            //add to graph
            addEdge((T) split[0], (T) split[1]);   //cast to type T
        }

    }

    /**
     * This method can create new vertices and adds a new edge to the graph.
     * The method will check if the elements of value "x" or "y" are already in the HashMap.
     * If "x" or "y" are not in the HashMap, the method will add vertices with labels "x" or "y" along with a new neighbor list for the newly created vertices.
     * For each new vertex added, the vertex count and capacity increases by 1.
     * "y" is added to the neighbor list of "x" and the edge count is increased by 1.
     * @param x the vertex that the edge will start from
     * @param y the vertex that the edge will go to
     */
    public void addEdge(T x, T y) {

        if (!adjList.containsKey(x)) {
            adjList.put(x, new Vertex<T>(x));   //create new neighbor list
            numVertices++;   //increase vertex count
            capacity++;
        }
        if (!adjList.containsKey(y)) {
            adjList.put(y, new Vertex<T>(y));   //create new neighbor list
            numVertices++; //increase vertex count
            capacity++;
        }

        adjList.get(x).getNeighbors().add(y);   //create link from x to y
        numEdges++;   //increase edge count

    }

    /**
     * This method removes an edge from "x" to "y" from the graph.
     * The method checks if the neighbor list of "x" contains "y".
     * If it does, "y" is removed from the neighbor list of "x" and the edge count decreases by 1.
     * Otherwise, do nothing.
     * @param x the element that the edge starts at
     * @param y the element that the edge goes to
     */
    public void removeEdge(T x, T y) {

        //check if x.getNeighbors() has y
        if (adjList.get(x).getNeighbors().contains(y)) {
            adjList.get(x).getNeighbors().remove(y);   //remove y from neighbor list
            numEdges--;   //decrease edge count
        }
    }

    /**
     * This method determines if two elements going from "x" to "y" are an edge.
     * The method checks the neighbor list of "x".
     * If the neighbor list of "x" contains "y", return true.
     * If not, return false.
     * @param x the element that the edge starts from
     * @param y the element that the edge goes to
     * @return true if "x" and "y" are an edge, false otherwise
     */
    public boolean isEdge(T x, T y) {
        if (adjList.get(x).getNeighbors().contains(y)) {   //check neighbor list for y
            return true;
        }

        return false;
    }

    /**
     * This method returns the neighbors of the vertex of label "x".
     * The method returns the neighbors of the vertex associated with "x" in the HashMap adjList.
     * @param x the element whose neighbors will be displayed
     * @return the neighbors of element "x"
     */
    public LinkedList<T> neighbors(T x) {
        return adjList.get(x).getNeighbors();
    }

    /**
     * This method returns the number of vertices in the graph.
     * @return the number of vertices in the graph
     */
    public int numVertices() {
        return numVertices;
    }

    /**
     * This method returns the number of edges in the graph.
     * @return the amount of edges in the graph
     */
    public int numEdges() {
        return numEdges;
    }

    /**
     * This method removes a vertex from the graph.
     * The edge count by the amount of neighbors "x" has, since all outgoing edges will be removed.
     * A for loop is used to traverse the HashMap adjList to search for elements that might have "x" as a neighbor.
     * If an element has "x" as a neighbor, "x" is removed from its neighbor list and the edge count is decreased by 1.
     * Finally, "x" is removed from adjList and the vertex count is decreased by 1.
     * @param x the element to be removed
     */
    public void removeVertex(T x) {
        numEdges -= adjList.get(x).getNeighbors().size();   //decrease edge count by x neighbor count

        for (HashMap.Entry element : adjList.entrySet()) {   //check neighbors for x
            if (adjList.get(element).getNeighbors().contains(x)) {
                adjList.get(element).getNeighbors().remove(x);   //remove x if element has x as neighbor
                numEdges--;
            }
        }
        adjList.remove(x);   //remove from graph
        numVertices--;   //decrease vertex count
    }

    /**
     * This method takes an already created graph and puts it in a new file in .dot format.
     * A new file is created with filename as the name and a PrintWriter is created so it can write in the file.
     * The file is started in .dot fashion by having the writer write "digraph{" as the first line.
     * For every element in adjList, a new iterator is created to go through the element's neighbor list.
     * While the element still has neighbors, print spaces, the element, edge indicator, and elements in neighbor list.
     * After adjList is traversed, writer writes the closing bracket and closes.
     * @param filename the name of the file that will be created
     * @throws FileNotFoundException
     */
    public void toDotFile(String filename) throws FileNotFoundException {
        File file = new File(filename);
        PrintWriter writer = new PrintWriter(filename);
        writer.println("digraph{");   //print starting line

        for (HashMap.Entry<T, Vertex> element : adjList.entrySet()) {   //traverse adjList

                ListIterator<T> iterator = element.getValue().getNeighbors().listIterator(0);   //iterator for neighbor list

                while (iterator.hasNext()) {
                    writer.println("   " + element.getValue().getLabel() + "->" + iterator.next());   //print label and neighbors
                }
            }

        writer.print("}");   //print closing bracket
        writer.close();   //close writer
    }

    /**
     * This method traverses the graph with a recursive depth first search.
     * The method first sets every vertex's "marked" instance variable to false to indicate that it hasn't been visited yet.
     * The method then calls its helper method, dfsHelper.
     * @param x the element that the search starts from
     */

    public void depthFirstSearch(T x) {

        for (HashMap.Entry<T, Vertex> element : adjList.entrySet()) {
            element.getValue().setMarked(false);   //everything not visited yet
        }

        dfsHelper(x);   //recursive helper
    }

    /**
     * This method is the recursive helper method for depthFirstSearch.
     * The LinkedList connections is made from the starting element's neighbors and a generic type nextNeighbor is created but not set.
     * Starting element is set as true and is printed.
     * A ListIterator is created to iterate through the neighbor list.
     * nextNeighbor is set to the next element in the neighbor list.
     * While the neighbor list still has elements, if nextNeighbor has not been visited yet, recursively call this method on nextNeighbor.
     * @param element the element that the search starts from
     */
    private void dfsHelper(T element) {
        LinkedList<T> connections = adjList.get(element).getNeighbors();


        T nextNeighbor;

        adjList.get(element).setMarked(true);   //visited

        System.out.print(adjList.get(element).getLabel() + " ");

        ListIterator<T> iterator = connections.listIterator(0);   //go through neighbor list

        while (iterator.hasNext()) {
            nextNeighbor = iterator.next();   //set as next element

            if (!adjList.get(nextNeighbor).isMarked()) {   //dfsHelper if not visited yet
                dfsHelper(nextNeighbor);
            }
        }

    }

    /**
     * This method traverses the graph with a breadth first search.
     * The LinkedList connections is made from the starting element's neighbors and a generic type nextNeighbor is created but not set.
     * LinkedList connections and generic types vertex and nextNeighbor are created but not set.
     * A queue is created. Starting element is marked as true.
     * Element is enqueued onto the queue.
     * While the queue isn't empty, the element is dequeued and is set to vertex and then is printed.
     * connections is set as the element's neighbors.
     * An iterator is created to iterate through the neighbor list.
     * nextNeighbor is set as the next element in the neighbor list.
     * If nextNeighbor has not been marked as visited, set nextNeigbor as marked for visiting and enqueue it.
     * @param x the element that the search starts from
     */
    public void breadthFirstSearch(T x) {
        for (HashMap.Entry<T, Vertex> element : adjList.entrySet()) {   //set all to false
            element.getValue().setMarked(false);
        }
        LinkedList<T> connections;   //neighbor list

        T vertex, nextNeighbor;

        Queue<T> queue = new Queue<T>();

        adjList.get(x).setMarked(true);   //set current as marked
        queue.enqueue(x);

        while (!queue.isEmpty()) {
            vertex = queue.dequeue();
            System.out.print(adjList.get(vertex).getLabel() + " ");

            connections = adjList.get(vertex).getNeighbors();   //neighbor list

            ListIterator<T> iterator = connections.listIterator(0);   //go through neighbor list

            while (iterator.hasNext()) {
                nextNeighbor = iterator.next();   //next element in neighbor list
                if (!adjList.get(nextNeighbor).isMarked()) {
                    adjList.get(nextNeighbor).setMarked(true);   //mark it as now visted
                    queue.enqueue(nextNeighbor);   //put unmarked vertex in the queue
                }
            }
        }
    }

    /**
     * This method traverses the graph using an iterative implementation of depth first search.
     * The method first sets every vertex's "marked" instance variable to false to indicate that it hasn't been visited yet.
     * A stack is created to store vertices and the starting element is pushed onto it.
     * While the stack isn't empty, pop a vertex and check if it has been visited yet or not.
     * If not, print the vertex and set its marked value to true to indicate it has been visited.
     * A ListIterator is created to iterate through a vertex's neighbor list.
     * While there are still elements that haven't been checked yet in the neighbor list, generic type next is set to the next element in the list.
     * If next has not been marked yet, push onto the stack.
     * @param x the element that the search starts from
     */

    public void depthFirstSearchIterative(T x) {
        for (HashMap.Entry<T, Vertex> element : adjList.entrySet()) {   //set all to false
            element.getValue().setMarked(false);
        }

        Stack<T> stack = new Stack<T>();   //stack to keep track

        stack.push(x);   //push start element
        while (!stack.isEmpty()) {
            x = stack.peek();   //x is the popped element
            stack.pop();

            if (!adjList.get(x).isMarked()) {   //print if unmarked
                System.out.print(adjList.get(x).getLabel() + " ");
                adjList.get(x).setMarked(true);   //mark as now visted
            }
            ListIterator<T> iterator = adjList.get(x).getNeighbors().listIterator();   //go through neighbor list

            while (iterator.hasNext()) {
                T next = iterator.next();   //next element in neighbor list
                if (!adjList.get(next).isMarked()) {
                    stack.push(next);   //push a vertex if unmarked
                }
            }
        }


    }

    public static void main(String[] args) throws FileNotFoundException {
        Graph g = new Graph();

        g.addEdge("A", "B");
        g.addEdge("A", "C");
        g.addEdge("B", "D");
        g.addEdge("B", "E");
        g.addEdge("E","H");
        g.addEdge("C","F");
        g.addEdge("C","G");
        g.addEdge("F","K");

        //g.toDotFile("firstdotfile");
        g.depthFirstSearch("A");
        System.out.println();
        g.depthFirstSearchIterative("A");
        System.out.println();
        g.breadthFirstSearch("A");

    }
}