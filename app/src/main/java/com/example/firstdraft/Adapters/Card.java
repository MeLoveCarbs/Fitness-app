package com.example.firstdraft.Adapters;

public class Card {
    private String imgURL;
    private String title;
    private String profileImg;
    private String nickName;

    public Card(String imgURL, String title, String profileImg, String nickName) {
        this.imgURL = imgURL;
        this.title = title;
        this.profileImg = profileImg;
        this.nickName = nickName;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String getProfileImg() {
        return profileImg;
    }

    public void setProfileImg(String profileImg) {
        this.profileImg = profileImg;
    }
    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}