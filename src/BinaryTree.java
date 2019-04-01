import java.util.Objects;

public class BinaryTree {

    private Node head;

    public BinaryTree(int value) {
        head = new Node(value, null);
    }

    public BinaryTree() {
    }

    public Node getHead() {
        return head;
    }

    public void add(int value) {
        if (head == null) {
            head = new Node(value, null);
        } else {

            Node currentNode = head;

            while (currentNode != null) {

                if (currentNode.getValue() > value) {
                    if (currentNode.getLeft() == null) {
                        currentNode.setLeft(new Node(value, currentNode));
                        break;
                    }
                    currentNode = currentNode.getLeft();

                } else {
                    if (currentNode.getRight() == null) {
                        currentNode.setRight(new Node(value, currentNode));
                        break;
                    }
                    currentNode = currentNode.getRight();
                }
            }
        }
    }

    public Node search(int value) {

        Node currentNode = head;
        while (currentNode != null && currentNode.getValue() != value) {
            if (currentNode.getValue() > value)
                currentNode = currentNode.getLeft();
            else
                currentNode = currentNode.getRight();

        }
        return currentNode;
    }


    public void delete(int value) {
        Node currentNode = search(value);
        Node parent = currentNode.getParent();
        if (currentNode.getRight() == null && currentNode.getLeft() != null) {
            if (currentNode.getValue() < parent.getValue()) {
                parent.setLeft(currentNode.getLeft());
            } else {
                parent.setRight(currentNode.getLeft());
            }
        } else if (currentNode.getRight().getRight() != null) {
            if (currentNode.getValue() < parent.getValue()) {
                parent.setLeft(currentNode.getRight());
            } else {
                parent.setRight(currentNode.getRight());
            }
            if (parent == null) {
                head = currentNode.getRight();
            } else if (parent.getValue() > currentNode.getValue()) {

                parent.setLeft(currentNode.getRight());
            } else {
                parent.setRight(currentNode.getRight());
            }
        } else if (currentNode.getRight().getLeft() != null) {
            Node min = minNode(currentNode.getRight());
            if (currentNode.getValue() < parent.getValue()) {
                parent.setLeft(min);
            } else {
                parent.setRight(min);
            }
        }
    }


    public Node[] getNeighbours(int value) {
        Node currentNode = search(value);
        return new Node[]{currentNode.getLeft(),
                currentNode.getRight(), currentNode.getParent()};
    }


    public Node minNode( Node currentNode) {
        while (currentNode.getLeft() != null) {
                currentNode = currentNode.getLeft();
            }
        return currentNode;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BinaryTree that = (BinaryTree) o;
        return equalsNodes(head, that.head);
    }

    public boolean equalsNodes(Node node, Node node1) {
        if (node1 != null && node != null) {
            return node1.getValue() == node.getValue();
        } else if ((node1 == null && node != null) ||
                (node == null && node1 != null)) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public int hashCode() {
        return 7 * head.getValue();
    }
}
