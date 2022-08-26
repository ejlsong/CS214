import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Stack;

class Graph<T> {
    HashMap<T, Vertex<T>> adjList;

    public Graph() {
        adjList = new HashMap<T, Vertex<T>>();
    }

    public Graph(String filename) throws FileNotFoundException {
        adjList = new HashMap<T, Vertex<T>>();
        Scanner sc = new Scanner(new File(filename));
        // skip first line
        sc.nextLine();
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (line.equals("}")) // stop at closing brace
                break;
            String[] parts = line.split("->");
            this.addEdge((T) (parts[0].trim()), (T) (parts[1].trim()));
        }

        sc.close();
    }

    public int numVertices() {
        return adjList.size();
    }

    public int numEdges() {
        int edgeSum = 0;
        // sum the number of edges from each vertex
        for (Vertex<T> vert : adjList.values()) {
            edgeSum += vert.getNeighbors().size();
        }
        return edgeSum;
    }

    public boolean isEdge(T x, T y) {
        Vertex<T> vert = adjList.get(x);
        if (vert == null)  // x not in graph
            return false;
        // check for y in edges from x
        for (Edge<T> e : vert.getNeighbors()) {
            if (e.getNeighbor().equals(y))
                return true;
        }
        // did not find y
        return false;
    }

    public void addEdge(T x, T y) {
        Vertex<T> xVertex = adjList.get(x);
        Vertex<T> yVertex = adjList.get(y);
        if (xVertex == null) {
            // create x if it doesn't yet exist
            xVertex = new Vertex<T>(x);
            adjList.put(x, xVertex);
        }
        if (yVertex == null) {
            // create y if it doesn't yet exist
            yVertex = new Vertex<T>(y);
            adjList.put(y, yVertex);
        }
        // create edge for y and add it to neighbors of x
        xVertex.getNeighbors().addLast(new Edge<T>(y));
    }

    public void addEdge(T x, T y, double weight) {
        // same as above, but includes weight for the new edge
        Vertex<T> xVertex = adjList.get(x);
        Vertex<T> yVertex = adjList.get(y);
        if (xVertex == null) {
            xVertex = new Vertex<T>(x);
            adjList.put(x, xVertex);
        }
        if (yVertex == null) {
            yVertex = new Vertex<T>(y);
            adjList.put(y, yVertex);
        }
        xVertex.getNeighbors().addLast(new Edge<T>(y, weight));
    }

    public void DFS(T x, HashMap<T, Boolean> marked) {
        LinkedList<Edge<T>> connections = this.neighbors(x);
        marked.put(x, true);
        System.out.println(x);
        for (Edge<T> edge : connections) {
            T nextNeighbor = edge.getNeighbor();
            if (!marked.containsKey(nextNeighbor))
                DFS(nextNeighbor, marked);
        }
    }

    public void BFS(T x) {
        HashMap<T, Boolean> marked = new HashMap<>();
        LinkedList<Edge<T>> connections;
        T vertex;
        Queue<T> q = new Queue<T>();
        marked.put(x, true);
        q.enqueue(x);
        while (!q.isEmpty()) {
            vertex = q.dequeue();
            System.out.println(vertex);
            connections = neighbors(vertex);
            for (Edge<T> edge : connections) {
                T nextNeighbor = edge.getNeighbor();
                if (!marked.containsKey(nextNeighbor)) {
                    marked.put(nextNeighbor, true);
                    q.enqueue(nextNeighbor);
                }
            }
        }
    }

    public void iterativeDFS(T x) {
        HashMap<T, Boolean> marked = new HashMap<>();
        LinkedList<Edge<T>> connections;
        T vertex;
        Stack<T> q = new Stack<T>();
        marked.put(x, true);
        q.push(x);
        while (!q.isEmpty()) {
            vertex = q.pop();
            System.out.println(vertex);
            connections = neighbors(vertex);
            for (Edge<T> edge : connections) {
                T nextNeighbor = edge.getNeighbor();
                if (!marked.containsKey(nextNeighbor)) {
                    marked.put(nextNeighbor, true);
                    q.push(nextNeighbor);
                }
            }
        }
    }

    public LinkedList<Edge<T>> neighbors(T x) {
        Vertex<T> vert = adjList.get(x);
        if (vert == null)
            throw new NoSuchElementException();
        return vert.getNeighbors();
    }

    public void removeEdge(T x, T y) {
        Vertex<T> vert = adjList.get(x);
        if (vert == null)
            throw new NoSuchElementException();

        // find and remove edge to y in x's neighbor list
        ListIterator<Edge<T>> it = vert.getNeighbors().listIterator();
        while (it.hasNext()) {
            if (it.next().getNeighbor().equals(y))
                it.remove();
        }
    }

    public void removeVertex(T x) {
        if (!adjList.containsKey(x))
            throw new NoSuchElementException();
        // removes vertex and all its outgoing edges
        adjList.remove(x);
        // iterate over all edges to find incoming edges
        for (Vertex<T> vert : adjList.values()) {
            ListIterator<Edge<T>> it = vert.getNeighbors().listIterator();
            while (it.hasNext()) {
                if (it.next().getNeighbor().equals(x))
                    // remove incoming edge
                    it.remove();
            }
        }
    }

    public void toDotFile(String filename) throws IOException {
        PrintWriter writer = new PrintWriter(new FileWriter(filename));
        // opening line
        writer.println("digraph {");
        // iterate over all edges
        for (Vertex<T> vert : adjList.values())
            for (Edge<T> edge : vert.getNeighbors())
                // write the line for this edge
                writer.println("    " + vert.getLabel() + "->" + edge.getNeighbor());

        // closing line
        writer.println("}");
        writer.close();
    }

    public HashMap<T, T> primsMST() {




    }

    private T minKey(HashMap<T,Boolean> map) {
        T min = ;

        for(HashMap.Entry element : map.entrySet()){
            if(){

            }
        }
    }

    public void solveMaze(T start, T end) {

    }

}