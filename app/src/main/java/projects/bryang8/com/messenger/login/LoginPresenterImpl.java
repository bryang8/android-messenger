package projects.bryang8.com.messenger.login;

import org.greenrobot.eventbus.Subscribe;

import projects.bryang8.com.messenger.login.events.LoginEvent;
import projects.bryang8.com.messenger.lib.EventBus;
import projects.bryang8.com.messenger.lib.GreenRobotEventBus;
import projects.bryang8.com.messenger.login.ui.LoginView;

/**
 * Created by bryan_g8 on 18/06/16.
 */
public class LoginPresenterImpl implements  LoginPresenter{
    private EventBus eventBus;
    private LoginView loginView;
    private LoginInteractor loginInteractor;

    public LoginPresenterImpl(LoginView loginView) {
        this.loginView = loginView;
        this.loginInteractor = new LoginInteractorImpl();
        this.eventBus = GreenRobotEventBus.getInstance();
    }

    @Override
    public void onCreate() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        loginView= null;
        eventBus.unregister(this);
    }

    @Override
    public void checkForAuthenticatedUser() {
        if (loginView != null) {
            loginView.disableInputs();
            loginView.showProgress();
        }

        loginInteractor.checkSession();
    }

    @Override
    public void validateLogin(String email, String password) {
        if (loginView != null) {
            loginView.disableInputs();
            loginView.showProgress();
        }
        loginInteractor.doSignIn(email, password);
    }

    @Override
    public void registerNewUser(String email, String password) {
        if (loginView != null) {
            loginView.disableInputs();
            loginView.showProgress();
        }
        loginInteractor.doSignUp(email, password);
    }

    @Override
    @Subscribe
    public void onEventMainThread(LoginEvent event) {
        switch (event.getEventType()) {
            case LoginEvent.onSignInSuccess:
                onSigninSuccess();
                break;
            case LoginEvent.onSignUpSuccess:
                onSignupSuccess();
                break;
            case LoginEvent.onSignInError:
                onSigninError(event.getErrorMesage());
                break;
            case LoginEvent.onSignUpError:
                onSignupError(event.getErrorMesage());
            case LoginEvent.onFailedToRecoverSession:
                onFailedToRecoverSession();
                break;
        }
    }

    private void onFailedToRecoverSession() {
        if (loginView != null) {
            loginView.hideProgress();
            loginView.enableInputs();
        }

    }

    private void onSigninSuccess(){
        if (loginView != null) {
            loginView.navigateToMainScreen();
        }
    }

    private void onSignupSuccess(){
        if (loginView != null) {
            loginView.newUserSuccess();
        }
    }

    private void onSigninError(String error){
        if (loginView != null) {
            loginView.hideProgress();
            loginView.enableInputs();
            loginView.loginError(error);
        }
    }

    private void onSignupError(String error){
        if (loginView != null) {
            loginView.hideProgress();
            loginView.enableInputs();
            loginView.newUserError(error);
        }
    }

}
