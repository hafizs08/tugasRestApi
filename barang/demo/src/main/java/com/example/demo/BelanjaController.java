package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/belanja")
public class BelanjaController {

    @Autowired
    private BarangRepository barangRepository;

    @GetMapping("/rekomendasi")
    public String rekomendasiBarang(@RequestParam double uang) {
        List<Barang> daftarBarang = barangRepository.findAll();
        double uangTersisa = uang;
        StringBuilder hasilBelanja = new StringBuilder("Barang yang dibeli:\n");

        for (Barang barang : daftarBarang) {
            if (uangTersisa >= barang.getHarga()) {
                uangTersisa -= barang.getHarga();
                hasilBelanja.append(barang.getNamaBarang()).append(" - Rp ").append(barang.getHarga()).append("\n");
            }
        }

        hasilBelanja.append("Uang tersisa: Rp ").append(uangTersisa);
        return hasilBelanja.toString();
    }
}

