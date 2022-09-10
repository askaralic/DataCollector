package com.dt.baseapplication.utils;


import android.content.Context;
import android.os.Environment;

import com.dt.baseapplication.R;
import com.dt.baseapplication.model.Persons;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;

import org.apache.poi.util.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.FileOutputStream;
import java.util.List;


public class ExcelHelper {
    private static String[] columns = {"Family ID", "Full Name", "Father Name", "Mother Name", "Spouse Name","Blood Group","Date Of Birth","Member Status","Primary Mobile","Alternative Mobile","Anbiyam","House No","EB Number","Water Contract No","Marital Status","Date of marriage","Education","Society Names","Occupation","Company Name","Working Place","Working Country","Proof Of Identity","Adhar Number"};
    public static void writeToFile(String fileName, List<Persons> personsList, Context context) {
        try {

            String dirPath;
            dirPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).getAbsolutePath() + "/DataCollector/";
            File dir = new File(dirPath);
            if (!dir.exists()) {
                dir.mkdirs();
            }


            File file = new File(dirPath, fileName + ".xlsx");
            if (!file.exists()) {
                file.createNewFile();
            }

            Workbook workbook  = new XSSFWorkbook(file);

           // Workbook workbook = new XSSFWorkbook(); // new HSSFWorkbook() for generating `.xls` file
            CreationHelper createHelper = workbook.getCreationHelper();

            Sheet sheet = workbook.getSheet("Persons");
            if (sheet == null) {
                sheet = workbook.createSheet("Persons");

                // Create a Font for styling header cells
                Font headerFont = workbook.createFont();
                headerFont.setBold(true);
                headerFont.setFontHeightInPoints((short) 14);
                headerFont.setColor(IndexedColors.RED.getIndex());

                // Create a CellStyle with the font
                CellStyle headerCellStyle = workbook.createCellStyle();
                headerCellStyle.setFont(headerFont);

                // Create a Row
                Row headerRow = sheet.createRow(0);

                // Create cells
                for (int i = 0; i < columns.length; i++) {
                    Cell cell = headerRow.createCell(i);
                    cell.setCellValue(columns[i]);
                    cell.setCellStyle(headerCellStyle);
                }

                // Create Cell Style for formatting Date


                // Create Other rows and cells with personsList data
            }
            int rowNum = sheet.getPhysicalNumberOfRows();
            ;
            for (Persons persons : personsList) {
                Row row = sheet.createRow(++rowNum);
                row.setHeight((short) 1000);

                row.createCell(0).setCellValue(persons.FamilyID);
                row.createCell(1).setCellValue(persons.FullName);
                row.createCell(2).setCellValue(persons.FatherName);
                row.createCell(3).setCellValue(persons.MotherName);
                row.createCell(4).setCellValue(persons.SpouseName);
                row.createCell(5).setCellValue(persons.BloodGroup);

                CellStyle dateCellStyle = workbook.createCellStyle();
                dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd-MM-yyyy"));
                Cell dateOfBirthCell = row.createCell(6);
                dateOfBirthCell.setCellValue(persons.DateOfBirth.toDate());
                dateOfBirthCell.setCellStyle(dateCellStyle);

                row.createCell(7).setCellValue(persons.MemberStatus);

                row.createCell(8).setCellValue(persons.PrimaryMobile);
                row.createCell(9).setCellValue(persons.AlternativeMobile);
                row.createCell(10).setCellValue(persons.Anbiyam);
                row.createCell(11).setCellValue(persons.HouseNo);
                row.createCell(12).setCellValue(persons.EBNumber);
                row.createCell(13).setCellValue(persons.WaterContractNo);
                row.createCell(14).setCellValue(persons.MaritalStatus);


                Cell dateOfMarriageCell = row.createCell(15);
                dateOfMarriageCell.setCellValue(persons.DateOfMarriage.toDate());
                dateOfMarriageCell.setCellStyle(dateCellStyle);

                row.createCell(16).setCellValue(persons.Education);
                row.createCell(17).setCellValue(persons.SocietyNames);
                row.createCell(18).setCellValue(persons.Occupation);
                row.createCell(19).setCellValue(persons.CompanyName);
                row.createCell(20).setCellValue(persons.WorkingPlace);
                row.createCell(21).setCellValue(persons.WorkingCountry);


                InputStream proofOfIdentityStream = new FileInputStream(persons.ProofOfIdentity);
                byte[] bytes = IOUtils.toByteArray(proofOfIdentityStream);
                int picture_id = workbook.addPicture(bytes, Workbook.PICTURE_TYPE_JPEG);
                proofOfIdentityStream.close();
                XSSFDrawing drawing = (XSSFDrawing) sheet.createDrawingPatriarch();
                XSSFClientAnchor anchor = new XSSFClientAnchor();
                anchor.setCol1(22);
                anchor.setRow1(rowNum - 1);
                anchor.setCol2(23);
                anchor.setRow2(rowNum);
                XSSFPicture my_picture = drawing.createPicture(anchor, picture_id);

                row.createCell(23).setCellValue(persons.AdharNumber);
            }

            // Resize all columns to fit the content size
            for (int i = 0; i < columns.length; i++) {
                int widthUnits = 20 * 256;
                sheet.setColumnWidth(1, widthUnits);

            }


            FileOutputStream fileOut = new FileOutputStream(file);
            workbook.write(fileOut);
            fileOut.close();

            // Closing the workbook
            workbook.close();

        }catch (Exception exception){

        }
    }

}