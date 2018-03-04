package project.service.api;

public interface SecurityService {

    String findLoggedInUsername();

    void autoLogin(String clientEmail, String password);
}
