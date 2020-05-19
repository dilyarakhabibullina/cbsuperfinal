package ru.itpark.service;

import lombok.var;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Part;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

public class FileService {
    private final String uploadPath;

    public FileService() throws IOException {
        uploadPath = System.getenv("UPLOAD_PATH");
        Files.createDirectories(Paths.get(uploadPath));
    }

    //метод который по id позволит читать все в outputstream
    //чтобы мы клиенту картинку при гет-запросе отдавали
    public void readFile(String id, ServletOutputStream os) throws IOException {
        var path = (var) Paths.get(uploadPath).resolve(id);
        Files.copy((Path) path, os);
    }

    //будет записывать файл из мультпорта?
    public String writeFile(Part part) throws IOException {
        String id = UUID.randomUUID().toString();
        part.write(Paths.get(uploadPath).resolve(id).toString());
        return id;
    }
}
