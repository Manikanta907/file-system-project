package com.operating_system.file_system_project.recovery;

import com.operating_system.file_system_project.service.FileSystemService;
import org.springframework.stereotype.Component;

import java.io.*;

@Component
public class RecoveryManager {

    public void log(String action) {
        try (FileWriter fw = new FileWriter("log.txt", true)) {
            fw.write(action + "\n");
        } catch (Exception e) {}
    }

    public String recover(FileSystemService fs) {

        StringBuilder result = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader("log.txt"))) {

            String line;

            while ((line = br.readLine()) != null) {

                String[] parts = line.split(" ");

                if (parts[0].equals("CREATE")) {
                    String name = parts[1];
                    int size = Integer.parseInt(parts[2]);
                    fs.createFile(name, size);
                    result.append("Recovered CREATE ").append(name).append("\n");
                }

                if (parts[0].equals("DELETE")) {
                    String name = parts[1];
                    fs.deleteFile(name);
                    result.append("Recovered DELETE ").append(name).append("\n");
                }
            }

            fs.resetCrash();

        } catch (Exception e) {
            return "Recovery failed";
        }

        return result.toString();
    }
}