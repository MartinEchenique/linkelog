package com.echenique.linkelog.dto;

public class UserDto {
    private int userId;
    private String firstName;
    private String lastName;
    private String companyName;
    private String profilePictureUrl;
    private String role;
    private String username;
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public UserDto() {
    }

    public UserDto(int userId, String firstName, String lastName, String companyName, String profilePictureUrl, String role, String username) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.companyName = companyName;
        this.profilePictureUrl = profilePictureUrl;
        this.role = role;
        this.username = username;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
