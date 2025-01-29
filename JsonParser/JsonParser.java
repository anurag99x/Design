package DesignProblems.commandLineTool.JsonParser;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JsonParser {
    public static void main(String[] args) {
        if(args.length != 1){
            System.out.println("Usage: java JsonValidator <file_path>");
            System.exit(1);
        }

        String filePath = args[0];
        Path path = Paths.get(filePath);

        try{
            String content = Files.readString(path).trim();

            if (isValidJson(content)){
                System.out.println("Valid Json");
                System.exit(0);
            }else{
                System.out.println("After reading this is Invalid Json");
                System.exit(1);
            }
        }catch (Exception e){
            System.out.println("Exception occoured: "+e);
            System.exit(1);
        }
    }

    private static boolean isValidJson(String content) {
        String jsonPattern = "^\\{\\s*(\"[^\"]+\"\\s*:\\s*(\"[^\"]*\"|true|false|null|\\d+|\\{}|\\[])\\s*)(,\\s*\"[^\"]+\"\\s*:\\s*(\"[^\"]*\"|true|false|null|\\d+|\\{}|\\[])\\s*)*}$";
        Pattern pattern = Pattern.compile(jsonPattern);
        Matcher matcher = pattern.matcher(content);
        return matcher.matches();
    }
}
