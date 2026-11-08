public class StatisticsVisitor implements Visitor {

    private final AbstractNode root;
    private int fileCount = 0;
    private int directoryCount = 0;
    private int maxDepth = 0;

    public StatisticsVisitor(AbstractNode root) {
        if (root == null) {
            throw new IllegalArgumentException("Root can't be null");
        }
        this.root = root;
    }

    @Override
    public void visit(FileNode file) {
        fileCount++;
        updateDepth(file);
    }

    @Override
    public void visit(DirectoryNode directory) {
        directoryCount++;
        updateDepth(directory);
    }

    private void updateDepth(AbstractNode node) {
        int depth = 0;

        while (node != root) {
            node = node.getParent();

            if (node == null) {
                throw new IllegalArgumentException("Node is outside the root");
            }

            depth++;
        }

        maxDepth = Math.max(maxDepth, depth);
    }

    public int getFileCount() {
        return fileCount;
    }

    public int getDirectoryCount() {
        return directoryCount;
    }

    public int getMaxDepth() {
        return maxDepth;
    }
}