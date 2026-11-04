import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DirectoryNode implements Element{
    private final String name;
    private final List<Element> children = new ArrayList<>();
    private DirectoryNode parent;

    public DirectoryNode(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Element> getChildren() {
        return Collections.unmodifiableList(children);
    }

    public void addChild(Element child){
        if (child == null){
            throw new IllegalArgumentException("Child can't be null");
        }
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
