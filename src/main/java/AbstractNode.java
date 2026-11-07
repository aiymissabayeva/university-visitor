public abstract class AbstractNode implements Element{
    private final String name;
    private DirectoryNode parent;

    protected AbstractNode(String name){
        if (name == null || name.isBlank()){
            throw new IllegalArgumentException("Name can't be empty");
        }
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    public DirectoryNode getParent() {
        return parent;
    }

    void setParent(DirectoryNode parent){
        this.parent = parent;
    }
}
