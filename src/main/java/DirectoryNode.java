import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DirectoryNode extends AbstractNode {

    private final List<Element> children = new ArrayList<>();

    public DirectoryNode(String name) {
        super(name);
    }

    public List<Element> getChildren() {
        return Collections.unmodifiableList(children);
    }

    public void addChild(AbstractNode child) {
        if (child == null) {
            throw new IllegalArgumentException("Child can't be null");
        }

        if (child.getParent() != null) {
            throw new IllegalArgumentException("Child already has a parent");
        }

        for (Element existing : children) {
            if (existing.getName().equals(child.getName())) {
                throw new IllegalArgumentException("Duplicate child name");
            }
        }

        DirectoryNode current = this;

        while (current != null) {
            if (current == child) {
                throw new IllegalArgumentException("Cycle detected");
            }
            current = current.getParent();
        }

        children.add(child);
        child.setParent(this);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}