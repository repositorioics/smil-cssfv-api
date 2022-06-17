package ni.org.ics.smil.cssfv.api.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import ni.org.ics.smil.cssfv.api.entity.security.Usuario;
import ni.org.ics.smil.cssfv.api.exceptions.NotEntityFoundException;
import ni.org.ics.smil.cssfv.api.repository.SegUsuarioRepository;

/**
 * Created by Miguel Salinas on 06/01/2021.
 */
@Service
public class SegUsuarioService {
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private SegUsuarioRepository repository;

	@Transactional(rollbackOn = Exception.class) 
	public Usuario saveUsuario(Usuario usuario) {
		usuario.setClave(bCryptPasswordEncoder.encode(usuario.getClave()));
		return repository.save(usuario);
	}

	public List<Usuario> saveUsuarios(List<Usuario> usuarios) {
		return repository.saveAll(usuarios);
	}

	public List<Usuario> getUsuarios() {
		return repository.findAll();
	}

	public Usuario getUsuarioById(int id) {
		Usuario usuario = repository.findById(id).orElse(null);
		if (usuario == null) throw new NotEntityFoundException(Usuario.class.getSimpleName(), "Id", String.valueOf(id));
		return usuario;
	}

	public void deleteUsuario(int id) {
		Usuario oldUsuario = repository.findById(id).orElse(null);		
		if (oldUsuario == null) throw new NotEntityFoundException(Usuario.class.getSimpleName(), "Id", String.valueOf(id));
		repository.deleteById(id);
	}

	public Usuario updateUsuario(Usuario usuario) {
		Usuario oldUsuario = repository.findById(usuario.getId()).orElse(null);
		
		if (oldUsuario == null) throw new NotEntityFoundException(Usuario.class.getSimpleName(), "Id", String.valueOf(usuario.getId()));
		
		oldUsuario.setNombres(usuario.getNombres());
		oldUsuario.setApellidos(usuario.getApellidos());
		oldUsuario.setCodigoPersonal(usuario.getCodigoPersonal());
		oldUsuario.setCorreo(usuario.getCorreo());
		oldUsuario.setImagenUrl(usuario.getImagenUrl());
		oldUsuario.setActivo(usuario.isActivo());
		oldUsuario.setUsuario(usuario.getUsuario());
		return repository.save(oldUsuario);
	}
	
	public Usuario updateClaveUsuario(Usuario usuario) {
		Usuario oldUsuario = repository.findById(usuario.getId()).orElse(null);
		
		if (oldUsuario == null) throw new NotEntityFoundException(Usuario.class.getSimpleName(), "Id", String.valueOf(usuario.getId()));
		
		oldUsuario.setClave(bCryptPasswordEncoder.encode(usuario.getClave()));
		return repository.save(oldUsuario);
	}
}
