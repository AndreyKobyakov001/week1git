import static java.lang.Math.max;

/**
 * A minimal implementation of a binary search tree. See the python version for
 * additional documentation.
 * <p>
 * You can also see Chapter 6 of <a href="https://www.teach.cs.toronto.edu/~csc148h/winter/notes/">CSC148 Course Notes</a>
 * if you want a refresher on BSTs, but it is required to complete this assignment.
 * @param <T>
 */
public class BST<T extends Comparable<T>> {
    //Note: the extends Comparable<T> above means we require T to implement the Comparable<T> interface,
    //      since a BST requires that we can compare its elements to determine the ordering.
    private T root;

    private BST<T> left;
    private BST<T> right;

    public BST(T root) {
        if (root != null) { // check to ensure we don't accidentally try to store null at the root!
            this.root = root;
            this.left = new BST<>();
            this.right = new BST<>();
        }
        // Note: each of the attributes will default to null
    }

    /**
     * Alternate constructor, so we don't have to explicitly pass in null.
     */
    public BST() {
        this(null);
    }

    public boolean isEmpty() {
        return root == null;
    }

    public boolean contains(T item) {
        // provided
        if (this.isEmpty()) {
            return false;
        } else if (item.equals(this.root)) { // we need to use .equals and not == to properly compare values
            return true;
        } else if (item.compareTo(this.root) < 0) {
            return this.left.contains(item);
        }
        return this.right.contains(item);

    }


    public void insert(T item) {
        if (this.isEmpty()) {
            this.root = item;
            this.left = new BST<>();
            this.right = new BST<>();
        } else if (item.compareTo(this.root) < 0) {
            this.left.insert(item);
        } else {
            this.right.insert(item);
        }
    }


    public void delete(T item) {
        if (this.isEmpty()) {
            return;
        } else if (item.equals(this.root)) {
            this.deleteRoot();
        } else if (item.compareTo(this.root) < 0) {
            this.left.delete(item);
        }
        this.right.delete(item);
    }

    private void deleteRoot() {
        if (this.left == null && this.right == null) {
            this.root = null;
        } else if (this.left == null) {
            this.root = this.right.root;
            this.left = this.right.left;
            this.right = this.right.right;
        } else if (this.right == null) {
            this.root = this.left.root;
            this.left = this.left.left;
            this.right = this.left.right;
        }
        assert this.left != null;
        this.root = this.left.extractMax();
    }


    private T extractMax() {
        if (this.right.isEmpty()) {
            T max_item = this.root;
            this.root = this.left.root;
            this.left = this.left.left;
            this.right = this.left.right;
            return max_item;
        }
        return this.right.extractMax();
    }

    public int height() {
        if (!this.isEmpty()) {
            return max(this.left.height(), this.right.height()) + 1;
        }
        return 0;
    }

    public int count(T item) {
        if (this.isEmpty()) {
            return 0;
        } else if (item.equals(this.root)) { // we need to use .equals and not == to properly compare values
            return 1 + this.right.count(item) + this.left.count(item);
        } else if (item.compareTo(this.root) < 0) {
            return this.left.count(item);
        }
        return this.right.count(item);
    }

    public int getLength() {
        if (this.isEmpty()) {
            return 0;
        } return 1 + this.right.getLength() + this.left.getLength();
    }

    public static void main(String[] args) {
        // Create a binary search tree
        BST<Integer> bst = new BST<>();

        // Test isEmpty
        System.out.println("Is BST empty? " + bst.isEmpty());

        // Insert elements
        bst.insert(5);
        bst.insert(3);
        bst.insert(7);
        bst.insert(2);
        bst.insert(4);
        bst.insert(6);
        bst.insert(8);

        // Test contains
        System.out.println("BST contains 3? " + bst.contains(3));
        System.out.println("BST contains 9? " + bst.contains(9));

        // Test delete
        bst.delete(3);
        System.out.println("After deleting 3, BST contains 3? " + bst.contains(3));

        // Test height
        System.out.println("Height of BST: " + bst.height());

        // Test count
        System.out.println("Count of 5 in BST: " + bst.count(5));
        System.out.println("Count of 10 in BST: " + bst.count(10));

        // Test getLength
        System.out.println("Length of BST: " + bst.getLength());
    }


}
