package com.company;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileBufferedWriter implements CreateFile {
    @Override
    public void createFile(String file, String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, false))) {
            writer.append(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
