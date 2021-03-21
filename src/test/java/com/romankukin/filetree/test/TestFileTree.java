package com.romankukin.filetree.test;

import com.romankukin.filetree.FileTree;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.assertEquals;

public class TestFileTree {

  @Test
  public void testNullPath() {
    FileTree fileTree = new FileTree();
    Optional<String> tree = fileTree.tree(null);
    assertTrue(tree.isEmpty(), "null path failure");
  }

  @Test
  public void testBadPath() {
    FileTree fileTree = new FileTree();
    Optional<String> tree = fileTree.tree(Path.of("the path does not exist"));
    assertTrue(tree.isEmpty(), "not existing path case failure");
  }

  @Test
  public void pathOne() throws IOException {
    String path = "path1";
    assertTrue(pathsTestsHandler(path), path);
  }

  @Test
  public void pathTwo() throws IOException {
    String path = "path2";
    assertTrue(pathsTestsHandler(path), path);
  }

  private boolean pathsTestsHandler(String path) throws IOException {
    FileTree fileTree = new FileTree();
    Optional<String> tree = fileTree.tree(Path.of("./src/test/resources/" + path));
    assertTrue(tree.isPresent());

    String expected = Files.lines(Path.of("./src/test/resources/" + path + "_result.txt"))
        .collect(Collectors.joining("\n"));
    assertEquals(expected.strip(), tree.get().strip());
    return true;
  }
}
