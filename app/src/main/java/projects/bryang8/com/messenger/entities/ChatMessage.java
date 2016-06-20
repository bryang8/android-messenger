package projects.bryang8.com.messenger.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by bryan_g8 on 19/06/16.
 */

@JsonIgnoreProperties({"sentByMe"})
public class ChatMessage {
    String msg;
    String sender;
    boolean sentByMe;

    public ChatMessage(){}

    public ChatMessage(String sender, String msg){
        this.msg = msg;
        this.sender = sender;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public boolean isSentByMe() {
        return sentByMe;
    }

    public void setSentByMe(boolean sentByMe) {
        this.sentByMe = sentByMe;
    }

    @Override
    public boolean equals(Object obj) {
        boolean equal = false;

        if (obj instanceof User) {
            ChatMessage msg = (ChatMessage) obj;
            equal = this.sender.equals(msg.sender) && this.sentByMe == msg.sentByMe
                    && this.msg.equals(msg.getMsg());
        }
        return equal;
    }
}
