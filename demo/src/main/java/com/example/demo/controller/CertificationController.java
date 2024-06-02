package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Certification;
import com.example.demo.service.CertificationService;

@RestController
@RequestMapping("/api/certifications")
public class CertificationController {

    @Autowired
    private CertificationService certificationService;

    @PostMapping
    public ResponseEntity<Certification> createCertification(@RequestBody Certification certification) {
        Certification savedCertification = certificationService.saveCertification(certification);
        return ResponseEntity.ok(savedCertification);
    }
}
