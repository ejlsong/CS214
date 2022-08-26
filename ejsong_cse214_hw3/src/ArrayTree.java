//Eric Song
//112294760
//CSE214
//Homework 3

import java.util.Arrays;

class ArrayTree<K extends Comparable<K>, V> {
    TreeElement<K, V>[] tree;

    // Sets up an initial array for the tree with 1 slot for the root
    @SuppressWarnings("unchecked")
    public ArrayTree() {
        tree = (TreeElement<K, V>[]) new TreeElement[1];
    }

    public TreeElement<K, V> getLeft(int loc) {
        return locToElement(getLeftLoc(loc));
    }

    public TreeElement<K, V> getRight(int loc) {
        return locToElement(getRightLoc(loc));
    }

    public TreeElement<K, V> getParent(int loc) {
        return locToElement(getParentLoc(loc));
    }

    public int getLeftLoc(int loc) {
        return 2 * loc + 1;
    }

    public int getRightLoc(int loc) {
        return 2 * loc + 2;
    }

    public int getParentLoc(int loc) {
        return (loc - 1) / 2;
    }

    // returns the element stored at a location
    public TreeElement<K, V> locToElement(int loc) {
        if (loc >= tree.length || loc < 0)
            return null;
        else
            return tree[loc];
    }

    // only method that actually adds things to the array
    public void setLoc(int loc, TreeElement<K, V> e) {
        if (loc < 0)
            throw new IndexOutOfBoundsException("Tree locations must be 0 or more");
        // if we're adding something beyond the length of the tree array, then we must
        // grow the array
        if (loc >= tree.length)
            resize();
        tree[loc] = e;
        e.setTreeLocation(loc);
    }

    // takes a location in the tree and a new Element
    // makes that element the left child of the node at loc
    public void setLeft(int loc, TreeElement<K, V> e) {
        setLoc(getLeftLoc(loc), e);
    }

    // takes a location in the tree and a new Element
    // makes that element the right child of the node at loc
    public void setRight(int loc, TreeElement<K, V> e) {
        setLoc(getRightLoc(loc), e);
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        // double the size of the array
        // adds just enough spaces for one more full level of a complete tree
        int new_length = tree.length * 2 + 1;
        // create new array
        TreeElement<K, V>[] temp_tree = (TreeElement<K, V>[]) new TreeElement[new_length];
        // copy contents of old array to new array
        for (int i = 0; i < tree.length; i++) {
            temp_tree[i] = tree[i];
        }
        // set replace old array with the new one
        tree = temp_tree;
    }

    @Override
    public String toString() {
        // returns string representation of tree array which holds nodes in complete
        // order
        // I.e. left to right, top to bottom
        return "ArrayTree " + Arrays.toString(tree);
    }

    /**
     * This method returns the uncle, or parent's sibling, of a node specified in a given location in the tree.
     * If the location of the node is 0, meaning that it is the root of the tree, null is returned, as the root cannot have an uncle, let alone a parent.
     * The left child is denoted as tree[loc*2+1].
     * If the left child of the grandparent exists, and the node's parent is not the left child of the grandparent, the grandparent's left child is returned.
     * The right child is denoted as tree[loc*2+2].
     * If the right child of the grandparent exists, and the node's parent is not the right child of the grandparent, the grandparent's right child is returned.
     * Otherwise, return null.
     * @param loc the location of the node that has its uncle returned
     * @return the uncle of the node in the specified location of the tree
     */
    public TreeElement<K, V> getUncle(int loc) {

        //if the node is the root of the entire tree, return null as it cannot have parents, let alone uncles
        if (loc == 0) {
            return null;
        }
        //left child is tree[loc*2+1]
        //right child is tree[loc*2+1]

        //getParentLoc is called twice to get the grandparent location, then is multiplied by 2 and added by 1 or 2 to get its other child
        //also checking of the grandparent's child is not null

        if (tree[getParentLoc(getParentLoc(loc))*2+1] != null && tree[getParentLoc(loc)] != tree[getParentLoc(getParentLoc(loc))*2+1]) {
            return tree[getParentLoc(getParentLoc(loc))*2+1];
        }
        if(tree[getParentLoc(getParentLoc(loc))*2+2] != null && tree[getParentLoc(loc)] != tree[getParentLoc(getParentLoc(loc))*2+2]){
            return tree[getParentLoc(getParentLoc(loc))*2+2];
        }

        //null if conditions are not met
        return null;
    }

    /**
     * This method determines of a tree denoted as an array is a min heap rooted at a specified location.
     * The method traverses the tree using a for loop, making sure to never reach a leaf location.
     * If the node currently being analyzed by the for loop has a value larger than any of its children, return false.
     * Return true if those if statements are never met.
     * In the event of a leaf location, since the location will be out of the for loop bounds, the method will automatically return true.
     * This is because single nodes are vacuously min heaps.
     * @param loc the location in the tree of the node that will be determined if the subtree rooted by that node is a min heap.
     * @return true if the subtree is a min heap, and false if it is not.
     */
    public boolean isMinHeap(int loc) {

        //start the loop at the specified location
        //(length-2)/2 is first leaf
        //make sure i does not surpass a leaf index

        for(int i = loc; i <   (tree.length-2)/2;i++ ){
            //check if the current node's children are smaller than the current node
            if(tree[getLeftLoc(loc)].getKey().compareTo(tree[loc].getKey()) < 0){
                return false;
            }
            if(tree[getRightLoc(loc)].getKey().compareTo(tree[loc].getKey()) < 0){
                return false;
            }
        }
        //if loc is greater than or equal to (length-2)/2, loop isn't performed and returns true
        //single nodes are always heaps
        //leafs are vacuously min heaps

        return true;


    }



}
