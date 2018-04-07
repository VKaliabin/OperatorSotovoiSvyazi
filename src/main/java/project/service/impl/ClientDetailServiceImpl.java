package project.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import project.dao.api.ClientDao;
import project.model.ClientEntity;
import project.model.RoleEntity;

import java.util.HashSet;
import java.util.Set;

public class ClientDetailServiceImpl implements UserDetailsService {
    private static final Logger logger = LoggerFactory.getLogger(ClientDetailServiceImpl.class);
    @Autowired
    private ClientDao clientDao;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String clientEmail) throws UsernameNotFoundException {
        ClientEntity client = clientDao.findByEmailOfEmail(clientEmail);

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

        for (RoleEntity role : client.getRoles()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getTypeUser()));
        }
        logger.info("User " + clientEmail + "was loaded");
        return new org.springframework.security.core.userdetails.User(client.getEmailOfEmail(), client.getPassword(), grantedAuthorities);
    }
}
