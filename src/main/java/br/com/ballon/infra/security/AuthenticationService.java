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
        var consumer = this.consumerEntityRepository.findByEmail(username);
        if (consumer != null) {
            return consumer;
        }

       var admin = this.adminEntityRepository.findByEmail(username);
        if (admin != null) {
            return admin;
        }

        throw new UsernameNotFoundException("Usuário não encontrado.");
    }

}
