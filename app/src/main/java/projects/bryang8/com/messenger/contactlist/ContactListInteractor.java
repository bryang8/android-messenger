package projects.bryang8.com.messenger.contactlist;

/**
 * Created by bryan_g8 on 18/06/16.
 */
public interface ContactListInteractor {
    void subscribe();
    void unsubscribe();
    void destroyListener();
    void removeContacts(String email);
}
