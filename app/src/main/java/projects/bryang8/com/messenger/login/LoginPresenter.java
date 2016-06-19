package projects.bryang8.com.messenger.login;

import projects.bryang8.com.messenger.login.events.LoginEvent;

/**
 * Created by bryan_g8 on 18/06/16.
 */
public interface LoginPresenter {
    void onCreate();
    void onDestroy();
    void checkForAuthenticatedUser();
    void validateLogin(String email, String password);
    void registerNewUser(String email, String password);
    void onEventMainThread(LoginEvent event);
}
