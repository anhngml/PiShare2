package dev.zed.pishare2.entity;

/**
 * Created by Dr on 7/3/2015.
 */
public class Comment {
    public String getowner() {
        return owner;
    }

    public void setowner(String owner) {
        this.owner = owner;
    }

    public String getownerEmail() {
        return ownerEmail;
    }

    public void setownerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

    public String getcontent() {
        return content;
    }

    public void setcontent(String content) {
        this.content = content;
    }
    private String ownerEmail;
    private String owner;
    private String content;
}
