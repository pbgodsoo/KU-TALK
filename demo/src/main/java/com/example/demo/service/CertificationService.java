package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Certification;
import com.example.demo.repository.CertificationRepository;

@Service
public class CertificationService {

    @Autowired
    private CertificationRepository certificationRepository;

    public Certification saveCertification(Certification certification) {
        return certificationRepository.save(certification);
    }
}
