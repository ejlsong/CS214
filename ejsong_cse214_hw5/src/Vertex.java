//Eric Song
//112294760
//CSE214
//Homework 5

import java.util.LinkedList;

class Vertex<T> {
    private T label;
    private double weight;
    private LinkedList<T> neighbors;
    private boolean marked;

    public Vertex(T label) {
        setLabel(label);
        setNeighbors(new LinkedList<T>());
        setWeight(1);
    }

    public T getLabel() {
        return label;
    }

    public void setLabel(T label) {
        this.label = label;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public LinkedList<T> getNeighbors() {
        return neighbors;
    }

    public void setNeighbors(LinkedList<T> neighbors) {
        this.neighbors = neighbors;
    }

    /**
     * This method determines if a vertex is marked for visiting.
     * It reads the boolean instance variable called "marked" in the Vertex class.
     * @return whether the vertex was visited or not
     */

    public boolean isMarked(){
        return marked;
    }

    /**
     * This method changes the boolean value of the boolean instance variable called "marked".
     * The method takes in the argument boolean value and changes "marked" to it.
     * @param x the boolean value that "marked" will be changed to
     */

    public void setMarked(boolean x){
        marked = x;
    }

}