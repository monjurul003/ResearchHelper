/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uofm.mik.logic;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;

/**
 *
 * @author mik
 */
public class CongestionWindow {

    private double time;

    private int cnwd;

    /**
     * ****************Get number of line in a file *******************
     */
    public int getLineCountInFile(String fileLocation, String fileName) {

        try {

            File file = new File(fileLocation + File.separator + fileName);

            if (file.exists()) {

                FileReader fr = new FileReader(file);
                LineNumberReader lnr = new LineNumberReader(fr);

                int linenumber = 0;

                while (lnr.readLine() != null) {
                    linenumber++;
                }

                System.out.println("Total number of lines : " + linenumber);

                lnr.close();
                return linenumber;

            } else {
                System.out.println("File does not exists!");
                return 0;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;

    }

    /**
     * ******************************* return file in a String Array
     * *********************************
     */
    public String[] readFileAsAStringArray(String fileLocation, String fileName) {
        BufferedReader br = null;
        int lineCount = getLineCountInFile(fileLocation, fileName);
        String[] fileInString = new String[lineCount];
        try {

            String sCurrentLine;

            br = new BufferedReader(new FileReader(fileLocation + File.separator + fileName));
            int i = 0;
            while ((sCurrentLine = br.readLine()) != null) {

                fileInString[i] = sCurrentLine;
                i++;
            }

            return fileInString;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    public void writeStringArrayInFile(String filePath, String fileName, String[] strArr) {
        try {
            File file = new File(filePath + File.separator + fileName);

            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            for (int i = 0; i < strArr.length; i++) {
                bw.write(strArr[i].trim() + "\n");
            }
            bw.close();

            System.out.println("Done");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
