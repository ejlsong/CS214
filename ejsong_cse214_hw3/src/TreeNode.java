//Eric Song
//112294760
//CSE214
//Homework 3
public class TreeNode<K extends Comparable<K>, V> {

    // pointers shared with subclasses
    protected TreeNode<K, V> left;
    protected TreeNode<K, V> right;
    protected TreeNode<K, V> parent;
    private K key;
    private V value;

    public TreeNode(K key, V value) {
        this.key = key;
        this.value = value;
        left = null;
        right = null;
        parent = null;
    }

    public TreeNode<K, V> getLeft() {
        return left;
    }

    public void setLeft(TreeNode<K, V> node) {
        node.setParent(this);
        this.left = node;
    }

    public TreeNode<K, V> getRight() {
        return right;
    }

    public void setRight(TreeNode<K, V> node) {
        node.setParent(this);
        this.right = node;
    }

    public TreeNode<K, V> getParent() {
        return parent;
    }

    public void setParent(TreeNode<K, V> parent) {
        this.parent = parent;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }



    public String printSubTree() {
        StringBuilder buffer = new StringBuilder(50);
        print(buffer, "", "");
        return buffer.toString();
    }

    private void print(StringBuilder buffer, String prefix, String childrenPrefix) {
        buffer.append(prefix);
        buffer.append(this.toString());
        buffer.append('\n');

        if (left != null) {
            if (right != null) {
                left.print(buffer, childrenPrefix + "├──left| ", childrenPrefix + "│   ");
            } else {
                left.print(buffer, childrenPrefix + "└──left| ", childrenPrefix + "    ");
            }
        }
        if (right != null) {
            right.print(buffer, childrenPrefix + "└──right| ", childrenPrefix + "    ");
        }
    }

    @Override
    public String toString() {
        return "TreeNode [key=" + key + ", value=" + value + "]";
    }

    /**
     * This method returns the uncle of a node.
     * If the node's grandparent's right child is not null and the node's parent is not the right child of the grandparent, return the grandparent's right child.
     * If the node's grandparent's left child is not null and the node's parent is not the left child of the grandparent, return the grandparent's left child.
     * Return null if conditions are not met.
     * @return the uncle of the node that this function is called on
     */

    public TreeNode<K, V> getUncle() {
        //grandparent is parent.parent
        //if the grandparent's right isn't null and the parent isn't the grandparent's right, return the grandparent's right
        if(parent.parent.getRight() != null && parent != parent.parent.getRight()){
            return parent.parent.getRight();
        }

        //if the grandparent's left isn't null and the parent isn't the grandparent's left, return the grandparent's left
        if(parent.parent.getLeft() != null && parent != parent.parent.getLeft()){
            return parent.parent.getLeft();
        }


        return null;   //otherwise, return null
    }

    /**
     * This method returns the next node that will be visited in an in-order traversal.
     * If the current node is null, return null.
     * If the right child of the current node exists, the left-most descendant of the right child is returned.
     * Otherwise, now analyze the right subtree, as the left subtree has already been visted.
     * temp is denoted as the node being analyzed.
     * Otherwise, while temp is the right child of its parent, temp is cast as its parent, going up the tree.
     * If temp's parent is null, meaning that it is the root of the entire tree, the method returns null, since the left subtree has already been traversed.
     * The while loop keeps going until it is not the right child anymore. temp's parent is then returned.
     * @return the next node to be visited in an in-order traversal
     */

    public TreeNode<K, V> nextInOrder() {
        if(this == null){   //return null if null
            return null;
        }
        TreeNode<K,V> temp = this;
        if(this.right != null){   //if right child exists, go to left most node of subtree
            return allTheWayDown(this.right);
        }
        else{
            while(temp == temp.parent.right){   //keep going up the tree as temp is right child
                temp = temp.parent;
                if(temp.parent == null){   //if root of the entire tree, return null
                    return null;
                }
            }
            return temp.parent;   //return parent when temp is not right child anymore
        }

    }

    /**
     * This method returns the left-most descendant of a node.
     * A new node, called x, will be used to denote the specified node.
     * The method uses a while loop to keep going down the left path of a node until the node's children doesn't exist, meaning that it is a leaf.
     * The loop will keep equating x to its left child until the left child is x is null.
     * x is then returned, as it is now the left-most descendant.
     * @param node the node whose left-most descendant will be found.
     * @return the left-most descendant of the specified node
     */
    private TreeNode<K,V> allTheWayDown(TreeNode<K,V> node){
        TreeNode<K,V> x = node;
        while(x.left != null){
            x = x.left;
        }
        return x;
    }


    /**
     * This method determines if a tree rooted at a node is a binary search tree.
     * The return line invokes the helper method, which will give the return value.
     * Parameters are cast as the K type for the minimum and maximum values an Integer can take.
     * @return the boolean value returned by the helper method, true if it is a binary search tree, false if not.
     */
    public boolean isBST() {
        return isBSTHelper(this,(K)((Integer)(-2147483648)),(K)((Integer)2147483647));

    }

    /**
     * This method returns whether if a tree rooted at a node is a binary search tree.
     * If the current node is null, meaning it did not violate any of the binary search tree rules, the method returns true.
     * If the current node's key is less than the minimum condition or larger than the maximum condition, the method returns false.
     * Otherwise, recursively call this method using the node's children and the updated conditions.
     * The left child's maximum condition will be the current node's key.
     * The right child's minimum condition will be the current node's key.
     * @param node the node currently being analyzed
     * @param min the minimum value condition of the binary search tree
     * @param max the maximum value condition of the binary search tree
     * @return
     */
    private boolean isBSTHelper(TreeNode<K,V> node,K min, K max){
        if(node == null){   //conditions not violated throughout tree, is bst
            return true;
        }
        if(node.getKey().compareTo(min) < 0 || node.getKey().compareTo(max) > 0){   //violates ranges, returns false
            return false;
        }
        return isBSTHelper(node.left,min,node.getKey()) && isBSTHelper(node.right,node.getKey(),max);   //recursively call method for children
    }


}