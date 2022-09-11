package com.commons.enums;

public enum MobileApplication {

    FleetMan(1), CabMan(2), SchoolParentApplication(3), ChauffeurManOrix(4),
    PublicBookingOrix(5), StudentStopPointPlotter(6), IMS(7), IMSRecovery(8),
    DPWFTMSCE(9), DPWFTMSAndroid(10), DPWForeman(11), Nanny(12),
    DTCParentPortal(13), BinAuditDM(14), BinAuditLJ(15), ETRSAS(16),
    BinAuditBH(17), RecoveryDispatch(18), WMSBeeah(19), WMSDispatchBeeah(20),
    WMSLavajet(21), WMSDispatchLavajet(22), RTAVDCS(23), DTCSchoolBusAndroid(24),
    ETRSASiOS(25), DTCSchoolBusiOS(26), RTAHelpDeskTv(27), MCWCSDTC(28),
    MCWCSENOC(29), STUKKRecovery(30), MfluidTest(31), CabmanOrix(32), DTCSmartBusPOC(33),
    RTAVSSFleetMan(34), TDMSFleetMan(35), GCFleetMan(36), RashidCentreParentApp(37),
    DMFTSFleetMan(38), DMRASIDFleetMan(39), ASPRecoveryDispatch(40), SchoolBusDriverASP(44),
    ArriveInStyleAndroid(48), AOGTPublicBooking(51), DTCCheckList(55), DTCheckList(56), FieldMan(57),
    AlghazalPublicBooking(58), DTPublicBooking(59), EuropcarPublicBooking(60), FTSServices(70);

    private final int value;

    MobileApplication(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
