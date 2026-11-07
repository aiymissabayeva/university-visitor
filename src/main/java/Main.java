public class Main {
    public static void main(String[] args) {

        DirectoryNode university = new DirectoryNode("University");

        DirectoryNode sdp = new DirectoryNode("SoftwareDesignPatterns");
        DirectoryNode web = new DirectoryNode("WebTechnologies");
        DirectoryNode javascript = new DirectoryNode("JavaScript");
        DirectoryNode math = new DirectoryNode("Mathematics");

        university.addChild(sdp);
        university.addChild(web);
        university.addChild(math);

        sdp.addChild(new FileNode("Visitor.java", 400));
        sdp.addChild(new FileNode("Notes.txt", 150));

        web.addChild(new FileNode("index.html", 600));
        web.addChild(javascript);

        javascript.addChild(new FileNode("script.js", 250));

        FileSystemWalker walker = new FileSystemWalker();

        TotalSizeVisitor sizeVisitor = new TotalSizeVisitor();
        walker.walk(university, sizeVisitor);
        System.out.println("Total size: " + sizeVisitor.getTotalSize());

        SearchVisitor searchVisitor = new SearchVisitor(university, ".java", 300);
        walker.walk(university, searchVisitor);
        System.out.println("Matching files: " + searchVisitor.getMatchingPaths());

        StatisticsVisitor statisticsVisitor = new StatisticsVisitor(university);
        walker.walk(university, statisticsVisitor);

        System.out.println("Files: " + statisticsVisitor.getFileCount());
        System.out.println("Directories: " + statisticsVisitor.getDirectoryCount());
        System.out.println("Maximum depth: " + statisticsVisitor.getMaxDepth());
    }
}