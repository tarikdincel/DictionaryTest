package com.example.ce_216_project;

import java.io.*;
import java.util.*;

class Headword {
    String headword;
    List<String> descriptions;

    public Headword(String headword) {
        this.headword = headword;
        this.descriptions = new ArrayList<>();
    }

    public void addDescription(String description) {
        descriptions.add(description);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(headword.toLowerCase()).append(System.lineSeparator()); // Write headword in lowercase
        for (int i = 0; i < descriptions.size(); i++) {
            String description = descriptions.get(i);
            if (description.startsWith("Note") || description.startsWith("Synonym") || description.startsWith("see")) {
                sb.append("  ");
                sb.append(description).append(System.lineSeparator());
            } else {
                if (!description.matches("^\\d+\\..+")) { // Skip descriptions starting with numeric order
                    sb.append((i + 1) + ". " + description).append(System.lineSeparator());
                } else {
                    sb.append(description).append(System.lineSeparator());
                }
            }
        }
        return sb.toString();
    }
}

class HeadwordComparator implements Comparator<Headword> {
    @Override
    public int compare(Headword o1, Headword o2) {
        String headword1 = o1.headword.substring(0, 1).toLowerCase() + o1.headword.substring(1);
        String headword2 = o2.headword.substring(0, 1).toLowerCase() + o2.headword.substring(1);
        return headword1.compareTo(headword2);
    }
}

public class DictionaryEditor {
    public void dictionarySorter(String startFile){
        String inputFile = "src/main/resources/dictionaries/" + startFile;
        String outputFile = "placeholder.dict";

        Map<String, Headword> headwordMap = new HashMap<>();

        // Read headwords and their descriptions from input file
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String line;
            Headword currentHeadword = null;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty() || line.startsWith("\"") || line.startsWith("'")
                        || !Character.isLetter(line.trim().charAt(0))) {
                    continue; // Skip empty lines, lines starting with " or ', or lines not starting with a letter
                }

                if (line.contains("<")) {
                    // If line contains "<", add it as the first description of the current headword
                    if (currentHeadword != null) {
                        String[] descriptions = line.split(",");
                        for (String description : descriptions) {
                            currentHeadword.addDescription(description.trim());
                        }
                    }
                } else if (countOccurrences(line, '/') >= 2) {
                    String headword = line.trim();
                    if (!headwordMap.containsKey(headword) && !line.startsWith("see") && !line.startsWith("Note") && !line.startsWith("Synonym")) {
                        currentHeadword = new Headword(headword);
                        headwordMap.put(headword, currentHeadword);
                    } else if (headwordMap.containsKey(headword)) {
                        currentHeadword = headwordMap.get(headword);
                    }
                } else if (currentHeadword != null) {
                    currentHeadword.addDescription(line.trim());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        List<Headword> headwords = new ArrayList<>(headwordMap.values());

        // Sort headwords alphabetically
        Collections.sort(headwords, new HeadwordComparator());
        // Write the sorted headwords to output file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            for (Headword headword : headwords) {
                writer.write(headword.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Sorting complete. Sorted dictionary saved to " + outputFile);
        renameFile(startFile, outputFile);
    }

    public static void renameFile(String oldFileName, String newFileName) {
        File oldFile = new File(oldFileName);
        File newFile = new File(newFileName);

        if (oldFile.exists()) {
            // Delete the old file
            if (oldFile.delete()) {
                System.out.println("Old File Deleted Successfully");
            } else {
                System.out.println("Failed to Delete Old File");
            }
        }

        // Rename the new file to the old file name
        if (newFile.renameTo(oldFile)) {
            System.out.println("Renamed Successfully");
        } else {
            System.out.println("Renamed Unsuccessfully");
        }
    }

    private static int countOccurrences(String str, char c) {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == c) {
                count++;
            }
        }
        return count;
    }

    public void removeNumericOrdersFromFile(String inputFile) {
        // Read input lines from text file
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Remove numeric orders
        List<String> result = new ArrayList<>();
        for (String line : lines) {
            String processedLine = line.replaceAll("^\\d+\\.", "");
            result.add(processedLine.trim());
        }

        // Write result to output text file
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("placeholder.dict"))) {
            for (String line : result) {
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        renameFile(inputFile, "placeholder.dict");
    }

}