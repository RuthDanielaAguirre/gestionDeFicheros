/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.console;

import java.io.File;
import java.util.Scanner;
import model.Funciones;

/**
 *
 * @author ruthagulop
 */
public class Main {

    static Scanner OpScan = new Scanner(System.in);

    public static void main(String[] args) {
        OpScan.useDelimiter("\n");
        String option;

        do {
            System.out.println("Menu: ");
            System.out.println("1.- Create Folder");
            System.out.println("2.- Create File");
            System.out.println("3.- Show Files");
            System.out.println("4.- Show File");
            System.out.println("5.- Modify File");
            System.out.println("6.- Delete File");
            System.out.println("7.- Count Letters");
            System.out.println("8.- Count Words");
            System.out.println("9.- Swap Word");
            System.out.println("10.- Print PDF");
            System.out.println("Z.- Exit");
            System.out.println("Option: ");

            option = OpScan.next().toUpperCase();

            switch (option) {
                case "1":
                    createFolder();
                    break;
                case "2":
                    createFile();
                    break;
                case "3":
                    showListFiles();
                    break;
                case "4":
                    showFile();
                    break;
                case "5":
                    overWriteFile();
                    break;
                case "6":
                    deleteFile();
                    break;
                case "7":
                    countChars();
                    break;
                case "8":
                    countWords();
                    break;
                case "9":
                    swapWords();
                    break;
                case "10":
                    printPDF();
                    break;
                case "Z":
                    System.out.println("Have a nice day!");
                    break;
                default:
                    System.out.println("Incorrect option, please try again");
            }

        } while (!option.equals("Z"));
    }

    static void createFolder() {
        try {
            System.out.println("Enter the name of the folder: ");
            String folderName = OpScan.next();
            String path = System.getProperty("user.dir") + File.separator + folderName;

            Funciones.createFolder(path);
            System.out.println("Folder created successfully.");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    static void createFile() {
        try {
            System.out.println("Enter the folder where you want to add the file: ");
            String folder = OpScan.next();
            String path = System.getProperty("user.dir") + File.separator + folder;

            System.out.println("Enter the name of the file: ");
            String fileName = OpScan.next();
            if (!fileName.endsWith(".txt")) {
                fileName += ".txt";
            }
            System.out.println("Enter the body of the file: ");
            String content = OpScan.next();

            Funciones.createFile(path, fileName, content);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    static void showListFiles() {
        System.out.println("Enter the name of the folder: ");
        String folder = OpScan.next();
        String path = System.getProperty("user.dir") + File.separator + folder;

        String[] fileList = Funciones.showListFiles(path);
        if (fileList != null && fileList.length > 0) {
            for (String fileName : fileList) {
                System.out.println("- " + fileName);
            }
        } else {
            System.out.println("The folder is empty or doesn't exist.");
        }
    }

    static void showFile() {
        try {
            System.out.println("Enter the folder: ");
            String folder = OpScan.next();
            System.out.println("Enter the file: ");
            String fileName = OpScan.next();
            if (!fileName.endsWith(".txt")) {
                fileName += ".txt";
            }

            String path = System.getProperty("user.dir") + File.separator + folder;
            String contenido = Funciones.showFile(path, fileName);
            System.out.println(contenido);

        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    static void overWriteFile() {
        try {
            System.out.println("Enter the folder name: ");
            String folder = OpScan.next();
            System.out.println("Enter the file name: ");
            String fileName = OpScan.next();
            if (!fileName.endsWith(".txt")) {
                fileName += ".txt";
            }
            System.out.println("Enter the body you want to modify: ");
            String content = OpScan.next();
            String path = System.getProperty("user.dir") + File.separator + folder;

            boolean result = Funciones.overwriteFile(path, fileName, content);
            if (result) {
                System.out.println("File successfully modified");
            }

        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    static void deleteFile() {
        try {
            System.out.println("Enter the folder name: ");
            String folder = OpScan.next();
            System.out.println("Enter the file name: ");
            String fileName = OpScan.next();
            if (!fileName.endsWith(".txt")) {
                fileName += ".txt";
            }
            String path = System.getProperty("user.dir") + File.separator + folder;

            Funciones.deleteFile(path, fileName);
            System.out.println("File deleted successfully.");

        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    static void countChars() {
        try {
            System.out.println("Enter the folder name: ");
            String folder = OpScan.next();
            System.out.println("Enter the file name: ");
            String fileName = OpScan.next();
            if (!fileName.endsWith(".txt")) {
                fileName += ".txt";
            }
            String path = System.getProperty("user.dir") + File.separator + folder;
            int count = Funciones.countChars(path, fileName);
            System.out.println("The number of characters without spaces is: " + count);

        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    static void countWords() {
        try {
            System.out.println("Enter the folder name: ");
            String folder = OpScan.next();
            System.out.println("Enter the file name: ");
            String fileName = OpScan.next();
            if (!fileName.endsWith(".txt")) {
                fileName += ".txt";
            }
            String path = System.getProperty("user.dir") + File.separator + folder;
            int totalWords = Funciones.countWords(path, fileName);
            System.out.println("Total of words: " + totalWords);

        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    static void swapWords() {
        try {
            System.out.println("Enter the folder name: ");
            String folder = OpScan.next();
            System.out.println("Enter the file name: ");
            String fileName = OpScan.next();
            if (!fileName.endsWith(".txt")) {
                fileName += ".txt";
            }
            String path = System.getProperty("user.dir") + File.separator + folder;
            System.out.println("Enter the word you want to change: ");
            String oldWord = OpScan.next();
            System.out.println("Enter the new word: ");
            String newWord = OpScan.next();

            String[] modifiedLines = Funciones.swapWords(path, fileName, oldWord, newWord);
            for (String line : modifiedLines) {
                System.out.println(line);
            }

        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    static void printPDF() {
        try {
            System.out.println("Enter folder name: ");
            String folder = OpScan.next();
            String path = System.getProperty("user.dir") + File.separator + folder;
            String[] files = Funciones.showListFiles(path);

            if (files == null || files.length == 0) {
                System.out.println("No files found.");
                return;
            }

            System.out.println("Select the file to convert to PDF: ");
            for (int i = 0; i < files.length; i++) {
                System.out.println(i + ".- " + files[i]);
            }
            int opcion = OpScan.nextInt();
            if (opcion >= 0 && opcion < files.length) {
                Funciones.printPDF(path, files[opcion]);
            }

        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }
}
