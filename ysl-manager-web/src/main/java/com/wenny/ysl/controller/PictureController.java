package com.wenny.ysl.controller;

import com.wenny.ysl.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import utils.JsonUtils;

import java.util.Map;

@Controller
public class PictureController {

    @Autowired
    private PictureService pictureService;

    @RequestMapping("/pic/upload")
    @ResponseBody
    public String pictureUpload(MultipartFile uploadFile){
        Map result = pictureService.uploadPicture(uploadFile);
        String json = JsonUtils.objectToJson(result);
        return json;
    }
}
