package dev.zed.pishare2.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dr on 7/3/2015.
 */
public class Status {
    public String getId() {
        return Id;
    }

    public void setId(String id) {
        this.Id = id;
    }

    public String getownerEmail() {
        return ownerEmail;
    }

    public void setownerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

    public String getstatus() {
        return status;
    }

    public void setstatus(String status) {
        this.status = status;
    }

    public long gettimeStamp() {
        return timeStamp;
    }

    public void settimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }
    public ArrayList<Comment> getComments() {
        return Comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.Comments = comments;
    }
    private String Id;
    private String ownerEmail;
    private String status;
    private long timeStamp;
    private ArrayList<Comment> Comments;
}
