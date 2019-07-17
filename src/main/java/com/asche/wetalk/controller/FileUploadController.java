package com.asche.wetalk.controller;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.asche.wetalk.util.PrintUtils.println;

@Controller
public class FileUploadController {

    private static Path rootLocation;

    static {
        if (rootLocation == null) {
            rootLocation = Paths.get("upload-dir");
            try {
                Files.createDirectories(rootLocation);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @GetMapping("/fileUpload")
    public String listUploadedFiles(Model model) {

        try {
            Stream<Path> stream = Files.walk(rootLocation, 1)
                    .filter(path -> !path.equals(rootLocation))
                    .map(rootLocation::relativize);

            model.addAttribute("files", stream
                    .map(path -> MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
                            "getFile", path.getFileName().toString()).build().toString())
                    .collect(Collectors.toList()));

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "uploadForm";
    }

    /**
     * 上传图片处理
     *
     * @param file
     * @param redirectAttributes
     * @return
     */
    @PostMapping(value = "/fileUpload", produces = "application/json")
    @ResponseBody
    public String handleUpload(@RequestParam("upload_file") MultipartFile file, RedirectAttributes redirectAttributes) {
        String fileName = file.getOriginalFilename();

        println("POST ---> " + fileName);
        try {
            Files.copy(file.getInputStream(), rootLocation.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
            redirectAttributes.addFlashAttribute("message", "You upload it successfully!");

        } catch (IOException e) {
            e.printStackTrace();

            redirectAttributes.addFlashAttribute("message", "You failed!!");
        }
        return "{\"code\":0, \"status\": \"success\", \"uri\": \"/files/" + fileName + "\" }";
    }

    /**
     * 图片资源映射
     *
     * @param fileName
     * @return
     */
    @GetMapping("/files/{fileName}")
    @ResponseBody
    public ResponseEntity<Resource> getFile(@PathVariable String fileName) {
        println("GET ---> " + fileName);

        Path path = rootLocation.resolve(fileName);
        try {
            Resource resource = new UrlResource(path.toUri());

            if (resource.exists() || resource.isReadable()) {
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment: filename=\"" + fileName + "\"")
                        .header(HttpHeaders.CONTENT_TYPE, "image/png")
                        .body(resource);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        println("-------------> failed!");
        return null;
    }
}
