package projects.bryang8.com.messenger.addcontact.events;

/**
 * Created by bryan_g8 on 18/06/16.
 */
public class AddContactEvent {
    boolean error = false;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }
}
