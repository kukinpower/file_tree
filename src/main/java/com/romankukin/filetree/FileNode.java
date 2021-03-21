package com.romankukin.filetree;

import java.io.File;
import java.nio.file.Path;
import java.util.Arrays;

public class FileNode {

  File root;
  FileNode[] files;
  FileNode parent;
  int lvl;
  long size;

  FileNode(String path) {
    this(new File(path), 0);
  }

  FileNode(Path path) {
    this(new File(String.valueOf(path)), 0);
  }

  FileNode(File file) {
    this(file, 0);
  }

  FileNode(File file, int lvl) {
    long currentSize = file.isDirectory() ? 0 : file.length();

    this.size = currentSize;
    this.parent = null;
    this.lvl = lvl;
    this.root = file;

    File[] listFiles = file.listFiles();

    if (listFiles != null) {
      Arrays.sort(listFiles, (o1, o2) -> {
        if (o1.isDirectory() && !o2.isDirectory()) {
          return -1;
        }
        if (!o1.isDirectory() && o2.isDirectory()) {
          return 1;
        }
        return o1.getName().compareToIgnoreCase(o2.getName());
      });
      files = new FileNode[listFiles.length];
      for (int i = 0; i < files.length; i++) {
        files[i] = new FileNode(listFiles[i], lvl + 1);
        currentSize += files[i].size;
        files[i].parent = this;
      }
    } else {
      files = null;
    }
    if (this.root.isDirectory()) {
      this.size = currentSize;
    }
  }

  void printFiles(StringBuilder stringBuilder, String indent, boolean isLast) {
    stringBuilder.append(indent)
        .append(parent != null ? " " : "")
        .append(this.root.getName())
        .append(" ")
        .append(this.size)
        .append(" bytes")
        .append("\n");

    if (files != null) {
      indent = indent.length() > 1 ? indent.substring(0, indent.length() - 2) : indent;
      String space = (isLast ? "   " : "│  ").repeat(this.lvl > 0 ? 1 : 0);
      for (int i = 0; i < files.length; i++) {
        if (i + 1 == files.length) {
          files[i].printFiles(stringBuilder, indent + space + "└─", true);
        } else {
          files[i].printFiles(stringBuilder, indent + space + "├─", false);
        }
      }
    }
  }
}