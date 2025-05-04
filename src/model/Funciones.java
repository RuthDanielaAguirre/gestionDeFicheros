/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ruthagulop
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Funciones {

    public static void createFolder(String folderPath) throws Exception {

        File folder = new File(folderPath);

        if (!folder.exists()) {
            folder.mkdir();
        } else {
            throw new Exception("The file already exists");
        }

    }

    public static void createFile(String path, String fileName, String content) {
        FileWriter fr = null;
        BufferedWriter bw = null;
        try {
            String separator = File.separator;
            String filePath = path + separator + fileName;
            File file = new File(filePath);
            fr = new FileWriter(file, true);
            bw = new BufferedWriter(fr);
            bw.write(content);
            bw.newLine();
            bw.flush();
        } catch (IOException ex) {
            Logger.getLogger(Funciones.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
                if (fr != null) {
                    fr.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(Funciones.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static String[] showListFiles(String path) {
        File folder = new File(path);
        String[] fileList = folder.list();
        return fileList;
    }

    public static String showFile(String path, String fileName) throws Exception {
        FileReader fr = null;
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();

        String separator = File.separator;
        File file = new File(path + separator + fileName);
        if (!file.exists()) {
            throw new Exception("The file could not be found.");
        }
        try {
            fr = new FileReader(file);
            br = new BufferedReader(fr);

            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }

        } catch (IOException ex) {
            return "Error: The file could not be read.";
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
                if (fr != null) {
                    fr.close();
                }
            } catch (IOException ex) {
                return "Error while closing the file.";
            }
        }
        return sb.toString();
    }

    public static boolean overwriteFile(String path, String fileName, String newContent) throws Exception {
        FileWriter fw = null;

        String separator = File.separator;
        String filePath = path + separator + fileName;
        File file = new File(filePath);

        if (!file.exists()) {
            throw new Exception("The file does not exist");
        }

        try {
            fw = new FileWriter(file, false);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(newContent);
            bw.newLine();
            bw.flush();
            bw.close();
            return true;

        } catch (IOException ex) {
            Logger.getLogger(Funciones.class.getName()).log(Level.SEVERE, null, ex);
            return false;

        } finally {
            try {
                if (fw != null) {
                    fw.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(Funciones.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void deleteFile(String path, String fileName) throws Exception {
        String separator = File.separator;
        String filePath = path + separator + fileName;

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

    public static int countChars(String path, String fileName) {
        FileReader fr = null;
        BufferedReader br = null;
        try {
            String filePath = path + File.separator + fileName;
            File file = new File(filePath);

            if (!file.exists()) {

                throw new Exception("The file could not be found");

            }

            fr = new FileReader(file);
            br = new BufferedReader(fr);
            int numChars = 0;
            String line;

            while ((line = br.readLine()) != null) {
                numChars += line.replaceAll("\\s+", "").length();

            }
            return numChars;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Funciones.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Funciones.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
                if (fr != null) {
                    fr.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(Funciones.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return 0;
    }

    public static int countWords(String path, String fileName) {
        FileReader fr = null;
        BufferedReader br = null;

        try {
            String filePath = path + File.separator + fileName;
            File file = new File(filePath);

            if (!file.exists()) {
                throw new Exception("The file could not be found");
            }

            fr = new FileReader(filePath);
            br = new BufferedReader(fr);

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

            return numberWords;

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Funciones.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Funciones.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
                if (fr != null) {
                    fr.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(Funciones.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return 0;
    }

    public static String[] swapWords(String path, String fileName, String oldWord, String newWord) {
        String filePath = path + File.separator + fileName;
        FileReader fr = null;
        BufferedReader br = null;
        FileWriter fw = null;
        BufferedWriter bw = null;
        try {

            File file = new File(filePath);
            if (!file.exists()) {
                throw new Exception("The file could not be found.");
            }
            fr = new FileReader(file);
            br = new BufferedReader(fr);

            List<String> modifiedLine = new ArrayList<>();
            StringBuilder sb = new StringBuilder();
            boolean changes =false;
            String line;

            while ((line = br.readLine()) != null) {
                String modified = line.replace(oldWord, newWord);
                
                if (!line.equals(modified)) {
                    changes = true;
                }
                modifiedLine.add(modified);
                sb.append(modified).append("\n");

            }
            
             if (!changes) {
            return new String[]{"No occurrences of '" + oldWord + "' were found."};
        }
            fw = new FileWriter(file, false);
            bw = new BufferedWriter(fw);
            bw.write(sb.toString());
            bw.flush();
            
            return modifiedLine.toArray(new String[0]);
            
        } catch (Exception ex) {
            Logger.getLogger(Funciones.class.getName()).log(Level.SEVERE, null, ex);
            return new String[]{"Error: " + ex.getMessage()};
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
                if (fr != null) {
                    fr.close();
                }
                if (bw != null) {
                    bw.close();
                }
                if (fw != null) {
                    fw.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(Funciones.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
}
