package ni.org.ics.smil.cssfv.api.security;

import java.util.ArrayList;

import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ni.org.ics.smil.cssfv.api.entity.security.Usuario;
import ni.org.ics.smil.cssfv.api.repository.SegUsuarioRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private SegUsuarioRepository repository;
	
	public UserDetailsServiceImpl(SegUsuarioRepository repository) {
        this.repository = repository;
    }
	
	@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DisabledException {
        Usuario applicationUser = repository.findByUsuario(username);
        if (applicationUser == null) {
            throw new UsernameNotFoundException(username);
        } else if (!applicationUser.isActivo()) {
        	throw new DisabledException("USER_DISABLED");
        }
        return new User(applicationUser.getUsuario(), applicationUser.getClave(),  new ArrayList<>());
    }
}
