package com.qf.service;

import com.qf.mapper.ProfileMapper;
import com.qf.mapper.UserMapper;
import com.qf.pojo.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileServiceImpl implements ProfileService
{
    @Autowired
    ProfileMapper profileMapper;
    @Override
    public Profile getProfile(int id) {
        return profileMapper.getProfile(id);
    }

    @Override
    public int updateProfile(Profile profile) {
        return profileMapper.updateProfile(profile);
    }

    @Override
    public int deleteProfile(int id) {

        return  profileMapper.deleteProfile(id);

    }
}
