package projects.bryang8.com.messenger.chat;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import projects.bryang8.com.messenger.chat.events.ChatEvent;
import projects.bryang8.com.messenger.domain.FirebaseHelper;
import projects.bryang8.com.messenger.entities.ChatMessage;
import projects.bryang8.com.messenger.lib.EventBus;
import projects.bryang8.com.messenger.lib.GreenRobotEventBus;

/**
 * Created by bryan_g8 on 19/06/16.
 */
public class ChatRepositoryImpl implements ChatRepository {
    private String receiver;
    private FirebaseHelper helper;
    private ChildEventListener chatEventListener;
    private EventBus eventBus;

    public ChatRepositoryImpl(){
        helper = FirebaseHelper.getInstance();
        eventBus = GreenRobotEventBus.getInstance();
    }

    @Override
    public void sendMessage(String msg) {
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setMsg(msg);
        chatMessage.setSender(helper.getAuthUserEmail());
        Firebase chatsReference = helper.getChatsReference(receiver);
        chatsReference.push().setValue(chatMessage);
    }

    @Override
    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    @Override
    public void destroyListener() {
        chatEventListener = null;
    }

    @Override
    public void subscribe() {
        if (chatEventListener == null) {
            chatEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String previousChildKey) {
                    ChatMessage chatMessage = dataSnapshot.getValue(ChatMessage.class);
                    String msgSender = chatMessage.getSender();
                    msgSender = msgSender.replace("_",".");

                    chatMessage.setSentByMe(msgSender.equals(helper.getAuthUserEmail()));

                    ChatEvent chatEvent = new ChatEvent();
                    chatEvent.setMessage(chatMessage);
                    eventBus.post(chatEvent);
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String previousChildKey) {}

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {}

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {}

                @Override
                public void onCancelled(FirebaseError firebaseError) {}
            };
            helper.getChatsReference(receiver).addChildEventListener(chatEventListener);
        }
    }

    @Override
    public void unSubscribe() {
        if (chatEventListener != null) {
            helper.getChatsReference(receiver).removeEventListener(chatEventListener);
        }
    }

    @Override
    public void changeUserConnectionStatus(boolean online) {
        helper.changeUserConnectionStatus(online);
    }
}
