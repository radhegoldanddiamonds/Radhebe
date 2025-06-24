package com.example.Radhebe.Entity;

// UserType.java
public enum UserType {
    Admin("Admin"),
    Retailer("Retailer"),
    Wholesaler("Wholesaler");

    private final String displayName;

    UserType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
