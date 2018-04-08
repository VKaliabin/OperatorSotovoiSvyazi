package project.authentication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AuthenticationSuccess extends SavedRequestAwareAuthenticationSuccessHandler {
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationSuccess.class);

    /**
     * This method determine url depends on a role of user
     * @param request - servler request
     * @param response - servlet response
     * @return - return url
     */
    @Override
    protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String role = auth.getAuthorities().toString();
        logger.debug("ROLE = {}", role);
        String targetUrl = "";
        if (role.contains("ROLE_USER")) {
            targetUrl = "welcome";
        } else if (role.contains("ROLE_ADMIN")) {
            targetUrl = "admin";
        }
        return targetUrl;
    }
}