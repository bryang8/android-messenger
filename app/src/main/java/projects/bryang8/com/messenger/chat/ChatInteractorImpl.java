package projects.bryang8.com.messenger.chat;

/**
 * Created by bryan_g8 on 19/06/16.
 */
public class ChatInteractorImpl implements ChatInteractor {
    ChatRepository chatRepository;

    public ChatInteractorImpl() {
        this.chatRepository = new ChatRepositoryImpl();
    }

    @Override
    public void subscribe() {
        chatRepository.subscribe();
    }

    @Override
    public void unSubscribe() {
        chatRepository.unSubscribe();
    }

    @Override
    public void destroyListener() {
        chatRepository.destroyListener();
    }

    @Override
    public void setRecipient(String recipient) {
        chatRepository.setReceiver(recipient);
    }

    @Override
    public void sendMessage(String msg) {
        chatRepository.sendMessage(msg);
    }
}
