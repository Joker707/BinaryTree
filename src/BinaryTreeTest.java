import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BinaryTreeTest {
    @Test
    public void add() {
        BinaryTree bt = new BinaryTree();
        BinaryTree actualBt = new BinaryTree();

        assertEquals(bt, actualBt);

        BinaryTree bst = new BinaryTree(10);
        BinaryTree actualBst = new BinaryTree(10);

        assertEquals(bst, actualBst);

        bst.add(7);
        Node head = actualBst.getHead();
        Node second = new Node(7, head);
        head.setLeft(second);

        assertEquals(bst, actualBst);

        bst.add(15);
        Node third = new Node(15, head);
        head.setRight(third);

        assertEquals(bst, actualBst);
    }

    @Test
    public void delete() {
        BinaryTree bst = new BinaryTree(8);
        int[] a = {5, 10, 2};
        for (int i : a) bst.add(i);
        bst.delete(5);
        BinaryTree actualbst = new BinaryTree(8);
        int[] b = {10, 2};
        for (int i : b) actualbst.add(i);
        assertEquals(bst, actualbst);

        BinaryTree bst1 = new BinaryTree(8);
        int[] c = {5, 10, 2, 7, 6};
        for (int i : c) bst1.add(i);
        bst1.delete(5);
        BinaryTree actualbst1 = new BinaryTree(8);
        int[] d = {6, 10, 2, 7};
        for (int i : d) actualbst1.add(i);
        assertEquals(bst1, actualbst1);

        BinaryTree bst2 = new BinaryTree(8);
        int[] e = {5, 10, 2, 6, 7};
        for (int i : e) bst2.add(i);
        bst2.delete(5);
        BinaryTree actualbst2 = new BinaryTree(8);
        int[] f = {6, 10, 2, 7};
        for (int i : f) actualbst2.add(i);
        assertEquals(bst2, actualbst2);
    }

    @Test
    public void search() {
        BinaryTree bst = new BinaryTree(10);
        int[] a = {5, 14, 6, 7, 23, 12};
        for (int i : a) bst.add(i);
        Node node = bst.search(7);
        assertEquals(7, node.getValue());
        node = bst.search(23);
        assertEquals(23, node.getValue());
    }
    private void equalsNeighbours(int value, Integer[] neighbours ) {
        BinaryTree bst = new BinaryTree(10);
        int[] a = {6, 14, 5, 7, 23, 12};
        for (int i : a) bst.add(i);

        Integer nodeValue = null;

        Node[] bstNodes = bst.getNeighbours(value);

        for (int i = 0; i < 3; i++) {
            if ( bstNodes[i] == null) {
                nodeValue = null;
            }
            if (bstNodes[i] != null) {
                nodeValue = bstNodes[i].getValue();
            }
            assertEquals(nodeValue, neighbours[i]);
        }
    }

    @Test
    public void neighbours() {
        Integer[] actual = {5, 7, 10};
        equalsNeighbours(6, actual);
        Integer[] actual1 = {12, 23, 10};
        equalsNeighbours(14, actual1);
        Integer[] actual2 = {null, null, 6};
        equalsNeighbours(5, actual2);
        Integer[] actual3 = {6, 14, null};
        equalsNeighbours(10, actual3);
    }

}

