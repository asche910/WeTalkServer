package com.asche.wetalk.controller;

import com.asche.wetalk.common.CommonResult;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.BufferedImageHttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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
                            "getImage", path.getFileName().toString()).build().toString())
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
    public CommonResult handleUpload(@RequestParam("upload_file") MultipartFile file, RedirectAttributes redirectAttributes) {
        String fileName = file.getOriginalFilename();

        println("POST ---> " + fileName);
        try {
            Files.copy(file.getInputStream(), rootLocation.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
            redirectAttributes.addFlashAttribute("message", "You upload it successfully!");

        } catch (IOException e) {
            e.printStackTrace();

            redirectAttributes.addFlashAttribute("message", "You failed!!");
        }
        return CommonResult.success("upload success!", "/files/" + fileName);
    }

    /**
     * 图片资源映射
     *
     * @param fileName
     * @return
     */
    @RequestMapping(value = "/files/{fileName:.+}",produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public byte[] getImage(@PathVariable String fileName) throws IOException {
        FileInputStream inputStream = new FileInputStream(new File(rootLocation.toString() + "/" + fileName));
        byte[] bytes = new byte[inputStream.available()];
        inputStream.read(bytes, 0, inputStream.available());
        return bytes;
    }
}
