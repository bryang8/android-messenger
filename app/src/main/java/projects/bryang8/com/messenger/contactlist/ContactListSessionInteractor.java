package projects.bryang8.com.messenger.contactlist;

/**
 * Created by bryan_g8 on 18/06/16.
 */
public interface ContactListSessionInteractor {
    void signOff();
    String getCurrentUserEmail();
    void changeConnectionStatus(boolean online);
}
