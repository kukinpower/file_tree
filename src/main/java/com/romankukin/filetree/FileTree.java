package com.romankukin.filetree;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.NoSuchElementException;
import java.util.Optional;

public class FileTree {

  public Optional<String> tree(Path path) {
    try {
      if (!Files.exists(path)) {
        return Optional.empty();
      }
    } catch (NullPointerException | NoSuchElementException e) {
      return Optional.empty();
    }
    StringBuilder stringBuilder = new StringBuilder();
    FileNode fileNode = new FileNode(path);
    fileNode.printFiles(stringBuilder, "", true);
    return Optional.of(stringBuilder.toString());
  }
}
