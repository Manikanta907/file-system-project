package com.operating_system.file_system_project.service;

import com.operating_system.file_system_project.model.FileEntry;
import com.operating_system.file_system_project.disk.DiskManager;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.util.HashMap;

@Service
public class FileSystemService {

    private HashMap<String, FileEntry> directory = new HashMap<>();
    private final DiskManager disk;
    private boolean isCrashed = false;

    public FileSystemService(DiskManager disk) {
        this.disk = disk;
    }

    public String createFile(String name, int size) {

        if (isCrashed) return "System crashed! Recover first.";

        int start = disk.allocate(size);

        if (start == -1) return "No space available!";

        FileEntry file = new FileEntry(name, start, size);
        directory.put(name, file);

        try (FileWriter fw = new FileWriter("virtualDisk.txt", true)) {
            fw.write(name + " stored at block " + start + "\n");
        } catch (Exception e) {}

        return "File created!";
    }

    public String deleteFile(String name) {

        if (isCrashed) return "System crashed!";

        FileEntry file = directory.get(name);

        if (file == null) return "File not found";

        disk.free(file.getStartBlock(), file.getSize());
        directory.remove(name);

        return "File deleted!";
    }

    public String readFile(String name) {
        return directory.containsKey(name) ?
                "Reading file: " + name :
                "File not found";
    }

    public HashMap<String, FileEntry> getAllFiles() {
        return directory;
    }

    // 🚨 Crash
    public String crashSystem() {
        isCrashed = true;
        return "System crashed!";
    }

    public void resetCrash() {
        isCrashed = false;
    }

    // ⚡ Optimization
    public String defragment() {

        int currentBlock = 0;

        for (FileEntry file : directory.values()) {
            FileEntry updated = new FileEntry(
                    file.getFileName(),
                    currentBlock,
                    file.getSize()
            );
            directory.put(file.getFileName(), updated);
            currentBlock += file.getSize();
        }

        return "Disk optimized!";
    }
}