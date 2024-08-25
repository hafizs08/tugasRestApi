package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tiket")
public class TiketController {

    @Autowired
    private TiketRepository tiketRepository;

    @PostMapping("/beli")
    public Tiket beliTiket(@RequestBody Tiket tiket) {
        return tiketRepository.save(tiket);
    }

    @GetMapping("/semua")
    public List<Tiket> getSemuaTiket() {
        return tiketRepository.findAll();
    }
}
