package com.qf.service;

import com.qf.pojo.Profile;

public interface ProfileService {
    Profile getProfile(int id);
    int updateProfile(Profile profile);
    int deleteProfile(int id);
}
