package com.commons.enums;

public enum NUIAttachmentId {

    BinLocationImage("S11"), DeviceMessageImage("S10"), CompanyLogo("S9"), VehicleTypeAttachment("S8"), CheckListDetails("S7"), WMSServiceImage("S6"), CommonAttachment("S5"), AttendantAttachment("S4"), StudentAttachment("S3"), DriverAttachment("S2"), UserAttachment("S1"), GuardianAttachment("S19"), ExaminerProfileAttachment("S12"),
    OrderPermitAttachment("S20"),;

    private final String value;

    NUIAttachmentId(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
