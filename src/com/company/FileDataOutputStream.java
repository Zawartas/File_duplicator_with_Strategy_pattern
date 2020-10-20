package com.company;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileDataOutputStream implements CreateFile {
    @Override
    public void createFile(String file, String content) {
        try (FileOutputStream fos = new FileOutputStream(file, false)) {
            DataOutputStream outStream = new DataOutputStream(new BufferedOutputStream(fos));
            outStream.writeUTF(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
