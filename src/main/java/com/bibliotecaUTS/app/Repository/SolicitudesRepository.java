package com.bibliotecaUTS.app.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bibliotecaUTS.app.Tables.Solicitudes;

public interface SolicitudesRepository extends JpaRepository<Solicitudes, Integer> {
}
