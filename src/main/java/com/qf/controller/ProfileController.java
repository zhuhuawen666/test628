package com.qf.controller;

import com.qf.pojo.Profile;
import com.qf.service.ProfileService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@RequestMapping("profile")
@Controller
public class ProfileController {
    @Autowired
    private ProfileService profileService;
    @RequestMapping("get")
    public String getProfileMapper(Model model,int id){

        Profile profile = profileService.getProfile(id);
        model.addAttribute("profile",profile);

        return "singleProfile";
    }
    @RequestMapping("update")
    public String update(Model model,int id){
        Profile profile = profileService.getProfile(id);
        model.addAttribute("profile",profile);
        return "updateP";
    }
    @RequestMapping(value = "update1",method = RequestMethod.POST,produces = "text/html;charset=utf-8")
    public String update(@RequestParam(value = "file",required = false) MultipartFile multipartFile, HttpServletRequest request,int id, String name, String birthday, String gender, String carrer, String address, String mobile) throws IOException {


        String filename = multipartFile.getOriginalFilename();

        Profile pro = new Profile(id,name,birthday,gender,carrer,address,Integer.parseInt(mobile),filename);
        profileService.updateProfile(pro);
        File file = new File(request.getServletContext().getRealPath("/upload"));
        if (!file.exists()){
            file.mkdir();
        }
        File file2 = new File(file,filename);
        multipartFile.transferTo(file2);

        return "redirect:../user/list";
    }
    @RequestMapping("singleP")
    public String getSingleP(Model model,int id){
        Profile profile = profileService.getProfile(id);
        model.addAttribute("profile",profile);
        return "singleProfile";

    }
    @RequestMapping(value = "delete",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String deleteProfile(int id){
        JSONObject jsonObject = new JSONObject();
        profileService.deleteProfile(id);
        jsonObject.element("resp","删除成功");
        return jsonObject.toString();

    }
}
