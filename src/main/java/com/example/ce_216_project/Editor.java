package com.example.ce_216_project;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class Editor {
    public void addWord(String word, String meaning, String language1, String language2) throws IOException {
        String path1 = ("src/main/resources/dictionaries/" + language1 + "-" + language2 + ".dict");

        File add = new File(path1);
        FileWriter fw = new FileWriter(add, true);
        PrintWriter pw = new PrintWriter(fw);

        pw.println(word + " /ADDED/");
        pw.println(meaning);

        pw.close();

    }

    public StringBuilder editWord(String headword, String firstLanguage, String secondLanguage) {
        String fileName = "src/main/resources/dictionaries/" + firstLanguage + "-" + secondLanguage + ".dict";
        List<String> result = searchHeadwordInFile(headword, fileName);
        List<String> descriptions = getDescriptions(fileName, parseInt(result.get(1)), parseInt(result.get(2)));
        StringBuilder st = new StringBuilder();

        st.append(result.get(0) + "\n");
        for (int i = 0; i < descriptions.size(); i++) {
            st.append(descriptions.get(i) + "\n");
        }
        return st;
    }

    public List<String> searchHeadwordInFile(String headword, String fileName) {
        List<String> result = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            int lineNumber = 1;
            while ((line = br.readLine()) != null) {
                if (line.startsWith(headword + " /")) {
                    result.add(line);
                    result.add(Integer.toString(lineNumber));
                    while ((line = br.readLine()) != null) {
                        lineNumber++;
                        if (line.matches("^\\w.*") && line.matches(".*\\/.*\\/.*") && !line.matches("^['\"].*")) {
                            result.add(Integer.toString(lineNumber));
                            break;
                        }
                    }
                }
                lineNumber++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static List<String> getDescriptions(String fileName, int startLine, int endLine) {
        List<String> lines = new ArrayList<>();
        try {
            File inputFile = new File(fileName);
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));

            String currentLine;
            int lineCount = 1;
            while ((currentLine = reader.readLine()) != null) {
                if (lineCount > startLine && lineCount < endLine) {
                    lines.add(currentLine);
                }
                lineCount++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    public void deleteLines(String fileName, int startLine, int endLine) {
        try {
            File inputFile = new File(fileName);
            File tempFile = new File("tempFile.txt");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String currentLine;
            int lineCount = 1;
            while ((currentLine = reader.readLine()) != null) {
                if (lineCount < startLine || lineCount > endLine) {
                    writer.write(currentLine + System.getProperty("line.separator"));
                }
                lineCount++;
            }
            writer.close();
            reader.close();
            inputFile.delete();
            tempFile.renameTo(inputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
