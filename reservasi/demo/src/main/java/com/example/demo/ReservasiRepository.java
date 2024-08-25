package com.example.demo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservasiRepository extends JpaRepository<Reservasi, Long> {
    List<Reservasi> findByTanggalReservasi(LocalDate tanggal);
}
