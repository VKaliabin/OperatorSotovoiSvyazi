package project.service;

public interface SecurityService {

    String findLoggedInUsername();

    void autoLogin(String clientEmail, String password);
}
