package com.company;

public class FilesCreatorController {
    private CreateFile algorithm;

    public void setAlgorithm(CreateFile algorithm) {
        this.algorithm = algorithm;
    }

    public void execute(String filename, String content) {
        algorithm.createFile(filename, content);
    }

}
