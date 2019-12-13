package br.com.central.erros.impl.config.security;

import br.com.central.erros.impl.business.entity.V1.UserV1;
import br.com.central.erros.impl.business.repository.V1.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository repo;

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserV1 cli = repo.findByEmail(email).orElseThrow(() ->
                new IllegalArgumentException(email)
        );
        return new UserSS(cli.getId(), cli.getEmail(), cli.getSenha(), cli.getPerfis());
    };
}