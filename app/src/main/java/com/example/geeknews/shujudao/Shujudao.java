package com.example.geeknews.shujudao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by 马明祥 on 2019/1/3.
 */
@Entity
public class Shujudao {
    @Id(autoincrement = true)
    private Long id;
    private String title;
    private boolean isShow;
    @Generated(hash = 724659836)
    public Shujudao(Long id, String title, boolean isShow) {
        this.id = id;
        this.title = title;
        this.isShow = isShow;
    }
    @Generated(hash = 778987155)
    public Shujudao() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public boolean getIsShow() {
        return this.isShow;
    }
    public void setIsShow(boolean isShow) {
        this.isShow = isShow;
    }
}
