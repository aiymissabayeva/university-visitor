import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class FileSystemTest {

    @Test
    void testTotalSize() {

        DirectoryNode university = new DirectoryNode("University");
        DirectoryNode courses = new DirectoryNode("Courses");
        DirectoryNode java = new DirectoryNode("Java");

        university.addChild(new FileNode("Notes.txt", 100));
        university.addChild(courses);

        courses.addChild(new FileNode("Lecture.pdf", 200));
        courses.addChild(java);

        java.addChild(new FileNode("Main.java", 300));

        TotalSizeVisitor visitor = new TotalSizeVisitor();
        FileSystemWalker walker = new FileSystemWalker();

        walker.walk(university, visitor);

        assertEquals(600, visitor.getTotalSize());
    }
    @Test
    void testSearchConditions() {

        DirectoryNode university = new DirectoryNode("University");

        university.addChild(new FileNode("Lecture.java", 500));
        university.addChild(new FileNode("Homework.java", 100));
        university.addChild(new FileNode("Lecture.pdf", 600));

        SearchVisitor visitor = new SearchVisitor(university, ".java", 300);
        FileSystemWalker walker = new FileSystemWalker();

        walker.walk(university, visitor);

        assertEquals(
                List.of("Lecture.java"),
                visitor.getMatchingPaths()
        );
    }
    @Test
    void testSearchPaths() {

        DirectoryNode university = new DirectoryNode("University");
        DirectoryNode web = new DirectoryNode("Web");
        DirectoryNode sdp = new DirectoryNode("SDP");

        university.addChild(web);
        university.addChild(sdp);

        web.addChild(new FileNode("Notes.txt", 200));
        sdp.addChild(new FileNode("Lecture.txt", 300));
        university.addChild(new FileNode("Homework.txt", 400));

        SearchVisitor visitor = new SearchVisitor(university, ".txt", 100);
        FileSystemWalker walker = new FileSystemWalker();

        walker.walk(university, visitor);

        assertEquals(
                List.of(
                        "Homework.txt",
                        "SDP/Lecture.txt",
                        "Web/Notes.txt"
                ),
                visitor.getMatchingPaths()
        );
    }
    @Test
    void testStatistics() {

        DirectoryNode university = new DirectoryNode("University");
        DirectoryNode courses = new DirectoryNode("Courses");
        DirectoryNode java = new DirectoryNode("Java");

        university.addChild(courses);
        courses.addChild(java);

        university.addChild(new FileNode("Notes.txt", 100));
        courses.addChild(new FileNode("Lecture.pdf", 200));
        java.addChild(new FileNode("Main.java", 300));

        StatisticsVisitor visitor = new StatisticsVisitor(university);
        FileSystemWalker walker = new FileSystemWalker();

        walker.walk(university, visitor);

        assertEquals(3, visitor.getFileCount());
        assertEquals(3, visitor.getDirectoryCount());
        assertEquals(3, visitor.getMaxDepth());
    }
    @Test
    void testEmptyDirectoryAndNoMatch() {

        DirectoryNode university = new DirectoryNode("University");

        FileSystemWalker walker = new FileSystemWalker();

        TotalSizeVisitor sizeVisitor = new TotalSizeVisitor();
        walker.walk(university, sizeVisitor);

        assertEquals(0, sizeVisitor.getTotalSize());

        StatisticsVisitor statsVisitor = new StatisticsVisitor(university);
        walker.walk(university, statsVisitor);

        assertEquals(0, statsVisitor.getFileCount());
        assertEquals(1, statsVisitor.getDirectoryCount());
        assertEquals(0, statsVisitor.getMaxDepth());

        SearchVisitor searchVisitor = new SearchVisitor(university, ".java", 100);
        walker.walk(university, searchVisitor);

        assertTrue(searchVisitor.getMatchingPaths().isEmpty());
    }
    @Test
    void testRepeatExecutions() {

        DirectoryNode university = new DirectoryNode("University");
        university.addChild(new FileNode("Notes.txt", 100));
        university.addChild(new FileNode("Lecture.txt", 200));

        FileSystemWalker walker = new FileSystemWalker();

        TotalSizeVisitor firstVisitor = new TotalSizeVisitor();
        walker.walk(university, firstVisitor);

        TotalSizeVisitor secondVisitor = new TotalSizeVisitor();
        walker.walk(university, secondVisitor);

        assertEquals(300, firstVisitor.getTotalSize());
        assertEquals(300, secondVisitor.getTotalSize());
    }
    @Test
    void testTreeIntegrity() {

        DirectoryNode university = new DirectoryNode("University");
        DirectoryNode courses = new DirectoryNode("Courses");

        university.addChild(courses);
        courses.addChild(new FileNode("Notes.txt", 100));

        assertEquals(1, university.getChildren().size());
        assertEquals(1, courses.getChildren().size());

        assertThrows(IllegalArgumentException.class,
                () -> new FileNode("Invalid.txt", -100));

        assertThrows(IllegalArgumentException.class,
                () -> courses.addChild(new FileNode("Notes.txt", 200)));

        DirectoryNode other = new DirectoryNode("Other");

        assertThrows(IllegalArgumentException.class,
                () -> other.addChild(courses));

        assertThrows(IllegalArgumentException.class,
                () -> courses.addChild(university));

        assertThrows(IllegalArgumentException.class,
                () -> university.addChild(university));
    }
}