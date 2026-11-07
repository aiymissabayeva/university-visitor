import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SearchVisitor implements Visitor {

    private final AbstractNode root;
    private final String namePart;
    private final long minimumSize;
    private final List<String> matchingPaths = new ArrayList<>();

    public SearchVisitor(AbstractNode root, String namePart, long minimumSize) {
        if (root == null || namePart == null || minimumSize < 0) {
            throw new IllegalArgumentException("Invalid search configuration");
        }

        this.root = root;
        this.namePart = namePart;
        this.minimumSize = minimumSize;
    }

    @Override
    public void visit(FileNode file) {
        if (file.getName().contains(namePart)
                && file.getSize() >= minimumSize) {
            matchingPaths.add(getPath(file));
        }
    }

    @Override
    public void visit(DirectoryNode directory) {
        // Directories are not search results
    }

    private String getPath(AbstractNode file) {
        List<String> parts = new ArrayList<>();
        AbstractNode current = file;

        while (current != root && current != null) {
            parts.add(current.getName());
            current = current.getParent();
        }

        if (current == null) {
            throw new IllegalArgumentException("File is outside the selected root");
        }

        if (parts.isEmpty()) {
            return root.getName();
        }

        Collections.reverse(parts);
        return String.join("/", parts);
    }

    public List<String> getMatchingPaths() {
        List<String> result = new ArrayList<>(matchingPaths);
        Collections.sort(result);
        return result;
    }
}