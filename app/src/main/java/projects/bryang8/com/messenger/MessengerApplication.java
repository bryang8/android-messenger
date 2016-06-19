package projects.bryang8.com.messenger;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by bryan_g8 on 18/06/16.
 */
public class MessengerApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        setupFirebase();
    }

    private void setupFirebase(){
        Firebase.setAndroidContext(this);
        Firebase.getDefaultConfig().setPersistenceEnabled(true);
    }
}
