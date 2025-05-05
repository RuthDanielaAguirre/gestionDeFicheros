/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ruthagulop
 */
import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class Funciones {

    public static void createFolder(String folderPath) throws Exception {
        File folder = new File(folderPath);
        if (!folder.exists()) {
            folder.mkdir();
        } else {
            throw new Exception("The folder already exists");
        }
    }

    public static void createFile(String path, String fileName, String content) throws IOException {
        String filePath = path + File.separator + fileName;
        FileWriter fr = new FileWriter(filePath, true);
        BufferedWriter bw = new BufferedWriter(fr);
        bw.write(content);
        bw.newLine();
        bw.flush();
        bw.close();
        fr.close();
    }

    public static String[] showListFiles(String path) {
        File folder = new File(path);
        return folder.list();
    }

    public static String showFile(String path, String fileName) throws Exception {
        String filePath = path + File.separator + fileName;
        File file = new File(filePath);
        if (!file.exists()) {
            throw new Exception("The file could not be found.");
        }
        StringBuilder sb;
        try (FileReader fr = new FileReader(file)) {
            BufferedReader br = new BufferedReader(fr);
            sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }   br.close();
        }
        return sb.toString();
    }

    public static boolean overwriteFile(String path, String fileName, String newContent) throws Exception {
        String filePath = path + File.separator + fileName;
        File file = new File(filePath);
        if (!file.exists()) {
            throw new Exception("The file does not exist");
        }
        FileWriter fw = new FileWriter(file, true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(newContent);
        bw.newLine();
        bw.flush();
        bw.close();
        fw.close();
        return true;
    }

    public static void deleteFile(String path, String fileName) throws Exception {
        String filePath = path + File.separator + fileName;
        File file = new File(filePath);
        if (!file.exists()) {
            throw new Exception("The file does not exist");
        } else {
            boolean deleted = file.delete();
            if (!deleted) {
                throw new Exception("The file could not be deleted");
            }
        }
    }

    public static int countChars(String path, String fileName) throws Exception {
        String filePath = path + File.separator + fileName;
        File file = new File(filePath);
        if (!file.exists()) {
            throw new Exception("The file could not be found");
        }
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        int numChars = 0;
        String line;
        while ((line = br.readLine()) != null) {
            numChars += line.replaceAll("\\s+", "").length();
        }
        br.close();
        fr.close();
        return numChars;
    }

    public static int countWords(String path, String fileName) throws Exception {
        String filePath = path + File.separator + fileName;
        File file = new File(filePath);
        if (!file.exists()) {
            throw new Exception("The file could not be found");
        }
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        int numberWords = 0;
        String line;
        while ((line = br.readLine()) != null) {
            if (!line.isBlank()) {
                String[] words = line.split("\\s+");
                for (String word : words) {
                    if (!word.isBlank()) {
                        numberWords++;
                    }
                }
            }
        }
        br.close();
        fr.close();
        return numberWords;
    }

    public static String[] swapWords(String path, String fileName, String oldWord, String newWord) throws Exception {
        String filePath = path + File.separator + fileName;
        File file = new File(filePath);
        if (!file.exists()) {
            throw new Exception("The file could not be found.");
        }
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        List<String> modifiedLine = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        boolean changes = false;
        String line;
        while ((line = br.readLine()) != null) {
            String modified = line.replace(oldWord, newWord);
            if (!line.equals(modified)) {
                changes = true;
            }
            modifiedLine.add(modified);
            sb.append(modified).append("\n");
        }
        br.close();
        fr.close();
        if (!changes) {
            return new String[]{"No occurrences of '" + oldWord + "' were found."};
        }
        FileWriter fw = new FileWriter(file, false);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        fw.close();
        return modifiedLine.toArray(new String[0]);
    }

    public static void printPDF(String path, String fileName) throws Exception {
        String filePath = path + File.separator + fileName;
        File file = new File(filePath);
        if (!file.exists()) {
            throw new Exception("File not found.");
        }
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line).append("\n");
        }
        br.close();
        fr.close();

        Document document = new Document();
        String outputPdfPath = path + File.separator + "output.pdf";
        PdfWriter.getInstance(document, new FileOutputStream(outputPdfPath));
        document.open();
        document.add(new Paragraph(sb.toString()));
        document.close();

        System.out.println("PDF created successfully at " + outputPdfPath);
    }
}