package com.company;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        FilesCreatorController controller = new FilesCreatorController();
        System.out.println("Pick class to create files: \n" +
                "1 - PrintWriter\n" +
                "2 - BufferedWriter\n" +
                "3 - DataOutputStream\n" +
                "4 - FileOutputStream\n" +
                "5 - Files");
        Scanner scanner = new Scanner(System.in);
        String content;
        String extension;

        chooseAlgorithmForFileDuplication(controller, scanner);
        prepareFolderForFiles();

        System.out.println("Choose operation: \n" +
                "1 - create new generic file\n" +
                "2 - copy existing file");

        switch (scanner.nextLine()) {
            case "1":
                content = ("Some given content" + System.lineSeparator()).repeat(20);
                extension = ".txt";
                break;
            case "2":
                System.out.println("Enter path to the file (c:\\some\\folder\\esample.file):");
                String pathToFile = scanner.nextLine();
                Path file = Paths.get(pathToFile);
                if (Files.isRegularFile(file)) {
                    content = new String(Files.readAllBytes(file));
                    extension = file.toString().substring(file.toString().lastIndexOf(".") + 1);
                } else {
                    System.out.println("Error. Not a correct file. Exiting.");
                    return;
                }
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + scanner.nextLine());
        }


        System.out.println("Enter amount of files to be created:");
        int iterations = scanner.nextInt();
        Instant start = Instant.now();
        for (int i = 0; i < iterations; i++) {
            String filename = ".//FILES/" + "file_" + i + extension;
            controller.execute(filename, content);
        }
        Instant stop = Instant.now();
        double timeElapsed = Duration.between(start, stop).toMillis();

        System.out.println("Done. Time elapsed: " + timeElapsed/1000);

    }

    private static void chooseAlgorithmForFileDuplication(FilesCreatorController controller, Scanner scanner) {
        CreateFile algorithm;
        switch (scanner.nextLine()) {
            case "1":
                algorithm = new FilePrintWriter();
                break;
            case "2":
                algorithm = new FileBufferedWriter();
                break;
            case "3":
                algorithm = new FileDataOutputStream();
                break;
            case "4":
                algorithm = new FileFileOutputStream();
                break;
            case "5":
                algorithm = new FileFiles();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + scanner.nextLine());
        }
        controller.setAlgorithm(algorithm);
    }

    private static void prepareFolderForFiles() {
        System.out.println("Creating empty ./FILES folder");

        try {
            Path filesFolder = Paths.get("./FILES/");
            if (Files.isDirectory(filesFolder)) {
                System.out.println("Folder exists. Emptying it.");
                deleteDirectoryRecursion(filesFolder);
            }
        } catch (Exception ignore) {
            //noop
        }
        createFolder();
    }

    private static void createFolder() {
        try {
            Files.createDirectory(Paths.get("./FILES/"));
        } catch (IOException ignore) {
            //noop
        }
    }

    static void deleteDirectoryRecursion(Path path) throws IOException {
        if (Files.isDirectory(path, LinkOption.NOFOLLOW_LINKS)) {
            try (DirectoryStream<Path> entries = Files.newDirectoryStream(path)) {
                for (Path entry : entries) {
                    deleteDirectoryRecursion(entry);
                }
            }
        }
        Files.delete(path);
    }
}
