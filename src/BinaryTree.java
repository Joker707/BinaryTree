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
            return;
        }
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
        if (currentNode.getLeft() == null && currentNode.getRight() == null) {
            currentNode = null;
        } else if (currentNode.getLeft() != null && currentNode.getRight() == null) {
            if (parent != null) {
                currentNode.getLeft().setParent(parent);
                if (currentNode.getValue() < parent.getValue()) {
                    parent.setLeft(currentNode.getLeft());
                } else {
                    parent.setRight(currentNode.getLeft());
                }
            } else {
                head = currentNode.getLeft();
                head.setParent(null);
            }
        } else if (currentNode.getLeft() == null && currentNode.getRight() != null) {
            if (parent != null) {
                currentNode.getRight().setParent(parent);
                if (currentNode.getValue() < parent.getValue()) {
                    parent.setLeft(currentNode.getRight());
                } else {
                    parent.setRight(currentNode.getRight());
                }
            } else {
                head = currentNode.getRight();
                head.setParent(null);
            }
        } else {
            if (currentNode.getValue() < parent.getValue()) {
                if (currentNode.getRight().getLeft() == null) {
                    currentNode.getLeft().setParent(currentNode.getRight());
                    currentNode.getRight().setParent(parent);
                    currentNode.getRight().setLeft(currentNode.getLeft());
                    if (parent != null) {
                        parent.setLeft(currentNode.getRight());
                    } else {
                        head = currentNode.getRight();
                        head.setParent(null);
                    }
                } else {
                    Node minNode = minNode(currentNode.getRight());
                    minNode.getParent().setLeft(null);
                    minNode.setParent(parent);
                    minNode.setLeft(currentNode.getLeft());
                    minNode.setRight(currentNode.getRight());
                    if (parent != null) {
                        parent.setLeft(minNode);
                    } else {
                        head = minNode;
                        head.setParent(null);
                    }
                    currentNode.getRight().setParent(minNode);
                    currentNode.getLeft().setParent(minNode);
                }
            } else {
                if (currentNode.getRight().getLeft() == null) {
                    currentNode.getLeft().setParent(currentNode.getRight());
                    currentNode.getRight().setParent(parent);
                    currentNode.getRight().setLeft(currentNode.getLeft());
                    if (parent != null) {
                        parent.setRight(currentNode.getRight());
                    } else {
                        head = currentNode.getRight();
                        head.setParent(null);
                    }
                } else {
                    Node minNode = minNode(currentNode.getRight());
                    minNode.getParent().setLeft(null);
                    minNode.setParent(parent);
                    minNode.setLeft(currentNode.getLeft());
                    minNode.setRight(currentNode.getRight());
                    if (parent != null) {
                        parent.setRight(minNode);
                    } else {
                        head = minNode;
                        head.setParent(null);
                    }
                    currentNode.getRight().setParent(minNode);
                    currentNode.getLeft().setParent(minNode);
                }
            }
        }
    }


    public Node[] getNeighbours(int value) {
        Node currentNode = search(value);
        return new Node[]{currentNode.getLeft(),
                currentNode.getRight(), currentNode.getParent()};
    }

    public Node minNode(Node currentNode) {
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

    private boolean equalsNodes(Node node, Node node1) {
        if (node1 != null && node != null) {
            return (node1.getValue() == node.getValue()) &&
                    (equalsNodes(node.getLeft(), node1.getLeft())) &&
                    (equalsNodes(node.getRight(), node1.getRight()));
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
