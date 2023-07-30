package com.echenique.linkelog.repositories;

import com.echenique.linkelog.models.UserProfile;

public interface UserRepositoryInterface {

    void addNewProfile(String firstName,
                       String lastName,
                       String companyName,
                       String roleString,
                       String userName,
                       String password);

    UserProfile getProfileById(int id);

    UserProfile getProfileByUsername(String username);

    int editUserRole(String newRole, int id);

    int editUserCompany(String newCompany, int id);

    int editUserCompanyRole(String newRole, String newCompany, int i);

    int editUserPicture(String newCompany, int i);

    int editUserPassword(String newpass, int i);
}
