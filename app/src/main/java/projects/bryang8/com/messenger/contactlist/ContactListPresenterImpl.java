package projects.bryang8.com.messenger.contactlist;

import org.greenrobot.eventbus.Subscribe;

import projects.bryang8.com.messenger.contactlist.events.ContactListEvent;
import projects.bryang8.com.messenger.contactlist.ui.ContactListView;
import projects.bryang8.com.messenger.entities.User;
import projects.bryang8.com.messenger.lib.EventBus;
import projects.bryang8.com.messenger.lib.GreenRobotEventBus;

/**
 * Created by bryan_g8 on 18/06/16.
 */
public class ContactListPresenterImpl implements ContactListPresenter{
    private EventBus eventBus;
    private ContactListView view;
    private ContactListInteractor listInteractor;
    private ContactListSessionInteractor listSessionInteractor;

    public ContactListPresenterImpl(ContactListView view) {
        this.view = view;
        this.eventBus = GreenRobotEventBus.getInstance();
        this.listInteractor = new ContactListInteractorImpl();
        this.listSessionInteractor = new ContactListSessionInteractorImpl();
    }

    @Override
    public void onCreate() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        eventBus.unregister(this);
        listInteractor.destroyListener();
        view = null;
    }

    @Override
    public void onPause() {
        listSessionInteractor.changeConnectionStatus(User.OFFLINE);
        listInteractor.unsubscribe();
    }

    @Override
    public void onResume() {
        listSessionInteractor.changeConnectionStatus(User.ONLINE);
        listInteractor.subscribe();
    }

    @Override
    public void signOff() {
        listSessionInteractor.changeConnectionStatus(User.OFFLINE);
        listInteractor.unsubscribe();
        listInteractor.destroyListener();
        listSessionInteractor.signOff();
    }

    @Override
    public String getCurrentUserEmail() {
        return listSessionInteractor.getCurrentUserEmail();
    }

    @Override
    public void removeContact(String email) {
        listInteractor.removeContacts(email);
    }

    @Override
    @Subscribe
    public void onEventMainThread(ContactListEvent event) {
        User user = event.getUser();
        switch (event.getEventType()) {
            case ContactListEvent.onContactAdded:
                onContactAdded(user);
                break;
            case ContactListEvent.onContactChanged:
                onContactChanged(user);
                break;
            case ContactListEvent.onContactRemoved:
                onContactRemoved(user);
                break;
        }
    }

    public void onContactAdded(User user) {
        if (view != null) {
            view.onContactAdded(user);
        }
    }

    public void onContactChanged(User user) {
        if (view != null) {
            view.onContactChanged(user);
        }
    }

    public void onContactRemoved(User user) {
        if (view != null) {
            view.onContactRemoved(user);
        }
    }
}
