//Eric Song
//112294760
//CSE214
//Homework 3

import java.security.InvalidParameterException;
import java.util.ArrayList;

public class RedBlackNode<K extends Comparable<K>, V> extends TreeNode<K, V> {
    // red node if this is true, black if false
    private boolean red;

    // default to red
    public RedBlackNode(K key, V value) {
        super(key, value);
        red = true;
    }

    // can take a string to set color
    // this makes things a little easier when testing
    public RedBlackNode(K key, V value, String color) {
        super(key, value);
        if (color.equalsIgnoreCase("black"))
            setBlack();
        else if (color.equalsIgnoreCase("red"))
            setRed();
        else
            throw new InvalidParameterException("color must be red or black");
    }

    public boolean isRed() {
        return red;
    }

    public boolean isBlack() {
        return !red;
    }

    public void setRed() {
        this.red = true;
    }

    public void setBlack() {
        this.red = false;
    }

    public RedBlackNode<K, V> getLeft() {
        return (RedBlackNode<K, V>) left;
    }

    public RedBlackNode<K, V> getRight() {
        return (RedBlackNode<K, V>) right;
    }

    public RedBlackNode<K, V> getParent() {
        return (RedBlackNode<K, V>) parent;
    }

    @Override
    public String toString() {
        return "TreeNode [red=" + red + ", " + "key=" + getKey() + ", value=" + getValue() + "]";
    }

    /**
     * This method determines if a tree rooted at a node is a valid red black tree.
     * If the node that the function is called on is red, return false. A red black tree cannot have a red node.
     * If the current node is red and if any of its children are red, return false.
     * If not, the method invokes the findBlackHeight(RedBlackNode node) function and returns the value returned by that.
     * The method returns false if the helper method returns -1. Otherwise, it returns true.
     * @return true if the tree rooted at this node is a red black tree, false if it is not.
     */
    public Boolean isRedBlackTree() {
        if(this.isRed()){   //if the root node is red, return false
            return false;
        }
        if((this.isRed() && ((RedBlackNode)this.left).isRed()) || (this.isRed() && ((RedBlackNode)this.right).isRed())){
            return false; //if the current node and any of its children are red, return false
        }
        return findBlackHeight(this) != -1;

    }

    /**
     * This method returns the amount of black nodes in a path of nodes in a tree.
     * If the current node is null, return 0, as an empty tree's height is always 0.
     * Integers left and right are determined by recursively calling this method onto the current node's children.
     * Integer add is 0 or 1 depending on the node's color. If the current node is red, add stays as zero. If it is black, add is changed to 1.
     * If the left's black height and the right's black height are not equal, or if left or right is -1, return -1.
     * Otherwise, return left tree's height plus add.
     * @param node the node whose black height is being found
     * @return the amount of black nodes in a path of nodes
     */
    private int findBlackHeight(RedBlackNode node){

        if(node == null){ //an empty tree's height is 0
            return 0;
        }
        int left = findBlackHeight((RedBlackNode)node.left);   //recursively finds height for left child of node
        int right = findBlackHeight((RedBlackNode)node.right);   //recursively finds height for right child of node
        //number to add if current node is black or not
        int add = 0;
        if(node.isBlack()){   //if current node is black, add 1
            add = 1;
        }
        //otherwise, add 0

        //if left side black count is not right side black count, return -1
        //the -1 will be recursively attributed to left or right
        if(left == -1 || right == -1 || left != right){
            return -1;
        }
        else{
            return left + add;   //return left side's black count with what is to be added
        }

    }


}