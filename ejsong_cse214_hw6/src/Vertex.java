import java.util.LinkedList;

class Vertex<T> {
    private T label;
    private LinkedList<Edge<T>> neighbors;

    public Vertex(T label) {
        setLabel(label);
        setNeighbors(new LinkedList<Edge<T>>());
    }

    public T getLabel() {
        return label;
    }

    public void setLabel(T label) {
        this.label = label;
    }

    public LinkedList<Edge<T>> getNeighbors() {
        return neighbors;
    }

    public void setNeighbors(LinkedList<Edge<T>> neighbors) {
        this.neighbors = neighbors;
    }

}