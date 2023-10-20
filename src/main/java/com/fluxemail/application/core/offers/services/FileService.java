package com.fluxemail.application.core.offers.services;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@Slf4j
public class FileService {

    private final Path root = Paths.get("uploads");

    public String saveFile( Long offerId , String type , MultipartFile file ){
        try {
            String random = UUID.randomUUID().toString().substring(0, 8);
            var fileNameExt = getFileExtension(file.getOriginalFilename());
            var fileName = String.format("image_%d_%s_%s.%s", offerId , random, type , fileNameExt);

            Files.copy(file.getInputStream(), this.root.resolve( fileName ));
            return this.root.resolve( fileName ).toString();
        } catch (Exception e) {
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
        }
    }

    private String getFileExtension( String fileName ){
        var fileNameExt = fileName.split("\\.");
        return fileNameExt[ fileNameExt.length - 1 ];
    }

    public Resource load(String filename) {
        try {
            Path file = root.resolve(filename);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    @PostConstruct
    public void init() {

        try {
            if( !Files.exists(root) ){
                Files.createDirectory(root);
                log.info(" Directory "+ root +" created  ");
                return;
            }
            log.info( root +" Already exists  " );

        } catch (IOException e) {
            throw new RuntimeException("Could not initialize folder for upload!");
        }
    }


}
