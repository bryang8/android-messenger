package projects.bryang8.com.messenger.contactlist;

import projects.bryang8.com.messenger.contactlist.events.ContactListEvent;

/**
 * Created by bryan_g8 on 18/06/16.
 */
public interface ContactListPresenter {
    void onCreate();
    void onDestroy();
    void onPause();
    void onResume();

    void signOff();
    String getCurrentUserEmail();
    void removeContact(String email);
    void onEventMainThread(ContactListEvent event);
}
