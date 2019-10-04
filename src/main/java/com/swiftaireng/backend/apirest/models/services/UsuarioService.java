package com.swiftaireng.backend.apirest.models.services;

/* Company: SWIFTAIR
 * auth: Juan José del Prado
 * Date: 06/08/2019
 */

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.swiftaireng.backend.apirest.DTO.LoginUsuario;
import com.swiftaireng.backend.apirest.models.entity.Usuario;
import com.swiftaireng.backend.apirest.repository.UsuarioRepository;

@Service
@Transactional
public class UsuarioService {
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	public Optional<Usuario> getByNombreUsuario(String nu){
        return usuarioRepository.findByNombreUsuario(nu);
    }

    public boolean existePorNombre(String nu){
        return usuarioRepository.existsByNombreUsuario(nu);
    }

    public  boolean existePorEmail(String email){
        return usuarioRepository.existsByEmail(email);
    }

    public Usuario guardar(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    public boolean checkIfValidOldPassword(final LoginUsuario loginUsuario, final String oldPassword) {
        return passwordEncoder.matches(oldPassword, loginUsuario.getPassword());
    }
}
