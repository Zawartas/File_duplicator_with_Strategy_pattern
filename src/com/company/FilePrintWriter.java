package com.company;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FilePrintWriter implements CreateFile {
    @Override
    public void createFile(String file, String content) {
        try (FileWriter fileWriter = new FileWriter(file, false)) {
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.print(content);
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
