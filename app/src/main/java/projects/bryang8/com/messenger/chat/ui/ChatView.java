package projects.bryang8.com.messenger.chat.ui;

import projects.bryang8.com.messenger.entities.ChatMessage;

/**
 * Created by bryan_g8 on 19/06/16.
 */
public interface ChatView {
    void sendMessage();
    void onMessageReceived(ChatMessage msg);
}
