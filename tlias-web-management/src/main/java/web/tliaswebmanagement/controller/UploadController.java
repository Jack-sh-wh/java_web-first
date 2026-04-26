package web.tliaswebmanagement.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import web.tliaswebmanagement.pojo.Result;

import java.io.File;
import java.util.UUID;

@Slf4j
@RestController
public class UploadController {

    @PostMapping("/upload")
    public Result upload(String name, Integer age, MultipartFile file) throws Exception {
        log.info("接收参数: {}, {}, {}", name, age, file);
        //获取上传文件名
        String fileName = file.getOriginalFilename();
        //获取文件扩展名
        String extension = fileName.substring(fileName.indexOf( "."));
        //通过UUID和文件拓展名创建新的文件名
        String newFileName = UUID.randomUUID().toString() + extension;

        file.transferTo(new File("D:/images/" + newFileName));
        return Result.success();
    }


}
