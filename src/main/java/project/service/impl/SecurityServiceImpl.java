package project.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import project.service.api.SecurityService;

@Service
public class SecurityServiceImpl implements SecurityService {
    private static final Logger logger = LoggerFactory.getLogger(SecurityServiceImpl.class);
    /**
     * Do authentication of token
     */
    @Autowired
    private AuthenticationManager authenticationManager;
    /**
     * Provides get a client by email
     */
    @Autowired
    private UserDetailsService clientDetailService;

    /**
     * Method finding logged users
     *
     * @return - return name of user
     */
    @Override
    public String findLoggedInUsername() {
        Object userDetails = SecurityContextHolder.getContext().getAuthentication().getDetails();
        if (userDetails instanceof UserDetails) {
            return ((UserDetails) userDetails).getUsername();
        }
        return null;
    }

    /**
     * Set context security under successfully authentication
     *
     * @param clientEmail - email of client
     * @param password    - password of client
     */
    @Override
    public void autoLogin(String clientEmail, String password) {
        UserDetails userDetails = clientDetailService.loadUserByUsername(clientEmail);
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
        authenticationManager.authenticate(authenticationToken);
        if (authenticationToken.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            logger.debug("current user role = {}", SecurityContextHolder.getContext().getAuthentication().getAuthorities());
            logger.debug(String.format("Successfully %s auto logged in", clientEmail));
        }
    }


}

