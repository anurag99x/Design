package DesignProblems.commandLineTool.Ccwc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Ccwc {

    public static void main(String[] args) {
        try {
            if (args.length == 0) {
                // Default behavior: Read from standard input if no filename is specified
                processInputFromStdin();
            } else if (args.length == 1) {
                // Default option (-c, -l, -w) when only filename is provided
                processFile(args[0]);
            } else if (args.length == 2) {
                // Handle specific options (-c, -l, -w)
                switch (args[0]) {
                    case "-c":
                        countBytes(args[1]);
                        break;
                    case "-l":
                        countLines(args[1]);
                        break;
                    case "-w":
                        countWords(args[1]);
                        break;
                    default:
                        System.out.println("Invalid option. Usage: ccwc [-c|-l|-w] <filename>");
                }
            } else {
                System.out.println("Invalid arguments. Usage: ccwc [-c|-l|-w] <filename>");
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void processInputFromStdin() throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
            processContent(content.toString(), "stdin");
        }
    }

    private static void processFile(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        if (!Files.exists(path)) {
            throw new IOException("File does not exist.");
        }
        String content = Files.readString(path);
        processContent(content, filePath);
    }

    private static void processContent(String content, String source) {
        long lineCount = content.lines().count();
        long wordCount = Stream.of(content.split("\\s+")).filter(word -> !word.isEmpty()).count();
        long byteCount = content.getBytes().length;

        System.out.printf("%8d %8d %8d %s%n", lineCount, wordCount, byteCount, source);
    }

    private static void countBytes(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        if (!Files.exists(path)) {
            throw new IOException("File does not exist.");
        }
        long byteCount = Files.size(path);
        System.out.println(byteCount + " " + filePath);
    }

    private static void countLines(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        if (!Files.exists(path)) {
            throw new IOException("File does not exist.");
        }
        long lineCount;
        try (Stream<String> lines = Files.lines(path)) {
            lineCount = lines.count();
        }
        System.out.println(lineCount + " " + filePath);
    }

    private static void countWords(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        if (!Files.exists(path)) {
            throw new IOException("File does not exist.");
        }
        long wordCount;
        try (Stream<String> lines = Files.lines(path)) {
            wordCount = lines.flatMap(line -> Stream.of(line.split("\\s+"))).filter(word -> !word.isEmpty()).count();
        }
        System.out.println(wordCount + " " + filePath);
    }
}
