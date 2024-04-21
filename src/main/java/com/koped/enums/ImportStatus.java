package com.koped.enums;

import lombok.Getter;
@Getter

public enum ImportStatus {
    PLACED("PLACED"),
    ACCEPTED("ACCEPTED"),
    DECLINED("DECLINED"),
    PROCESSED("PROCESSED"),
    SHIPPED("SHIPPED"),
    DELIVERED("DELIVERED"),
    FINISHED("FINISHED"),
    CANCELLED("CANCELLED");

    private final String value;

    private ImportStatus(String value) {
        this.value = value;
    }

    public static boolean contains(String param) {
        for (ImportStatus importStatus : ImportStatus.values()) {
            if (importStatus.name().equals(param)) {
                return true;
            }
        }
        return false;
    }
}