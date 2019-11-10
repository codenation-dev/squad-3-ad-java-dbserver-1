package br.com.central.erros.impl.business.service.V1;


import br.com.central.erros.impl.business.entity.V1.UserV1;
import br.com.central.erros.impl.business.repository.V5.UserRepositoryV1;
import br.com.central.erros.impl.config.security.UserSS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepositoryV1 userRepositoryV1;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserV1 userV1 = userRepositoryV1.findByEmail(email);
        if (userV1 == null) {
            throw new UsernameNotFoundException(email);
        }
        return new UserSS(Math.toIntExact(userV1.getId()), userV1.getEmail(), userV1.getSenha(), userV1.getPerfis());
    }

}