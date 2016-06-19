package projects.bryang8.com.messenger.addcontact;

import projects.bryang8.com.messenger.addcontact.events.AddContactEvent;

/**
 * Created by bryan_g8 on 18/06/16.
 */
public interface AddcontactPresenter {
    void onShow();
    void onDestroy();

    void addContact(String email);
    void onEventMainThread(AddContactEvent event);
}
