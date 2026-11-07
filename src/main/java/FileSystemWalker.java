public class FileSystemWalker {

    public void walk(Element root, Visitor visitor) {
        root.accept(new Visitor() {

            @Override
            public void visit(FileNode file) {
                file.accept(visitor);
            }

            @Override
            public void visit(DirectoryNode directory) {
                directory.accept(visitor);

                for (Element child : directory.getChildren()) {
                    walk(child, visitor);
                }
            }
        });
    }
}