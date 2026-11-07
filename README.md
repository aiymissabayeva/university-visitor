
# University File System - Visitor Pattern

## Project Description

This project demonstrates the Visitor Design Pattern using a simple university file system.

The file system contains files and directories. Different visitors perform operations without changing the file and directory classes.

## Design Pattern

**Visitor Pattern** separates operations from the objects on which they operate.

The project uses:
- `Element` - interface for file system elements.
- `AbstractNode` - base class for files and directories.
- `FileNode` - represents a file with a name and size.
- `DirectoryNode` - represents a directory containing files and subdirectories.
- `Visitor` - interface for visitor operations.
- `TotalSizeVisitor` - calculates total file size.
- `SearchVisitor` - searches files by name and minimum size.
- `StatisticsVisitor` - counts files, directories, and maximum depth.
- `FileSystemWalker` - traverses the file system.
- `Main` - demonstrates the application.

## Requirements

- Java 17
- Maven
- JUnit 5

## How to Run

Open the project in IntelliJ IDEA and run `Main.java`.

## How to Run Tests

Open a terminal in the project root and execute:

```bash
mvn test
```

The project contains 8 JUnit tests in:
- `VisitorTest.java`
- `FileSystemTest.java`

## Example Output

```text
Total size: 1400
Matching files: [SoftwareDesignPatterns/Visitor.java]
Files: 4
Directories: 5
Maximum depth: 3
```