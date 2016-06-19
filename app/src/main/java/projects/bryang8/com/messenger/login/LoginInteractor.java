package projects.bryang8.com.messenger.login;

/**
 * Created by bryan_g8 on 18/06/16.
 */
public interface LoginInteractor {
    void checkSession();
    void doSignUp(String email, String password);
    void doSignIn(String email, String password);
}
