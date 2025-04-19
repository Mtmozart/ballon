package br.com.ballon.infra.security;

import br.com.ballon.infra.user.AdminEntityRepository;
import br.com.ballon.infra.user.ConsumerEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    private AdminEntityRepository adminEntityRepository;

    @Autowired
    ConsumerEntityRepository consumerEntityRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var consumerOptional = consumerEntityRepository.findByEmail(username);
        if (consumerOptional.isPresent()) {
            return consumerOptional.get();
        }

        var adminOptional = adminEntityRepository.findByEmail(username);
        if (adminOptional.isPresent()) {
            return adminOptional.get();
        }

        throw new UsernameNotFoundException("Usuário não encontrado.");
    }


}
