package com.example.geeknews.shezhidao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by 马明祥 on 2019/1/6.
 */
@Entity
public class SheZhi {
    @Id(autoincrement = true)
    private Long id;
    private boolean isHuan;
    private boolean isWutu;
    private boolean isNight;
    @Generated(hash = 28085657)
    public SheZhi(Long id, boolean isHuan, boolean isWutu, boolean isNight) {
        this.id = id;
        this.isHuan = isHuan;
        this.isWutu = isWutu;
        this.isNight = isNight;
    }
    @Generated(hash = 1098506760)
    public SheZhi() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public boolean getIsHuan() {
        return this.isHuan;
    }
    public void setIsHuan(boolean isHuan) {
        this.isHuan = isHuan;
    }
    public boolean getIsWutu() {
        return this.isWutu;
    }
    public void setIsWutu(boolean isWutu) {
        this.isWutu = isWutu;
    }
    public boolean getIsNight() {
        return this.isNight;
    }
    public void setIsNight(boolean isNight) {
        this.isNight = isNight;
    }
    
}
