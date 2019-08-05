package com.dt.baseapplication.model;

import com.google.gson.annotations.Expose;

/**
 * File Description
 * ------------------
 * Author : Feby Varghese
 * Email : feby@dt.ae
 * Date : 8/4/2019
 * Project : BaseApplication
 */
public class User {
    @Expose
    public int UserUno;
    @Expose
    public String UserEmailID;
    @Expose
    public int UserTypeUno;
    @Expose
    public String UserMobileNumber;
    @Expose
    public int PreferredRegionUno;
    @Expose
    public int PreferredDepartmentUno;
    @Expose
    public int PreferredSectionUno;
    @Expose
    public int PreferredUnitUno;
    @Expose
    public String PermittedRegions;
    @Expose
    public String PermittedDepartments;
    @Expose
    public String PermittedSections;
    @Expose
    public String PermittedUnits;
    @Expose
    public int CompanyUno;
    @Expose
    public String FullName;
    @Expose
    public String UserTypeName;
    @Expose
    public int OperationTypeUno;
    @Expose
    public int CustomerUno;
    @Expose
    public String RegionName;
    @Expose
    public String DepartmentName;
    @Expose
    public String SectionName;
    @Expose
    public String UnitName;
    @Expose
    public String CompanyName;
    @Expose
    public boolean Status;
    @Expose
    public String Message;
    public boolean IsApprovedPaymentGatewayToken;
    @Expose
    public String PaymentGatewayTokenID;
    @Expose
    public String UpdateURL;
    @Expose
    public int VersionCode;
    @Expose
    public boolean IsAppStoreURL;
    @Expose
    public int EmailVerificationCode;
    @Expose
    public int SMSVerificationCode;
    @Expose
    public boolean IsEmailVerified;
    @Expose
    public boolean IsSMSVerified;
    @Expose
    public boolean IsPasswordChangeRequired;
    @Expose
    public int UserCategoryUno;
    @Expose
    public int LanguageUno;
    @Expose
    public String AuthKey;
    @Expose
    public boolean isLoggedIn = false;
    @Expose
    public String AttachmentPath;
    @Expose
    public String Rights;
    @Expose
    public String Modules;
    @Expose
    public String UserImageFile;
    @Expose
    public long LoggedInDateTime;


}
