package com.example.geeknews.shezhidao;

import com.example.geeknews.app.Myapp;
import com.example.geeknews.dao.DaoMaster;
import com.example.geeknews.dao.DaoSession;
import com.example.geeknews.dao.SheZhiDao;

import java.util.List;

/**
 * Created by 马明祥 on 2019/1/6.
 */

public class SheZhiku {
    private static SheZhiku sSheZhiku;
        private SheZhiDao mSheZhiDao;

        public SheZhiku() {
            DaoMaster.DevOpenHelper openHelper = new DaoMaster.DevOpenHelper(Myapp.getMyapp(), "cvfgh.db");
            DaoMaster daoMaster = new DaoMaster(openHelper.getWritableDatabase());
            DaoSession daoSession = daoMaster.newSession();
            mSheZhiDao = daoSession.getSheZhiDao();
        }

        public static SheZhiku getInsh() {
            if (sSheZhiku == null) {
                synchronized (SheZhiku.class) {
                    if (sSheZhiku == null) {
                        sSheZhiku = new SheZhiku();
                    }
                }
            }
            return sSheZhiku;
        }

        public void insert(SheZhi sheZhi){
            mSheZhiDao.insert(sheZhi);
        }

        public void delect(SheZhi sheZhi){
            mSheZhiDao.delete(sheZhi);
        }

        public void updata(SheZhi sheZhi){
            mSheZhiDao.update(sheZhi);
        }

        public List<SheZhi> selectAll(){
            return mSheZhiDao.queryBuilder().list();
        }

        public List<SheZhi>selectPage(int page,int count){
            return mSheZhiDao.queryBuilder().offset(page * count).limit(count).list();
        }

        public List<SheZhi>selectS(String s){
            return mSheZhiDao.queryBuilder().where(SheZhiDao.Properties.IsNight.eq(s)).list();
        }
}
