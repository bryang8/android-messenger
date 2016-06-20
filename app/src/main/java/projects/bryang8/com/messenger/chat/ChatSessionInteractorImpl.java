package projects.bryang8.com.messenger.chat;

/**
 * Created by bryan_g8 on 19/06/16.
 */
public class ChatSessionInteractorImpl implements ChatSessionInteractor {

    ChatRepository chatRepository;

    public ChatSessionInteractorImpl() {
        this.chatRepository = new ChatRepositoryImpl();
    }

    @Override
    public void changeConnectionStatus(boolean online) {
        chatRepository.changeUserConnectionStatus(online);
    }
}
