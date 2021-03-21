package com.romankukin.filetree;

import java.nio.file.Path;
import java.util.Optional;

public class Main {
  public static void main(String[] args) {
    if (args.length == 0) {
      System.out.println("No arguments passed. Exit.");
      return;
    } else if (args.length != 1) {
      System.out.println("Pass only one argument. Exit.");
      return;
    }
    FileTree fileTree = new FileTree();
    Optional<String> tree = fileTree.tree(Path.of(args[0]));
    if (tree.isEmpty()) {
      System.out.println("Bad path. Exit.");
    } else {
      System.out.println(tree.get());
    }
  }
}
