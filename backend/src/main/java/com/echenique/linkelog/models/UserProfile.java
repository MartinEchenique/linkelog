package com.echenique.linkelog.models;

public class UserProfile {
    private int userId;
    private String firstName;
    private String lastName;
    private String companyName;
    private String profilePictureUrl;
    private String role;

    public UserProfile(){};
    public UserProfile(int userId, String firstName, String lastName) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.companyName = "";
        this.profilePictureUrl = "";
        this.role = "";
    }

    public UserProfile(int userId, String firstName, String lastName, String companyName) {
        this(userId, firstName, lastName);
        this.companyName = companyName;
        this.profilePictureUrl = "";
        this.role = "";
    }

    public UserProfile(int userId, String firstName,
                       String lastName, String companyName, String profilePictureUrl, String role) {
        this(userId, firstName, lastName, companyName);
        this.profilePictureUrl = profilePictureUrl;
        this.role = role;
    }

    public int getUserId() {
        return userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getProfilePictureUrl() {
        return profilePictureUrl;
    }

    public void setProfilePictureUrl(String profilePictureUrl) {
        this.profilePictureUrl = profilePictureUrl;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
