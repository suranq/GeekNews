package com.example.geeknews.greendao;

import com.example.geeknews.app.Myapp;
import com.example.geeknews.dao.DaoMaster;
import com.example.geeknews.dao.DaoNewsDao;
import com.example.geeknews.dao.DaoSession;

import java.util.List;

/**
 * Created by 马明祥 on 2019/1/2.
 */

public class GreenDaoHelep {
    private static GreenDaoHelep mDataBeasHelep;
        private DaoNewsDao mDaoNewsDao;

        public GreenDaoHelep() {
            DaoMaster.DevOpenHelper openHelper = new DaoMaster.DevOpenHelper(Myapp.getMyapp(), "dgc.db");
            DaoMaster daoMaster = new DaoMaster(openHelper.getWritableDatabase());
            DaoSession daoSession = daoMaster.newSession();
            mDaoNewsDao = daoSession.getDaoNewsDao();
        }

        public static GreenDaoHelep getInsh() {
            if (mDataBeasHelep == null) {
                synchronized (GreenDaoHelep.class) {
                    if (mDataBeasHelep == null) {
                        mDataBeasHelep = new GreenDaoHelep();
                    }
                }
            }
            return mDataBeasHelep;
        }

        public void insert(DaoNews daoNews){
            mDaoNewsDao.insert(daoNews);
        }

        public void delect(DaoNews daoNews){
            mDaoNewsDao.delete(daoNews);
        }

        public void updata(DaoNews daoNews){
            mDaoNewsDao.update(daoNews);
        }

        public List<DaoNews> selectAll(){
            return mDaoNewsDao.queryBuilder().list();
        }

        public List<DaoNews>selectPage(int page,int count){
            return mDaoNewsDao.queryBuilder().offset(page * count).limit(count).list();
        }

        public List<DaoNews>selectS(String s){
            return mDaoNewsDao.queryBuilder().where(DaoNewsDao.Properties.Title.eq(s)).list();
        }
}
