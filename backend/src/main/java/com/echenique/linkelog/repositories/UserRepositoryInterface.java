package com.echenique.linkelog.repositories;

import com.echenique.linkelog.models.UserProfile;

import java.util.Optional;

public interface UserRepositoryInterface {

    void addNewProfile(String firstName,
                       String lastName,
                       String companyName,
                       String roleString,
                       String profilePicture,
                       String userName,
                       String password);

    Optional<UserProfile> getProfileById(int id);

    Optional<UserProfile> getProfileByUsername(String username);

    int editUserRole(String newRole, int id);

    int editUserCompany(String newCompany, int id);

    int editUserCompanyRole(String newRole, String newCompany, int i);

    int editUserPicture(String newCompany, int i);

    int editUserPassword(String newpass, int i);
}
