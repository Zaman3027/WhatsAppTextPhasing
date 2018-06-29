package com.company;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.util.Scanner;

public class Main {
    private static String filePath;
    private static String line = null;
    private static PDDocument document;
    private static PDPage page;
    private static PDPageContentStream pdPageContentStream;
    private static AnalyticClass analyticClass;

    public static void main(String[] args) throws IOException {
        filePath = fileOpener();
        String fileName = "PdfWithtext.pdf";
        System.out.println("1: Copy text in whatsApp\n2: Email Text in whatsApp");
        Scanner scanner = new Scanner(System.in);
        document = new PDDocument();
        page = new PDPage();
        document.addPage(page);
        pdPageContentStream = new PDPageContentStream(document,page,true,true);
        analyticClass = new AnalyticClass();
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
        pdPageContentStream.close();
        document.save(fileName);
        document.close();
    }

    private static void copyText(String filePath) {
        int z = 700;
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
                message = line.split(":")[2];
                int n =0;
                if ((n= line.split(":").length)>3){
                    //System.out.println("Length: "+n);
                    for (int i = 3; i<n; i++){
                        message = message +":"+ line.split(":")[i];
                    }
                }
                System.out.println("Date: "+date + " Time:" + time + " Name:"+name + " Message:" + message);
                writeLineInPdf(name,message,z);
                z = z+20;
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
        int z = (int) page.getBBox().getHeight()-30;
        int totalWords=0;
        try {
            fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while ((line=bufferedReader.readLine())!=null){
                String date = line.split(",")[0];
                String time = line.split(",")[1].split("-")[0];
                String name = line.split(",")[1].split("-")[1].split(":")[0];
                String message = line.split(":")[2];
                int n;
                if ((n= line.split(":").length)>3){
                    for (int i = 3; i<n; i++){
                        message = message +":"+ line.split(":")[i];
                    }
                }

                //if (message.length()>)
                //System.out.println(message.length());
                //System.out.println("Date: "+date + " Time:" + time + " Name:"+name + " Message:" + message);
                System.out.println(message);
                totalWords =totalWords+ analyticClass.countWord(message);

                writeLineInPdf(name,message,z);
                z = z-20;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Total Word: "+totalWords);
    }
    private static void writeLineInPdf(String name,String message,int n) throws IOException {
        pdPageContentStream.beginText();
        pdPageContentStream.setFont(PDType1Font.COURIER_BOLD,8);
        pdPageContentStream.moveTextPositionByAmount(80,n);
        pdPageContentStream.drawString(name+":  "+message);
        pdPageContentStream.endText();
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
