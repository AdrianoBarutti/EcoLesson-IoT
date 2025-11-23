package br.com.fiap.universidade_fiap.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import br.com.fiap.universidade_fiap.model.Usuario;
import br.com.fiap.universidade_fiap.repository.UsuarioRepository;

import java.util.stream.Collectors;

@Service
public class UsuarioUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepo;

    @Override
    public UserDetails loadUserByUsername(String emailUsuario) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepo.findByEmailUsuario(emailUsuario)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado!"));
        return new User(usuario.getEmailUsuario(),
                        usuario.getSenha(),
                        usuario.getFuncoes().stream()
                                .map(f -> new SimpleGrantedAuthority("ROLE_" + f.getNome()))
                                .collect(Collectors.toList()));
    }
}
