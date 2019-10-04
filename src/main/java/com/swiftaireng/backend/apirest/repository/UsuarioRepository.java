package com.swiftaireng.backend.apirest.repository;

/* Company: SWIFTAIR
 * auth: Juan José del Prado
 * Date: 06/08/2019
 */

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.swiftaireng.backend.apirest.models.entity.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	Optional<Usuario> findByNombreUsuario(String nu);
    boolean existsByNombreUsuario(String nu);
    boolean existsByEmail(String email);
}
