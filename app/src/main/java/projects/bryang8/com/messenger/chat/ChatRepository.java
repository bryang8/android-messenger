package projects.bryang8.com.messenger.chat;

/**
 * Created by bryan_g8 on 19/06/16.
 */
public interface ChatRepository {
    void sendMessage(String msg);
    void setReceiver(String receiver);

    void destroyListener();
    void subscribe();
    void unSubscribe();

    void changeUserConnectionStatus(boolean online);
}
