package projects.bryang8.com.messenger.addcontact;

import org.greenrobot.eventbus.Subscribe;

import projects.bryang8.com.messenger.addcontact.events.AddContactEvent;
import projects.bryang8.com.messenger.addcontact.ui.AddContactFragment;
import projects.bryang8.com.messenger.addcontact.ui.AddContactView;
import projects.bryang8.com.messenger.lib.EventBus;
import projects.bryang8.com.messenger.lib.GreenRobotEventBus;

/**
 * Created by bryan_g8 on 18/06/16.
 */
public class AddContactPresenterImpl implements AddcontactPresenter {
    private AddContactView view;
    private EventBus eventBus;
    private AddContactInteractor interactor;

    public AddContactPresenterImpl(AddContactView view) {
        this.view = view;
        this.eventBus = GreenRobotEventBus.getInstance();
        this.interactor = new AddContactInteractorImpl();
    }

    @Override
    public void onShow() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        view =null;
        eventBus.unregister(this);
    }

    @Override
    public void addContact(String email) {
        if (view != null) {
            view.hideInput();
            view.showProgress();
        }
        interactor.execute(email);
    }

    @Override
    @Subscribe
    public void onEventMainThread(AddContactEvent event) {
        if (view != null) {
            view.hideProgress();
            view.showInput();

            if (event.isError()) {
                view.contactAdded();
            } else {
                view.contactAdded();
            }
        }
    }
}
