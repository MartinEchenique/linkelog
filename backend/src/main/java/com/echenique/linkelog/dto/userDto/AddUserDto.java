package com.echenique.linkelog.dto.userDto;


import com.echenique.linkelog.exceptions.FailedValidationException;

public class AddUserDto {
    private String firstName;
    private String lastName;
    private String companyName;
    private String profilePictureUrl;
    private String role;
    private String username;
    private String password;

    public AddUserDto() {
    }

    public AddUserDto(String firstName, String lastName, String companyName, String profilePictureUrl, String role, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.companyName = companyName;
        this.profilePictureUrl = profilePictureUrl;
        this.role = role;
        this.username = username;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void validate() throws FailedValidationException {
        final String pswPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$";
        String error = "";
        if(this.firstName.length() < 3) error += " - first name must have 3 or more characters";
        if(this.lastName.length() < 3) error += " - last name must have 3 or more characters";
        if(this.username.length() < 3) error += " - username must have 3 or more characters";
        if(!this.password.matches(pswPattern)) error+= " - non valid password";

        if(!error.equals("")) throw new FailedValidationException(error);
    }
}
