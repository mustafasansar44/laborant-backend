package com.msansar.laborant.service;

import com.msansar.laborant.exception.FileCouldNotBeDeletedException;
import com.msansar.laborant.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class ImageService {

    @Value("${project_images_path}")
    private String imagesPath;

    protected String saveImage(MultipartFile image) throws IOException {    // Verilen resmin ismini değiştirip belirttiğimiz klasöre kaydeder.
        if (image.isEmpty()) {
            throw new NotFoundException("Lütfen resim yükleyiniz!");
        }
        String fileName = UUID.randomUUID()+"."+StringUtils.getFilenameExtension(image.getOriginalFilename());
        Path filePath = Paths.get(imagesPath+fileName);

        Files.copy(image.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        return fileName;       // işlemler bitince yeni dosya ismini döner
    }

    protected String deleteImage(String fileName) throws FileCouldNotBeDeletedException {
        File imageFile = new File(imagesPath + fileName);
        boolean isDeleted = imageFile.delete();
        if (!isDeleted) {
            throw new FileCouldNotBeDeletedException("Dosya silinemedi!");
        }
        return "Dosya silindi!";
    }
}
