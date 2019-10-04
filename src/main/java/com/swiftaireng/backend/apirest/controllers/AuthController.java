package com.swiftaireng.backend.apirest.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swiftaireng.backend.apirest.DTO.JwtDTO;
import com.swiftaireng.backend.apirest.DTO.LoginUsuario;
import com.swiftaireng.backend.apirest.DTO.Mensaje;
import com.swiftaireng.backend.apirest.models.services.UsuarioService;
import com.swiftaireng.backend.apirest.security.JWT.JwtProvider;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins= {"http://localhost:4200"})
public class AuthController {
	
//	@Autowired
//    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UsuarioService usuarioService;

//    @Autowired
//    RolService rolService;

    @Autowired
    JwtProvider jwtProvider;
    
//    @PostMapping("/nuevo")
//    public ResponseEntity<?> nuevo(@Valid @RequestBody NuevoUsuario nuevoUsuario, BindingResult bindingResult){
//        if(bindingResult.hasErrors())
//            return new ResponseEntity(new Mensaje("campos vacíos o email inválido"), HttpStatus.BAD_REQUEST);
//        if(usuarioService.existePorNombre(nuevoUsuario.getNombreUsuario()))
//            return new ResponseEntity(new Mensaje("ese nombre ya existe"), HttpStatus.BAD_REQUEST);
//        if(usuarioService.existePorEmail(nuevoUsuario.getEmail()))
//            return new ResponseEntity(new Mensaje("ese email ya existe"), HttpStatus.BAD_REQUEST);
//        Usuario usuario =
//                new Usuario(nuevoUsuario.getNombre(), nuevoUsuario.getPrimerApellido(), nuevoUsuario.getSegundoApellido(), nuevoUsuario.getNombreUsuario(), nuevoUsuario.getEmail(),
//                        passwordEncoder.encode(nuevoUsuario.getPassword()));
//        Set<String> rolesStr = nuevoUsuario.getRoles();
//        Set<Rol> roles = new HashSet<>();
//        for (String rol : rolesStr) {
//            switch (rol) {
//                case "admin":
//                    Rol rolAdmin = rolService.getByRolNombre(RolNombre.ROLE_ADMIN).get();
//                    roles.add(rolAdmin);
//                    break;
//                default:
//                    Rol rolUser = rolService.getByRolNombre(RolNombre.ROLE_USER).get();
//                    roles.add(rolUser);
//            }
//        }
//        usuario.setRoles(roles);
//        usuarioService.guardar(usuario);
//        return new ResponseEntity(new Mensaje("usuario guardado"), HttpStatus.CREATED);
//    }
    
    @PostMapping("/login")
    public ResponseEntity<JwtDTO> login(@Valid @RequestBody LoginUsuario loginUsuario, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity(new Mensaje("campos vacíos o email inválido"), HttpStatus.BAD_REQUEST);
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginUsuario.getNombreUsuario(), loginUsuario.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        JwtDTO jwtDTO = new JwtDTO(jwt, userDetails.getUsername(), userDetails.getAuthorities());
        return new ResponseEntity<JwtDTO>(jwtDTO, HttpStatus.OK);
    }

    @PostMapping("/check")
    public Boolean checkUserPassword(@Valid @RequestBody LoginUsuario oldLogUser, BindingResult bindingResult){
    	String username = null;
  	  	String userpassword = null;
    	Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	if (principal instanceof UserDetails) {
    	  username = ((UserDetails)principal).getUsername();
    	  userpassword = ((UserDetails)principal).getPassword();
    	} else {
    	  username = principal.toString();
    	  userpassword = principal.toString();
    	}
    	LoginUsuario logUser = new LoginUsuario(username,userpassword);
    	return usuarioService.checkIfValidOldPassword(logUser, oldLogUser.getPassword());
    }
}
