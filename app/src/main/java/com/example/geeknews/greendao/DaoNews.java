package com.example.geeknews.greendao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by 马明祥 on 2019/1/2.
 */
@Entity
public class DaoNews {
    @Id(autoincrement = true)
    private Long id;

    private int ida;

    private String image;

    private String title;

    private String url;

    private int type;

    private Long time;

    @Generated(hash = 333682938)
    public DaoNews(Long id, int ida, String image, String title, String url,
            int type, Long time) {
        this.id = id;
        this.ida = ida;
        this.image = image;
        this.title = title;
        this.url = url;
        this.type = type;
        this.time = time;
    }

    @Generated(hash = 1263796077)
    public DaoNews() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getIda() {
        return this.ida;
    }

    public void setIda(int ida) {
        this.ida = ida;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Long getTime() {
        return this.time;
    }

    public void setTime(Long time) {
        this.time = time;
    }


}
