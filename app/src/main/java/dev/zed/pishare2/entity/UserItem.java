package dev.zed.pishare2.entity;

import android.graphics.Bitmap;

/**
 * Created by Dr on 4/8/2015.
 */
public class UserItem implements BaseItem {
    private int id;
    private String name, image, profilePic;
    private Bitmap avatar;
    public String getName(){
        return  name;
    }
    public String getProfilePic(){
        return profilePic;
    }
    public String getImage(){
        return  image;
    }
    public UserItem(){}
    public UserItem(int id, String name, String image,
                    String profilePic) {
        super();
        this.id = id;
        this.name = name;
        this.image = image;
        this.profilePic = profilePic;
    }
}
