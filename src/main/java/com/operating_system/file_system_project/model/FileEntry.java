package com.operating_system.file_system_project.model;

public class FileEntry {

    private String fileName;
    private int startBlock;
    private int size;

    public FileEntry(String fileName, int startBlock, int size) {
        this.fileName = fileName;
        this.startBlock = startBlock;
        this.size = size;
    }

    public String getFileName() {
        return fileName;
    }

    public int getStartBlock() {
        return startBlock;
    }

    public int getSize() {
        return size;
    }
}