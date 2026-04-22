package com.operating_system.file_system_project.controller;

import com.operating_system.file_system_project.service.FileSystemService;
import com.operating_system.file_system_project.recovery.RecoveryManager;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class FileController {

    private final FileSystemService fs;
    private final RecoveryManager rm;

    public FileController(FileSystemService fs, RecoveryManager rm) {
        this.fs = fs;
        this.rm = rm;
    }

    @PostMapping("/create")
    public String create(@RequestParam String name, @RequestParam int size) {
        rm.log("CREATE " + name + " " + size);
        return fs.createFile(name, size);
    }

    @PostMapping("/delete")
    public String delete(@RequestParam String name) {
        rm.log("DELETE " + name);
        return fs.deleteFile(name);
    }

    @GetMapping("/read")
    public String read(@RequestParam String name) {
        return fs.readFile(name);
    }

    @PostMapping("/crash")
    public String crash() {
        return fs.crashSystem();
    }

    @GetMapping("/recover")
    public String recover() {
        return rm.recover(fs);
    }

    @GetMapping("/optimize")
    public String optimize() {
        return fs.defragment();
    }

    @GetMapping("/files")
    public Object list() {
        return fs.getAllFiles();
    }
}