package com.swiftaireng.backend.apirest.DTO;

/* Company: SWIFTAIR
 * auth: Juan José del Prado
 * Date: 06/08/2019
 */

import javax.validation.constraints.NotBlank;

public class LoginUsuario {

	@NotBlank
    private String nombreUsuario;

    @NotBlank
    private String password;

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

	public LoginUsuario(@NotBlank String nombreUsuario, @NotBlank String password) {
		super();
		this.nombreUsuario = nombreUsuario;
		this.password = password;
	}
}
