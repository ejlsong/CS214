public class Edge<T> {
    private T neighbor;
    private double weight;

    public T getNeighbor() {
        return neighbor;
    }

    public void setNeighbor(T neighbor) {
        this.neighbor = neighbor;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Edge(T neighbor) {
        setNeighbor(neighbor);
        setWeight(1);
    }

    public Edge(T neighbor, double weight) {
        setNeighbor(neighbor);
        setWeight(weight);
    }
}