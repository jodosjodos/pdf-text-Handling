package org.example;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;

public class District {
    public static void main(String[] args) {
        Document document = new Document(PageSize.A4);
        Scanner scan = null;

        try {
            PdfWriter.getInstance(document, new FileOutputStream("district.pdf"));

            document.open();
            PdfPTable table = new PdfPTable(4);
            table.addCell(new PdfPCell(new Phrase("District code")));
            table.addCell(new PdfPCell(new Phrase("District Name")));
            table.addCell(new PdfPCell(new Phrase("Province code")));
            table.addCell(new PdfPCell(new Phrase("Province Name")));

            // Create the Scanner instance within the try block
            scan = new Scanner(new BufferedReader(new FileReader("districts.txt")));
            scan.useDelimiter("[,\n]");

            while (scan.hasNext()) {
                int districtCode = scan.nextInt();
                String districtName = scan.next();
                int provinceCode = scan.nextInt();
                String provinceName = scan.next();
                table.addCell(new PdfPCell(new Phrase(Integer.toString(districtCode))));
                table.addCell(new PdfPCell(new Phrase(districtName)));
                table.addCell(new PdfPCell(new Phrase(Integer.toString(provinceCode))));
                table.addCell(new PdfPCell(new Phrase(provinceName)));
            }
            document.add(table);
        } catch (FileNotFoundException | DocumentException e) {
            System.out.println(e);
        } finally {
            if (scan != null) {
                scan.close();
            }
            if (document != null) {
                document.close();
            }
        }
    }
}
