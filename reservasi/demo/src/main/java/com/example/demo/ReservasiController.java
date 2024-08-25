package com.example.demo;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reservasi")
public class ReservasiController {

    @Autowired
    private ReservasiRepository reservasiRepository;

    @PostMapping("/buat")
    public String buatReservasi(@RequestBody Reservasi reservasi) {
        LocalDate tanggal = reservasi.getTanggalReservasi();
        if (tanggal.getDayOfWeek() == DayOfWeek.WEDNESDAY || tanggal.getDayOfWeek() == DayOfWeek.FRIDAY) {
            return "Hari Rabu dan Jumat libur.";
        }

        List<Reservasi> reservasiHariIni = reservasiRepository.findByTanggalReservasi(tanggal);
        if (reservasiHariIni.size() >= 2) {
            return "Reservasi penuh untuk tanggal: " + tanggal;
        }

        reservasiRepository.save(reservasi);
        return "Reservasi berhasil untuk: " + tanggal;
    }

    @GetMapping("/minggu")
    public List<Reservasi> getReservasiMinggu() {
        LocalDate sekarang = LocalDate.now();
        LocalDate akhirMinggu = sekarang.plusDays(7);
        return reservasiRepository.findAll().stream()
                .filter(r -> !r.getTanggalReservasi().getDayOfWeek().equals(DayOfWeek.WEDNESDAY) &&
                             !r.getTanggalReservasi().getDayOfWeek().equals(DayOfWeek.FRIDAY) &&
                             !r.getTanggalReservasi().isBefore(sekarang) &&
                             !r.getTanggalReservasi().isAfter(akhirMinggu))
                .toList();
    }
}