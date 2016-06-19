package projects.bryang8.com.messenger.contactlist;

/**
 * Created by bryan_g8 on 18/06/16.
 */
public interface ContactListRepository {
    void signOff();
    String getCurrentEmail();
    void removeContact(String email);
    void destroyContactListListener();
    void subscribeForContactListUpdates();
    void unSubscribeForContactListUpdates();
    void changeUserConnectionStatus(boolean online);
}
