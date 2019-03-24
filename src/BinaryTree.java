import java.util.Objects;

public class BinaryTree {

    private Node head;

    public BinaryTree(int value) {
        head = new Node(value, null);
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

    private void AddNode(Node currentNode, int value) {
        if (currentNode.getValue() > value) {
            if (currentNode.getLeft() == null) {
                currentNode.setLeft(new Node(value, currentNode));
            } else {
                AddNode(currentNode.getLeft(), value);
            }

        } else {
            if (currentNode.getRight() == null) {
                currentNode.setRight(new Node(value, currentNode));
            } else {
                AddNode(currentNode.getRight(), value);
            }
        }
    }

    public void Add(int value) {
        if (head == null) {
            head = new Node(value, null);
        } else {
            Node currentNode = head;
            AddNode(currentNode, value);
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
            parent.setLeft(currentNode.getLeft());
        } else {
            if (currentNode.getRight().getRight() != null && currentNode.getRight().getLeft() == null) {
                currentNode.getRight().setLeft(currentNode.getLeft());
                parent.setLeft(currentNode.getRight());
                if (parent == null) {
                    head = currentNode.getRight();
                } else {
                    if (parent.getValue() > currentNode.getValue()) {
                        parent.setLeft(currentNode.getRight());
                    }
                }
            } else {
                if (currentNode.getRight().getRight() == null
                        && currentNode.getRight().getLeft() != null) {
                    currentNode.getRight().getLeft().setLeft(currentNode.getLeft());
                    currentNode.setLeft(currentNode.getRight().getLeft());
                    currentNode.getLeft().setRight(currentNode.getRight());
                    parent.setLeft(currentNode.getLeft());
                }
            }
        }
    }

    public Node[] getNeighbours(int value) {
        Node currentNode = search(value);
        return new Node[]{currentNode.getLeft(),
                currentNode.getRight(), currentNode.getParent()};
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BinaryTree that = (BinaryTree) o;
        return Node.equalsNodes(head, that.head);
    }

    @Override
    public int hashCode() {
        return 7 * head.getValue();
    }
}
