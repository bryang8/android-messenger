package projects.bryang8.com.messenger.chat;

/**
 * Created by bryan_g8 on 19/06/16.
 */
public interface ChatInteractor {
    void sendMessage(String msg);
    void setRecipient(String recipient);

    void subscribe();
    void unSubscribe();
    void destroyListener();
}
