package com.example.university.controller.authController;

import com.example.university.entity.MyFile;
import com.example.university.payload.Result;
import com.example.university.service.impl.MyFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileUrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.MalformedURLException;
import java.net.URLEncoder;

@RestController
@RequestMapping("/api/auth/file")
@RequiredArgsConstructor
public class FileAuthController {
    private final MyFileService myFileService;

    @PostMapping("/save")
    public ResponseEntity<?> saveFile(@RequestParam(name = "file") MultipartFile multipartFile) {
        Result result = myFileService.save(multipartFile);
        return ResponseEntity.status(result.isStatus() ? 200 : 409).body(result);
    }

    @GetMapping("/download/{hashId}")
    public ResponseEntity<?> download(@PathVariable String hashId) throws MalformedURLException {
        MyFile myFile = myFileService.findByHashId(hashId);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; fileName=" + URLEncoder.encode(myFile.getName()))
                .contentType(MediaType.parseMediaType(myFile.getContentType()))
                .body(new FileUrlResource(String.format("%s/%s.%s", myFile.getUploadPath(), myFile.getHashId(), myFile.getExtension())));
    }

    @DeleteMapping("/delete/{hashId}")
    public ResponseEntity<?> delete(@PathVariable String hashId) throws MalformedURLException {
        Result result = myFileService.delete(hashId);
        return ResponseEntity.status(result.isStatus() ? 200 : 409).body(result);
    }

    @GetMapping("/get-all")
    public ResponseEntity<?> getAllHashId(@RequestParam("page") int page, @RequestParam("size") int size) {
        return ResponseEntity.ok(myFileService.getHashId(page, size));
    }

    @GetMapping("/{hashId}")
    public ResponseEntity<?> preview(@PathVariable String hashId) throws MalformedURLException {
        MyFile myFile = myFileService.findByHashId(hashId);
        return ResponseEntity.ok()
                .header(HttpHeaders.EXPIRES, "inline; fileName=" + URLEncoder.encode(myFile.getName()))
                .contentType(MediaType.parseMediaType(myFile.getContentType()))
                .body(new FileUrlResource(String.format("%s/%s.%s",
                        myFile.getUploadPath(),
                        myFile.getHashId(),
                        myFile.getExtension())));
    }
}
