package projects.bryang8.com.messenger.contactlist;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.FirebaseError;

import projects.bryang8.com.messenger.contactlist.events.ContactListEvent;
import projects.bryang8.com.messenger.contactlist.ui.ContactListActivity;
import projects.bryang8.com.messenger.contactlist.ui.ContactListView;
import projects.bryang8.com.messenger.domain.FirebaseHelper;
import projects.bryang8.com.messenger.entities.User;
import projects.bryang8.com.messenger.lib.EventBus;
import projects.bryang8.com.messenger.lib.GreenRobotEventBus;

/**
 * Created by bryan_g8 on 18/06/16.
 */
public class ContactListRepositoryImpl implements ContactListRepository{
    private FirebaseHelper firebaseHelper;
    private ChildEventListener contactEventListener;
    private EventBus eventBus;

    public ContactListRepositoryImpl() {
        this.firebaseHelper = FirebaseHelper.getInstance();
        this.eventBus = GreenRobotEventBus.getInstance();
    }

    @Override
    public void signOff() {
        firebaseHelper.signOff();
    }

    @Override
    public String getCurrentEmail() {
        return firebaseHelper.getAuthUserEmail();
    }

    @Override
    public void removeContact(String email) {
        String currentUserEmail = firebaseHelper.getAuthUserEmail();
        firebaseHelper.getOneContactReference(currentUserEmail, email).removeValue();
        firebaseHelper.getOneContactReference(email, currentUserEmail).removeValue();
    }

    @Override
    public void destroyContactListListener() {
        contactEventListener = null;
    }

    @Override
    public void subscribeForContactListUpdates() {
        if (contactEventListener == null) {
            contactEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String previousChildKey) {
                    handleContact(dataSnapshot,ContactListEvent.onContactAdded);
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String previousChildKey) {
                    handleContact(dataSnapshot, ContactListEvent.onContactChanged);
                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {
                    handleContact(dataSnapshot, ContactListEvent.onContactRemoved);
                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {}

                @Override
                public void onCancelled(FirebaseError firebaseError) {}
            };
        }
        firebaseHelper.getMyContactsReference().addChildEventListener(contactEventListener);

    }

    @Override
    public void unSubscribeForContactListUpdates() {
        if (contactEventListener != null) {
            firebaseHelper.getMyContactsReference().removeEventListener(contactEventListener);
        }
    }

    @Override
    public void changeUserConnectionStatus(boolean online) {

    }

    private void handleContact(DataSnapshot dataSnapshot,int type) {
        String email = dataSnapshot.getKey();
        email = email.replace("_",".");
        boolean online = ((Boolean)dataSnapshot.getValue()).booleanValue();
        User user = new User(email, online, null);
        postEvent(type, user);
    }

    private void postEvent(int type, User user) {
        ContactListEvent contactListEvent = new ContactListEvent();
        contactListEvent.setEventType(type);
        contactListEvent.setUser(user);
        eventBus.post(contactListEvent);
    }
}
