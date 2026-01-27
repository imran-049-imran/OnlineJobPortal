package com.jobportal.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/applications")
@CrossOrigin(origins = "http:localhost:5173")
public class ResumeController {

    private static final String UPLOAD_DIR = "uploads/resumes/";

    @PostMapping("/upload-resume")
    public ResponseEntity<String> uploadResume(@RequestParam("file") MultipartFile file) {

        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("File is empty");
        }

        // optional: ensure user is authenticated
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth != null ? auth.getName() : "anonymous";

        String originalName = file.getOriginalFilename();
        String safeName = originalName != null ? originalName.replaceAll("\\s+", "_") : "resume";
        String filename = System.currentTimeMillis() + "_" + safeName;

        Path uploadPath = Paths.get(UPLOAD_DIR);
        Path filePath = uploadPath.resolve(filename);

        try {
            Files.createDirectories(uploadPath);
            Files.write(filePath, file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Failed to upload file");
        }

        // Simple local URL – you can adjust this if you serve files differently
        String resumeUrl = "/files/resumes/" + filename;

        return ResponseEntity.ok(resumeUrl);
    }
}

