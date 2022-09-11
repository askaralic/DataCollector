package com.dt.datacollector.model;

import org.joda.time.DateTime;

import java.io.File;

public class Persons {
    public  String FamilyID;
    public  String FullName;
    public  String FatherName;
    public  String MotherName;
    public  String SpouseName;
    public  String BloodGroup;
    public  DateTime DateOfBirth;
    public  String MemberStatus;
    public  String PrimaryMobile;
    public  String AlternativeMobile;
    public  String Anbiyam;
    public  String HouseNo;
    public  String EBNumber;
    public  String WaterContractNo;
    public  String MaritalStatus;
    public  DateTime DateOfMarriage;
    public  String Education;
    public  String SocietyNames;
    public  String Occupation;
    public  String CompanyName;
    public  String WorkingPlace;
    public  String WorkingCountry;
    public  File ProofOfIdentity;
    public  String AdharNumber;


    public Persons(String familyID, String fullName, String fatherName, String motherName, String spouseName, String bloodGroup, DateTime dateOfBirth, String memberStatus, String primaryMobile, String alternativeMobile, String anbiyam, String houseNo, String ebNumber, String waterContractNo, String maritalStatus, DateTime dateOfMarriage, String education, String societyNames, String occupation, String companyName, String workingPlace, String workingCountry, File proofOfIdentity, String adharNumber) {
        FamilyID = familyID;
        FullName = fullName;
        FatherName = fatherName;
        MotherName = motherName;
        SpouseName = spouseName;
        BloodGroup = bloodGroup;
        DateOfBirth = dateOfBirth;
        MemberStatus = memberStatus;
        PrimaryMobile = primaryMobile;
        AlternativeMobile = alternativeMobile;
        Anbiyam = anbiyam;
        HouseNo = houseNo;
        EBNumber = ebNumber;
        WaterContractNo = waterContractNo;
        MaritalStatus = maritalStatus;
        DateOfMarriage = dateOfMarriage;
        Education = education;
        SocietyNames = societyNames;
        Occupation = occupation;
        CompanyName = companyName;
        WorkingPlace = workingPlace;
        WorkingCountry = workingCountry;
        ProofOfIdentity = proofOfIdentity;
        AdharNumber = adharNumber;
    }
}
