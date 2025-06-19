package com.example.Radhebe.Entity;


// CartStatus.java
public enum CartStatus {
    PENDING("Pending"),
    ORDERED("Ordered"),
    COMPLETED("Completed");

    private final String displayName;

    CartStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
