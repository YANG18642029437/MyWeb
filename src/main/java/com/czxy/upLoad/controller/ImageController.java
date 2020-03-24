package com.czxy.upLoad.controller;

import com.czxy.upLoad.config.ImageConfig;
import com.czxy.vo.BaseResult;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * 图片上传 Controller
 */
@RestController
@RequestMapping("image")
public class ImageController {

    @Resource
    private ImageConfig imageConfig;
    /**
     * @param images
     * @return
     */
    @PostMapping("upLoad")
    public BaseResult upLoadImage(MultipartFile images) throws IOException {
//        System.out.println(images.getContentType());
        String imagePath = imageConfig.imagePath+"/"+images.getOriginalFilename();
        File imageFile = new File(imagePath);
//        System.out.println(imageFile.getAbsolutePath());
        images.transferTo(imageFile);
        String url = "http://localhost:8081/image/getImage/" + images.getOriginalFilename();
        return BaseResult.ok("图片上传成功",url);
    }


    @GetMapping(value = "getImage/{imagePath}",produces = {MediaType.IMAGE_JPEG_VALUE,MediaType.IMAGE_GIF_VALUE,MediaType.IMAGE_PNG_VALUE,})
    public byte[] getImage(@PathVariable("imagePath") String imagePath) throws Exception {
        File file = new File(imageConfig.imagePath,imagePath);
        FileInputStream inputStream = new FileInputStream(file);
        byte[] arr = new byte[inputStream.available()];
        inputStream.read(arr,0,inputStream.available());
        return arr;
    }
}
