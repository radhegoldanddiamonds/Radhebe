package com.example.Radhebe.Entity;

// UserType.java
public enum UserType {
    ADMIN("Admin"),
    RETAILER("Retailer"),
    WHOLESALER("Wholesaler");

    private final String displayName;

    UserType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
