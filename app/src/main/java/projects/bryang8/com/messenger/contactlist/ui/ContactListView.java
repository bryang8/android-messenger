package projects.bryang8.com.messenger.contactlist.ui;

import projects.bryang8.com.messenger.entities.User;

/**
 * Created by bryan_g8 on 18/06/16.
 */
public interface ContactListView {
    void onContactAdded(User user);
    void onContactChanged(User user);
    void onContactRemoved(User user);
}
