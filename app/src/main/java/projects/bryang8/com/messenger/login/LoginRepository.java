package projects.bryang8.com.messenger.login;

/**
 * Created by bryan_g8 on 18/06/16.
 */
public interface LoginRepository {
    void signUp(String email, String password);
    void signIn(String email, String password);
    void checkSession();
}
