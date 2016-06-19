package projects.bryang8.com.messenger.addcontact.ui;

/**
 * Created by bryan_g8 on 18/06/16.
 */
public interface AddContactView {
    void showInput();
    void hideInput();
    void showProgress();
    void hideProgress();

    void contactAdded();
    void contactNotAdded();
}
