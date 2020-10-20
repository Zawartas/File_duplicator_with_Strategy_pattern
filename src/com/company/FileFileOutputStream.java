package com.company;

import java.io.FileOutputStream;
import java.io.IOException;

public class FileFileOutputStream implements CreateFile {
    @Override
    public void createFile(String file, String content) {
        try (FileOutputStream outputStream = new FileOutputStream(file, false)) {
            byte[] strToBytes = content.getBytes();
            outputStream.write(strToBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
