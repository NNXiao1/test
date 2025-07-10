public class BinaryTree<T> {

    TreeNode<T> root;

    public BinaryTree() {
        root = null;
    }

    public BinaryTree(TreeNode<T> t) {
        root = t;
    }

    public TreeNode<T> getRoot() {
        return root;
    }

    /* Returns the height of the tree. */
    public int height() {
        return calculateHeight(root);
    }

    /* Helper method to calculate the height of a subtree rooted at 'node'. */
    // Moved to be a static method within BinaryTree.
    private static <T> int calculateHeight(TreeNode<T> node) {
        if (node == null) {
            return 0; // The height of a null subtree is 0
        }
        int leftHeight = calculateHeight(node.left);
        int rightHeight = calculateHeight(node.right);
        return Math.max(leftHeight, rightHeight) + 1;
    }


    /* Returns true if the tree's left and right children are the same height
       and are themselves completely balanced. */
    public boolean isCompletelyBalanced() {
        // Handle empty tree and single-node tree as completely balanced
        if (root == null || (root.left == null && root.right == null)) {
            return true;
        }
        return balancedHelper(root);
    }

    // Helper method for isCompletelyBalanced
    public static <T> boolean balancedHelper(TreeNode<T> tree) {
        if (tree == null) {
            return true; // A null subtree is considered completely balanced
        }
        // Base case: Leaf nodes are completely balanced
        if (tree.left == null && tree.right == null) {
            return true;
        }

        // Calculate heights of left and right subtrees
        int leftHeight = calculateHeight(tree.left);
        int rightHeight = calculateHeight(tree.right);

        // For a tree to be completely balanced (non-leaf), the heights of its
        // left and right children must be exactly equal.
        if (leftHeight != rightHeight) {
            return false;
        }

        // Recursively check if both left and right subtrees are also completely balanced
        return balancedHelper(tree.left) && balancedHelper(tree.right);
    }

    /* Returns a BinaryTree representing the Fibonacci calculation for N. */
    public static BinaryTree<Integer> fibTree(int N) {
        if (N < 0) {
            throw new IllegalArgumentException("N must be a non-negative integer for fibTree.");
        }

        // Base cases for Fibonacci sequence
        if (N == 0) {
            return new BinaryTree<>(new TreeNode<>(0)); // F(0) = 0
        }
        if (N == 1) {
            return new BinaryTree<>(new TreeNode<>(1)); // F(1) = 1
        }

        // Recursive step: Calculate F(N-1) and F(N-2) recursively
        // and build the tree structure based on the definition
        BinaryTree<Integer> leftSubtree = fibTree(N - 1);
        BinaryTree<Integer> rightSubtree = fibTree(N - 2);

        // The root's item is the sum of the left and right children's items (F(N-1) + F(N-2) = F(N))
        int rootValue = leftSubtree.getRoot().getItem() + rightSubtree.getRoot().getItem();

        TreeNode<Integer> rootNode = new TreeNode<>(rootValue, leftSubtree.getRoot(), rightSubtree.getRoot());
        return new BinaryTree<>(rootNode);
    }

    /* Print the values in the tree in preorder: root value first, then values
       in the left subtree (in preorder), then values in the right subtree
       (in preorder). */
    public void printPreorder() {
        if (root == null) {
            System.out.println("(empty tree)");
        } else {
            root.printPreorder();
            System.out.println();
        }
    }

    /* Print the values in the tree in inorder: values in the left subtree
       first (in inorder), then the root value, then values in the right subtree
       (in inorder). */
    public void printInorder() {
        if (root == null) {
            System.out.println("(empty tree)");
        } else {
            root.printInorder();
            System.out.println();
        }
    }

    // Static nested TreeNode class
    static class TreeNode<T> {

        private T item;
        private TreeNode<T> left;
        private TreeNode<T> right;

        TreeNode(T obj) {
            item = obj;
            left = null;
            right = null;
        }

        TreeNode(T obj, TreeNode<T> left, TreeNode<T> right) {
            item = obj;
            this.left = left;
            this.right = right;
        }

        public T getItem() {
            return item;
        }

        public TreeNode<T> getLeft() {
            return left;
        }

        public TreeNode<T> getRight() {
            return right;
        }

        void setItem(T item) {
            this.item = item;
        }

        void setLeft(TreeNode<T> left) {
            this.left = left;
        }

        void setRight(TreeNode<T> right) {
            this.right = right;
        }

        private void printPreorder() {
            System.out.print(item + " ");
            if (left != null) {
                left.printPreorder();
            }
            if (right != null) {
                right.printPreorder();
            }
        }

        private void printInorder() {
            if (left != null) {
                left.printInorder();
            }
            System.out.print(item + " ");
            if (right != null) {
                right.printInorder();
            }
        }
    }
}