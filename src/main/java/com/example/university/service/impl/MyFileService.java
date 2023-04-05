package com.example.university.service.impl;

import com.example.university.entity.MyFile;
import com.example.university.exceptions.BadRequest;
import com.example.university.payload.Result;
import com.example.university.repository.MyFileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MyFileService {

    private final MyFileRepository repository;

    @Value("${upload}")
    private String downloadPath;


    public Result save(MultipartFile multipartFile) {

        MyFile myFile = new MyFile();

        myFile.setContentType(multipartFile.getContentType());
        myFile.setFileSize(multipartFile.getSize());
        myFile.setName(multipartFile.getOriginalFilename());
        myFile.setExtension(getExtension(myFile.getName()).toLowerCase());
        myFile.setHashId(UUID.randomUUID().toString());


        LocalDate date = LocalDate.now();

        // change value downloadPath
        String localPath = downloadPath + String.format(
                "/%d/%d/%d/%s",
                date.getYear(),
                date.getMonthValue(),
                date.getDayOfMonth(),
                myFile.getExtension().toLowerCase());

        myFile.setUploadPath(localPath);


        // downloadPath / year / month / day / extension
        File file = new File(localPath);

        // " downloadPath / year / month / day / extension "   crate directory
        file.mkdirs();

        // save MyFile into base
        myFile.setLink(myFile.getUploadPath()+"/"+myFile.getHashId()+"."+myFile.getExtension());
        repository.save(myFile);

        try {
            // copy bytes into new file or saving into storage
            multipartFile.transferTo(new File(file.getAbsolutePath() + "/" + String.format("%s.%s", myFile.getHashId(), myFile.getExtension())));
            myFile.setLink("https://booking-demo.herokuapp.com/"+file.getAbsolutePath() + "/" + String.format("%s.%s", myFile.getHashId(), myFile.getExtension()));
            repository.save(myFile);
            Map<Object, Object> data = new HashMap<>();
            data.put("hashId", myFile.getHashId());
            return new Result("File successfully saved!", true, data);

        } catch (IOException e) {
            e.printStackTrace();
            throw new BadRequest("File not saved!");
        }
    }

    private String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    public MyFile findByHashId(String hashId) {
        return repository.findByHashId(hashId);
    }

    @Transactional
    public Result delete(String hashId){
        MyFile myFile = findByHashId(hashId);
        File file = new File(String.format("%s/%s.%s", myFile.getUploadPath(), myFile.getHashId(), myFile.getExtension()));

        try {
            file.delete();
            repository.deleteByHashId(hashId);
            return Result.message("successful deleted",true);
        }catch (Exception e) {
            return Result.exception(e);
        }

    }

    public Page<String> getHashId(int page,int size){
        Pageable pageable= PageRequest.of(page,size);
        return repository.getHashId(pageable);
    }


}
