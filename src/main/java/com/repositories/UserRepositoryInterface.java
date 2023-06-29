package com.repositories;

import com.models.UserProfile;

public interface UserRepositoryInterface {
    void addNewProfile(UserProfile user);

    void addNewProfile(String firstName, String lastName, String companyName, String profilePictureUrl, String role);

    UserProfile getProfileById(java.lang.Long id);
}
