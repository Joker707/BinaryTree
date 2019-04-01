import java.util.Objects;

public class Node {

    private int value;

    private Node left;

    private Node right;

    private Node parent;

    public Node(int value, Node parent) {
        this.value = value;
        this.parent = parent;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public boolean equalsNodes(Node node, Node node1) {
        if (node1 != null && node != null) {
            return node1.value == node.value;
        } else if ((node1 == null && node != null) ||
                (node == null && node1 != null)) {
            return false;
        } else {
            return true;
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return value == node.value &&
                equalsNodes(left, node.left) &&
                equalsNodes(right, node.right) &&
                equalsNodes(parent, node.parent);
    }

    @Override
    public int hashCode() {
        int result = 7 * value;
        if (parent != null) {
            result += 7 * parent.value;
        }
        if (left != null) {
            result += 7 * left.value;
        }
        if (right != null) {
            result += 7 * right.value;
        }
        return result;
    }
}
