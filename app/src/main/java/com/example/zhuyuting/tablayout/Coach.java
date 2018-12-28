package com.example.zhuyuting.tablayout;

public class Coach {
    private String name;
    private String imageUrl;
    public Coach(String name,String imageUrl){
        this.name = name;
        this.imageUrl = imageUrl;
    }
    public String getName(){
        return name;
    }
    public String getImageUrl(){
        return imageUrl;
    }
}