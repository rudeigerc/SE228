package bookstore.model;

import java.util.ArrayList;

public class GroupChat {

    private ArrayList<String> contents;

    private String identifier;

    public GroupChat() {}

    public GroupChat(ArrayList<String> contents, String identifier) {
        this.identifier = "bookstore";
        this.contents = contents;
    }

    public ArrayList<String> getContents() {
        return contents;
    }

    public void setContents(ArrayList<String> contents) {
        this.contents = contents;
    }
}
