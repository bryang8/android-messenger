package projects.bryang8.com.messenger.contactlist.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import projects.bryang8.com.messenger.R;
import projects.bryang8.com.messenger.addcontact.ui.AddContactFragment;
import projects.bryang8.com.messenger.chat.ui.ChatActivity;
import projects.bryang8.com.messenger.contactlist.ContactListPresenter;
import projects.bryang8.com.messenger.contactlist.ContactListPresenterImpl;
import projects.bryang8.com.messenger.contactlist.ui.adapters.ContactListAdapter;
import projects.bryang8.com.messenger.contactlist.ui.adapters.OnItemClickListener;
import projects.bryang8.com.messenger.entities.User;
import projects.bryang8.com.messenger.lib.GlideImageLoader;
import projects.bryang8.com.messenger.lib.ImageLoader;
import projects.bryang8.com.messenger.login.ui.LoginActivity;

public class ContactListActivity extends AppCompatActivity implements ContactListView , OnItemClickListener {
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.recyclerViewContacts)
    RecyclerView recyclerView;

    private ContactListPresenterImpl presenter;
    private ContactListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);
        ButterKnife.bind(this);



        presenter = new ContactListPresenterImpl(this);
        presenter.onCreate();

        setupAdapter();
        setupRecyclerView();
        setupToolbar();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_contactlist, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_logout) {
            presenter.signOff();
            Intent intent = new Intent(this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                            | Intent.FLAG_ACTIVITY_NEW_TASK
                            | Intent.FLAG_ACTIVITY_CLEAR_TASK);

            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupAdapter() {
        ImageLoader loader = new GlideImageLoader(this.getApplicationContext());
        adapter = new ContactListAdapter(new ArrayList<User>(), loader, this);
    }

    private void setupRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    private void setupToolbar() {
        toolbar.setTitle(presenter.getCurrentUserEmail());
        setSupportActionBar(toolbar);
    }

    @OnClick(R.id.fab)
    public void addContact(){
        new AddContactFragment().show(getSupportFragmentManager(), getString(R.string.addcontact_message_title));
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    protected void onPause() {
        presenter.onPause();
        super.onPause();
    }

    @Override
    public void onContactAdded(User user) {
        adapter.add(user);
    }

    @Override
    public void onContactChanged(User user) {
        adapter.update(user);
    }

    @Override
    public void onContactRemoved(User user) {
        adapter.remove(user);
    }

    @Override
    public void onItemClick(User user) {
        Intent intent = new Intent(this, ChatActivity.class);
        intent.putExtra(ChatActivity.EMAIL_KEY, user.getEmail());
        intent.putExtra(ChatActivity.ONLINE_KEY, user.isOnline());
        startActivity(intent);
    }

    @Override
    public void onItemLongClick(User user) {
        presenter.removeContact(user.getEmail());
    }
}
