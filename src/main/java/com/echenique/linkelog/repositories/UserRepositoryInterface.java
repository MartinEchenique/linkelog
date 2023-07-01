package com.echenique.linkelog.repositories;

import com.echenique.linkelog.models.UserProfile;

public interface UserRepositoryInterface {
    void addNewProfile(UserProfile user);

    void addNewProfile(String firstName, String lastName, String companyName, String profilePictureUrl, String role);

    UserProfile getProfileById(int id);
}
