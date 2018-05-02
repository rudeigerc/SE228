package bookstore.model;

import java.util.Date;

public class Message {

    public int from;
    public String fromName;
    public String avatar;
    public String text;
    public Date date;

    public Message(int from, String fromName, String avatar, String text, Date date) {
        this.from = from;
        this.fromName = fromName;
        this.avatar = avatar;
        this.text = text;
        this.date = date;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
