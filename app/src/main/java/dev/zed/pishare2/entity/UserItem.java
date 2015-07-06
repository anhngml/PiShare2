package dev.zed.pishare2.entity;

import android.graphics.Bitmap;

/**
 * Created by Dr on 4/8/2015.
 */
public class UserItem implements BaseItem {
    private String id;
    private String name, profilePic, email, location;
    public void setId(String value){
        id = value;
    }
    public String getId(){return  id;}
    public void setName(String value){name = value;}
    public String getName(){
        return  name;
    }
    public void setEmail(String value){email = value;}
    public String getEmail(){
        return  email;
    }
    public void setLocation(String value){location = value;}
    public String getLocation(){
        return  location;
    }
    public void setProfilePic(String value){profilePic = value;}
    public String getProfilePic(){
        return profilePic;
    }

    public UserItem(){}
    public UserItem(String id, String name,
                    String profilePic) {
        super();
        this.id = id;
        this.name = name;
        this.profilePic = profilePic;
    }
}
