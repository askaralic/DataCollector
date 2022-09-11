package com.commons.enums;

import static com.commons.enums.GenderType.Male;

public enum MappingFor {
    WorkshopInspectionProcessRequest(143), StopPointRequest(127), RequestForStudentCardPrintConfiguration(126), WayBillSenderSignature(39), WayBillReceiverSignature(44), ContainerPhotoAttachment(40);

    private final int value;

    MappingFor(int value) {
        this.value = value;
    }

    public static String getName(int value) {
        for (MappingFor v : values()) {
            if (v.getValue() == value) {
                return v.name();
            }
        }
        return Male.name();
    }

    public int getValue() {
        return this.value;
    }
}
