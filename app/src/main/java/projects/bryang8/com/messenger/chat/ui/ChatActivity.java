package projects.bryang8.com.messenger.chat.ui;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import projects.bryang8.com.messenger.MessengerApplication;
import projects.bryang8.com.messenger.R;
import projects.bryang8.com.messenger.chat.ChatPresenter;
import projects.bryang8.com.messenger.chat.ChatPresenterImpl;
import projects.bryang8.com.messenger.chat.ui.adapters.ChatAdapter;
import projects.bryang8.com.messenger.domain.AvatarHelper;
import projects.bryang8.com.messenger.entities.ChatMessage;
import projects.bryang8.com.messenger.lib.GlideImageLoader;
import projects.bryang8.com.messenger.lib.ImageLoader;

public class ChatActivity extends AppCompatActivity implements ChatView{
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.txtUser)
    TextView txtUser;
    @Bind(R.id.txtStatus)
    TextView txtStatus;
    @Bind(R.id.editTxtMessage)
    EditText inputMessage;
    @Bind(R.id.messageRecyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.imgAvatar)
    CircleImageView imgAvatar;

    public final static String EMAIL_KEY = "email";
    public final static String ONLINE_KEY = "online";

    private ChatAdapter adapter;
    private ChatPresenter chatPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ButterKnife.bind(this   );

        chatPresenter = new ChatPresenterImpl(this);
        chatPresenter.onCreate();

        setupAdapter();
        setupRecyclerView();
        setupToolbar(getIntent());

    }

    @Override
    protected void onResume() {
        chatPresenter.onResume();
        super.onResume();
    }

    @Override
    protected void onPause() {
        chatPresenter.onPause();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        chatPresenter.onDestroy();
        super.onDestroy();
    }

    @Override
    @OnClick(R.id.btnSendMessage)
    public void sendMessage() {
        if (!inputMessage.getText().toString().equals("")) {
            chatPresenter.sendMessage(inputMessage.getText().toString());
            inputMessage.setText("");
        }
    }

    @Override
    public void onMessageReceived(ChatMessage msg) {
        adapter.add(msg);
        recyclerView.scrollToPosition(adapter.getItemCount() - 1);
    }

    private void setupToolbar(Intent i) {
        String recipient = i.getStringExtra(EMAIL_KEY);
        chatPresenter.setChatRecipient(recipient);

        boolean online = i.getBooleanExtra(ONLINE_KEY, false);
        String status = online ? "online" : "offline";
        int color = online ? Color.GREEN : Color.RED;

        txtUser.setText(recipient);
        txtStatus.setText(status);
        txtStatus.setTextColor(color);

        ImageLoader imageLoader = new GlideImageLoader(getApplicationContext());
        imageLoader.load(imgAvatar, AvatarHelper.getAvatarUrl(recipient));

    }

    private void setupAdapter() {
        adapter = new ChatAdapter(this, new ArrayList<ChatMessage>());
    }

    private void setupRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }


}
