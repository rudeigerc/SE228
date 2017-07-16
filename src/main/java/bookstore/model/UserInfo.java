package bookstore.model;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by rudeigerc on 2017/7/15.
 */

@Document(collection = "UserInfo")
public class UserInfo {

    private int uid;
    private String avatar;
    private String name;
    private String address;

    public UserInfo() {};

    public UserInfo(int uid, String avatar, String name, String address) {
        this.uid = uid;
        this.avatar = avatar;
        this.name = name;
        this.address = address;
    }


    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
