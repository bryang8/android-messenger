package projects.bryang8.com.messenger.contactlist.ui.adapters;

import projects.bryang8.com.messenger.entities.User;

/**
 * Created by bryan_g8 on 18/06/16.
 */
public interface OnItemClickListener {
    void onItemClick(User user);
    void onItemLongClick(User user);
}
