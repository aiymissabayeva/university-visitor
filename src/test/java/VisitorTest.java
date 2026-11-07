import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class VisitorTest {

    @Test
    void testDoubleDispatch() {
        FileNode file = new FileNode("Lecture.txt", 100);
        DirectoryNode directory = new DirectoryNode("Courses");

        class TestVisitor implements Visitor {
            int fileVisits = 0;
            int directoryVisits = 0;

            @Override
            public void visit(FileNode file) {
                fileVisits++;
            }

            @Override
            public void visit(DirectoryNode directory) {
                directoryVisits++;
            }
        }

        TestVisitor visitor = new TestVisitor();

        file.accept(visitor);
        directory.accept(visitor);

        assertEquals(1, visitor.fileVisits);
        assertEquals(1, visitor.directoryVisits);
    }
}