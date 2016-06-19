package projects.bryang8.com.messenger.lib;

/**
 * Created by bryan_g8 on 18/06/16.
 */
public interface EventBus {
    void register (Object suscriber);
    void unregister (Object suscriber);
    void post (Object event);
}
