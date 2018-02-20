package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import project.dao.ClientDao;
import project.model.ClientEntity;
import project.model.RoleEntity;

import java.util.HashSet;
import java.util.Set;

/*
*  Implementation of {@link org.springframework.security.core.userdetails.UserDetailsService} interface.
*
* */
public class ClientDetailServiceImpl implements UserDetailsService {

    @Autowired
    private ClientDao clientDao;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String clientEmail) throws UsernameNotFoundException {
        ClientEntity client = clientDao.findByEmailOfEmail(clientEmail);

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

        for(RoleEntity role:client.getRoles()){
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getTypeUser()));
        }
        return new org.springframework.security.core.userdetails.User(client.geteMail(), client.getPassword(), grantedAuthorities);
    }
}
