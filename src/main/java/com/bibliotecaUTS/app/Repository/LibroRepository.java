package com.bibliotecaUTS.app.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bibliotecaUTS.app.Tables.Libro;

public interface LibroRepository extends JpaRepository<Libro, Integer>{

}
