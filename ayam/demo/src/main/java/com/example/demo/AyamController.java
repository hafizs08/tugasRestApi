package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ayam")
public class AyamController {

    @Autowired
    private AyamRepository ayamRepository;

    @PostMapping("/input")
    public Ayam inputTelur(@RequestBody Ayam ayam) {
        return ayamRepository.save(ayam);
    }

    @GetMapping("/rekapitulasi")
    public double rekapitulasiPenghasilan() {
        List<Ayam> ayamList = ayamRepository.findAll();
        return ayamList.stream().mapToDouble(a -> a.getJumlahTelur() * a.getHargaTelur()).sum();
    }
}

