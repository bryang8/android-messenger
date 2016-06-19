package projects.bryang8.com.messenger.contactlist;

/**
 * Created by bryan_g8 on 18/06/16.
 */
public class ContactListInteractorImpl implements ContactListInteractor {
    ContactListRepository repository;

    public ContactListInteractorImpl() {
        this.repository = new ContactListRepositoryImpl();
    }

    @Override
    public void subscribe() {
        repository.subscribeForContactListUpdates();
    }

    @Override
    public void unsubscribe() {
        repository.unSubscribeForContactListUpdates();
    }

    @Override
    public void destroyListener() {
        repository.destroyContactListListener();
    }

    @Override
    public void removeContacts(String email) {
        repository.removeContact(email);
    }
}
