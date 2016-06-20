package projects.bryang8.com.messenger.chat;

import org.greenrobot.eventbus.Subscribe;

import projects.bryang8.com.messenger.chat.events.ChatEvent;
import projects.bryang8.com.messenger.chat.ui.ChatView;
import projects.bryang8.com.messenger.entities.User;
import projects.bryang8.com.messenger.lib.EventBus;
import projects.bryang8.com.messenger.lib.GreenRobotEventBus;

/**
 * Created by bryan_g8 on 19/06/16.
 */
public class ChatPresenterImpl implements ChatPresenter {
    private EventBus eventBus;
    private ChatView chatView;
    private ChatInteractor chatInteractor;
    private ChatSessionInteractor chatSessionInteractor;

    public ChatPresenterImpl(ChatView view) {
        this.chatView = view;
        this.eventBus = GreenRobotEventBus.getInstance();

        this.chatInteractor = new ChatInteractorImpl();
        this.chatSessionInteractor = new ChatSessionInteractorImpl();
    }

    @Override
    public void onCreate() {
        eventBus.register(this);
    }

    @Override
    public void onResume() {
        chatInteractor.subscribe();
        chatSessionInteractor.changeConnectionStatus(User.ONLINE);
    }

    @Override
    public void onPause() {
        chatInteractor.unSubscribe();
        chatSessionInteractor.changeConnectionStatus(User.OFFLINE);
    }

    @Override
    public void onDestroy() {
        eventBus.unregister(this);
        chatInteractor.destroyListener();
        chatView = null;
    }

    @Override
    public void setChatRecipient(String recipient) {
        this.chatInteractor.setRecipient(recipient);
    }

    @Override
    public void sendMessage(String msg) {
        chatInteractor.sendMessage(msg);
    }

    @Override
    @Subscribe
    public void onEventMainThread(ChatEvent event) {
        if (chatView != null) {
            chatView.onMessageReceived(event.getMessage());
        }
    }
}
