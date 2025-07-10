import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class test1 {

    // Helper methods to create TreeNodes for convenience
    private static <T> BinaryTree.TreeNode<T> createNode(T item) {
        return new BinaryTree.TreeNode<>(item);
    }

    private static <T> BinaryTree.TreeNode<T> createNode(T item, BinaryTree.TreeNode<T> left, BinaryTree.TreeNode<T> right) {
        return new BinaryTree.TreeNode<>(item, left, right);
    }

    @Test
    void testEmptyTreeIsCompletelyBalanced() {
        BinaryTree<Integer> tree = new BinaryTree<>();
        assertTrue(tree.isCompletelyBalanced());
    }

    @Test
    void testSingleNodeTreeIsCompletelyBalanced() {
        BinaryTree.TreeNode<Integer> root = createNode(1);
        BinaryTree<Integer> tree = new BinaryTree<>(root);
        assertTrue(tree.isCompletelyBalanced());
    }

    @Test
    void testTwoNodesOneSideIsNotCompletelyBalanced() {
        // Tree: 1 -> 2 (left)
        BinaryTree.TreeNode<Integer> root = createNode(1, createNode(2), null);
        BinaryTree<Integer> tree = new BinaryTree<>(root);
        assertFalse(tree.isCompletelyBalanced());
    }

    @Test
    void testTwoNodesOtherSideIsNotCompletelyBalanced() {
        // Tree: 1 -> 2 (right)
        BinaryTree.TreeNode<Integer> root = createNode(1, null, createNode(2));
        BinaryTree<Integer> tree = new BinaryTree<>(root);
        assertFalse(tree.isCompletelyBalanced());
    }

    @Test
    void testPerfectlyBalancedTreeIsCompletelyBalanced() {
        // Tree:
        //      1
        //     / \
        //    2   3
        //   / \ / \
        //  4  5 6  7
        BinaryTree.TreeNode<Integer> root = createNode(1,
                createNode(2, createNode(4), createNode(5)),
                createNode(3, createNode(6), createNode(7)));
        BinaryTree<Integer> tree = new BinaryTree<>(root);
        assertTrue(tree.isCompletelyBalanced());
    }

    @Test
    void testBalancedAtRootButNotRecursivelyIsNotCompletelyBalanced() {
        // Tree:
        //      1
        //     / \
        //    2   3
        //   /
        //  4
        // The root is balanced (height of left subtree = 2, height of right subtree = 1),
        // but the definition requires that both children are also completely balanced.
        // Node 2 is not completely balanced because its right child is null.
        BinaryTree.TreeNode<Integer> root = createNode(1,
                createNode(2, createNode(4), null),
                createNode(3));
        BinaryTree<Integer> tree = new BinaryTree<>(root);
        assertFalse(tree.isCompletelyBalanced());
    }

    @Test
    void testNotBalancedAtRootIsNotCompletelyBalanced() {
        // Tree:
        //      1
        //     /
        //    2
        //   / \
        //  3   4
        // The root has a left subtree height of 3 and right subtree height of 0.
        // It's not balanced at the root according to the definition.
        BinaryTree.TreeNode<Integer> root = createNode(1,
                createNode(2, createNode(3), createNode(4)),
                null);
        BinaryTree<Integer> tree = new BinaryTree<>(root);
        assertFalse(tree.isCompletelyBalanced());
    }

    @Test
    void testBalancedWithOneMissingLeafIsNotCompletelyBalanced() {
        // Tree:
        //      1
        //     / \
        //    2   3
        //   / \ /
        //  4  5 6
        // Left subtree rooted at 2 is completely balanced.
        // Right subtree rooted at 3 is NOT completely balanced because its right child is null.
        BinaryTree.TreeNode<Integer> root = createNode(1,
                createNode(2, createNode(4), createNode(5)),
                createNode(3, createNode(6), null));
        BinaryTree<Integer> tree = new BinaryTree<>(root);
        assertFalse(tree.isCompletelyBalanced());
    }

    @Test
    void testCompletelyBalancedWithHeight3OrMore() {
        //      1
        //     / \
        //    2   3
        //   / \ / \
        //  4  5 6  7
        // / \ / \ / \ / \
        //8  9 A B C D E F
        BinaryTree.TreeNode<Integer> root = createNode(1,
                createNode(2,
                        createNode(4, createNode(8), createNode(9)),
                        createNode(5, createNode(10), createNode(11))),
                createNode(3,
                        createNode(6, createNode(12), createNode(13)),
                        createNode(7, createNode(14), createNode(15))));
        BinaryTree<Integer> tree = new BinaryTree<>(root);
        assertTrue(tree.isCompletelyBalanced());
    }

    @Test
    void testUnbalancedDeeplyNestedTree() {
        // A tree that is "mostly" balanced, but has one imbalance deep down.
        //      1
        //     / \
        //    2   3
        //   / \ / \
        //  4  5 6  7
        // / \
        //8  9
        //   /
        //  10
        BinaryTree.TreeNode<Integer> root = createNode(1,
                createNode(2,
                        createNode(4, createNode(8), createNode(9, createNode(10), null)),
                        createNode(5)),
                createNode(3,
                        createNode(6),
                        createNode(7)));
        BinaryTree<Integer> tree = new BinaryTree<>(root);
        assertFalse(tree.isCompletelyBalanced());
    }
}