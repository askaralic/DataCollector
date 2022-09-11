package com.dt.datacollector.utils;

import android.content.Context;
import android.os.Environment;

import com.dt.datacollector.model.Persons;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.commons.utils.TimeUtils;

public class CSVParser {
    private static String[] columns = {"Family ID", "Full Name", "Father Name", "Mother Name", "Spouse Name","Blood Group","Date Of Birth","Member Status","Primary Mobile","Alternative Mobile","Anbiyam","House No","EB Number","Water Contract No","Marital Status","Date of marriage","Education","Society Names","Occupation","Company Name","Working Place","Working Country","Proof Of Identity","Adhar Number"};
    public static void saveEmployeeToCSVFile(String fileName, List<Persons> personsList, Context context) {

        int folderStatus = 0;
        try {
            String fileDirPath;
            fileDirPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).getAbsolutePath() + "/DataCollector/";
            File fileDir = new File(fileDirPath);
            if (!fileDir.exists()) {
                fileDir.mkdirs();
            }

            String photosDirPath;
            photosDirPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).getAbsolutePath() + "/DataCollector/Attachments/";
            File photosDir = new File(photosDirPath);
            if (!photosDir.exists()) {
                photosDir.mkdirs();
            }

            File file = new File (fileDir,fileName+".csv");
            if(!file.exists()){
                folderStatus = file.createNewFile()?1:0;
            }else{
                folderStatus = 3;
            }

            /*first time so create heading*/
            if(folderStatus ==1){
                StringBuilder columnEntries= new StringBuilder();
                for (String columnName : columns) {
                    columnEntries.append(columnName).append(",");
                }
                writeToCSV(file, columnEntries.toString());
            }

            String splitter = ",";

            StringBuilder rawEntries= new StringBuilder();
            for (Persons persons : personsList) {
                rawEntries = new StringBuilder(rawEntries + persons.FamilyID + splitter + persons.FullName + splitter + persons.FatherName + splitter +persons.MotherName +splitter + persons.SpouseName + splitter + persons.BloodGroup + splitter + TimeUtils.dateFmt.print(persons.DateOfBirth) + splitter + persons.MemberStatus + splitter + persons.PrimaryMobile + splitter + persons.AlternativeMobile+ splitter + persons.Anbiyam+ splitter + persons.HouseNo+ splitter + persons.EBNumber+ splitter + persons.WaterContractNo+ splitter + persons.MaritalStatus+ splitter + TimeUtils.dateFmt.print(persons.DateOfMarriage)+ splitter + persons.Education+ splitter + persons.SocietyNames+ splitter + persons.Occupation+ splitter + persons.CompanyName + splitter + persons.WorkingPlace + splitter + persons.WorkingCountry+ splitter + persons.ProofOfIdentity+ splitter + persons.AdharNumber);
              /*  File renamedFile = new File(persons.PrimaryMobile +000+persons.DateOfBirth.getMillis());
                persons.ProofOfIdentity.renameTo(renamedFile);*/
                FileUtils.copyFileToDirectory(persons.ProofOfIdentity, photosDir);
            }
            writeToCSV(file,rawEntries.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    private static void writeToCSV(File file,String content) {
        try {
            FileWriter fw = new FileWriter(file.getAbsoluteFile(),true);
            fw.write(content + "\n");
            fw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
