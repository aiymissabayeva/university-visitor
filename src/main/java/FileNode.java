public class FileNode extends AbstractNode{
    private final long size;

    public FileNode(String name, long size){
        super(name);
        if (size < 0){
            throw new IllegalArgumentException("File size can't be negative");
        }
        this.size = size;
    }

    public long getSize() {
        return size;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
