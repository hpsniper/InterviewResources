package trees;

public class BNode extends AbstractBNode {
    int value;
    AbstractBNode leftNode;
    AbstractBNode rightNode;

    public boolean isEmpty() { return false; }

    @Override
    public String toString() { return String.format("%d", value); }
}
