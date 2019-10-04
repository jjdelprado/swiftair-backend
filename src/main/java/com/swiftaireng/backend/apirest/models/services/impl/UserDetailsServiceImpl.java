package com.swiftaireng.backend.apirest.models.services.impl;

/* Company: SWIFTAIR
 * auth: Juan José del Prado
 * Date: 06/08/2019
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.swiftaireng.backend.apirest.models.entity.Usuario;
import com.swiftaireng.backend.apirest.models.services.UsuarioService;
import com.swiftaireng.backend.apirest.security.UsuarioPrincipal;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UsuarioService usuarioService;
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Usuario usuario = usuarioService.getByNombreUsuario(username).get();
		return UsuarioPrincipal.build(usuario);
	}

}
