package com.qijianguo.springcloud.controller;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

/**
 * @author qijianguo
 */
@RestController
@RequestMapping("/file")
public class FileController {

    @PostMapping("/upload")
    public String upload(@RequestParam("file")MultipartFile file) {
        try {
            byte[] bytes = file.getBytes();
            File fileToSave = new File(file.getOriginalFilename());
            FileCopyUtils.copy(bytes, fileToSave);
            return fileToSave.getAbsolutePath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
