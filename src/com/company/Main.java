package com.company;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.Scanner;

public class Main {
    private static String filePath;
    private static String line = null;

    public static void main(String[] args) {
        filePath = fileOpener();
        System.out.println("1: Copy text in whatsApp\n2: Email Text in whatsApp");
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.close();
        if (n==1){
            System.out.println("Copy");
            copyText(filePath);
        }else if (n==2){
            System.out.println("Email");
            emailText(filePath);
        }else {
            System.out.println("Please select valid option");
        }
    }

    private static void copyText(String filePath) {
        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while ((line = bufferedReader.readLine()) != null) {
                String date = null;
                String time = null;
                String name = null;
                String message = null;
                date = line.split("]")[0];
                time = line.split("]")[0];
                date = date.split(",")[0].replace("[", "");
                time = time.split(",")[1];
                name = line.split("]")[1].split(":")[0];
                message = line.split("]")[1].split(":")[1];
                System.out.println("Date: " + date + " Time:" + time + " Name: " + name + " Message: " + message);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Buffer Reader: " + e.getMessage());
        }
    }

    private static void emailText(String filePath){
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while ((line=bufferedReader.readLine())!=null){
                String date = line.split(",")[0];
                String time = line.split(",")[1].split("-")[0];
                String name = line.split(",")[1].split("-")[1].split(":")[0];
                String message = line.split(",")[1].split("-")[1].split(":")[1];

                //System.out.println(line.split(",")[1].split("-")[1].split(":").length);
                System.out.println("Date: "+date + " Time:" + time + " Name:"+name + " Message:" + message);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static String fileOpener(){
        String path = null;
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter ( "text files " , "txt");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(null);
        if (returnVal==JFileChooser.APPROVE_OPTION){
            System.out.println("File Name: "+chooser.getSelectedFile().getName());
            System.out.println("File Path: "+chooser.getSelectedFile().getPath());
            path = chooser.getSelectedFile().getPath();
        }else {
            System.out.println("You did not choose any file");
            System.exit(0);
        }
        return path;
    }
}
