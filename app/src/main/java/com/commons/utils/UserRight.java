package com.commons.utils;

/**
 * Created by Wajid on 16-11-2016.
 */

public enum UserRight {

    FleetmanFilteredMapMonitor("MOB1397"),WMSOfflineOperation("P780"), BinOnlyUpdateTagAndLocation("P653"), BinMasterMobile("P540"), CollectionPointMobile("P541"), BinIncidentMobile("P542"), VehicleMonitorMobile("P543"), VehicleAlertsDashboardMobile("P544"), BinIncidentDispatch("P549"), BinIncidentAssign("P577"), BinIncidentStatusChange("P578"), BinSurvey("P578"),
    FTSMobileVehicleRequest("P1183"), FTSMobileVehicleCheckList("P1185"), FTSMobileDynamicProcess("P1186"), FTSMobileDynamicProcessApproval("P1187"), FTSMobileGHDTickets("P1208"), FTSMobileVehicleMaintenance("P1209"), FTSMaintenanceCheckList("P1223"), FTSVehicleRequestApproval("P1249"), WMSNewBooking("P1249"), WMSBookings("P1339"),
    WMSVehicleCheckList("P1185"),WMSBinSurvey("P1338"), FieldManStoreManagerTagInventory("P1357"),
    FleetmanVehicleMonitor("MOB1386"), FleetmanVehicleUtilization("MOB1387"), FleetmanMapMonitor("MOB1388"), FleetmanVehicleAlerts("MOB1389"), FleetmanLandfill("MOB1390"), FleetmanRasidDashboard("MOB1391"), FleetmanVehicleEnquiry("MOB1392"), FleetmanCompanyRating("MOB1393"), FleetmanDriverRating("MOB1394"), FleetmanRestArea("MOB1395"), FleetmanPumpMonitor("MOB1396"),
    ;

    private final String value;

    UserRight(final String value) {
        this.value = value;
    }

    private String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return this.getValue();
    }

}
