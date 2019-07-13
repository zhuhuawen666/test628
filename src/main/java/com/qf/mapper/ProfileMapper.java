package com.qf.mapper;

import com.qf.pojo.Profile;

public interface ProfileMapper {
    Profile getProfile(int id);
    int updateProfile(Profile profile);
    int deleteProfile(int id);
}
