package com.bibliotecaUTS.app.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bibliotecaUTS.app.Tables.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{

}
