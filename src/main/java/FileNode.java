public class FileNode implements Element{
    private final String name;
    private final long size;

    public FileNode(String name, long size){
        if (size < 0){
            throw new IllegalArgumentException("File size can't be negative");
        }

        this.name = name;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public long getSize() {
        return size;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
