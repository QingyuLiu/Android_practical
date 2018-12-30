package com.example.zhuyuting.tablayout.Entity;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

public class BmobCoachTable extends BmobObject{
    private String cname;
    private BmobFile cimg;

    public void setCname(String cname){
        this.cname = cname;
    }

    public String getCname(){
        return cname;
    }

    public void setCoach_photo(BmobFile coach_photo){
        this.cimg = cimg;
    }

    public String getCimg(){
        return cimg.getFileUrl();
    }
}
