package com.operating_system.file_system_project.disk;

import org.springframework.stereotype.Component;

@Component
public class DiskManager {

    private final int TOTAL_BLOCKS = 100;
    private boolean[] freeBlocks = new boolean[TOTAL_BLOCKS];

    public int allocate(int size) {
        for (int i = 0; i <= TOTAL_BLOCKS - size; i++) {
            boolean free = true;

            for (int j = 0; j < size; j++) {
                if (freeBlocks[i + j]) {
                    free = false;
                    break;
                }
            }

            if (free) {
                for (int j = 0; j < size; j++) {
                    freeBlocks[i + j] = true;
                }
                return i;
            }
        }
        return -1;
    }

    public void free(int start, int size) {
        for (int i = 0; i < size; i++) {
            freeBlocks[start + i] = false;
        }
    }
}