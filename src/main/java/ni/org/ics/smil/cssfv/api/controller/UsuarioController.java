package ni.org.ics.smil.cssfv.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ni.org.ics.smil.cssfv.api.entity.security.Usuario;
import ni.org.ics.smil.cssfv.api.service.SegUsuarioService;

@RestController
public class UsuarioController {

	@Autowired
	private SegUsuarioService service;
	
	@PostMapping("/seguridad/usuarios/add")
	public Usuario addUsuario(@RequestBody Usuario usuario) {
		return service.saveUsuario(usuario);
	}
	
	@GetMapping("/seguridad/usuarios")
	public List<Usuario> getUsuarios() {
		return service.getUsuarios();
	}
	
    @GetMapping("/seguridad/usuarios/{id}")
    public Usuario getUsuarioById(@PathVariable int id) {
        return service.getUsuarioById(id);
    }
	
	@PutMapping("/seguridad/usuarios")
    public Usuario updateUsuario(@RequestBody Usuario usuario) {
        return service.updateUsuario(usuario);
    }
	
	@PutMapping("/seguridad/usuarios/clave")
    public Usuario updateClaveUsuario(@RequestBody Usuario usuario) {
        return service.updateClaveUsuario(usuario);
    }
	
	/*@GetMapping(value = "/refreshtoken")
	public ResponseEntity<?> refreshtoken(HttpServletRequest request) throws Exception {
		// From the HttpRequest get the claims
		Claim claims = (Claim) request.getAttribute("claims");

		Map<String, Object> expectedMap = getMapFromIoJsonwebtokenClaims(claims);
		String token = jwtUtil.doGenerateRefreshToken(expectedMap, expectedMap.get("sub").toString());
		return ResponseEntity.ok(new AuthenticationResponse(token));
	}*/
 
}
