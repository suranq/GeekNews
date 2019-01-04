package com.example.geeknews.shujudao;

import com.example.geeknews.app.Myapp;
import com.example.geeknews.dao.DaoMaster;
import com.example.geeknews.dao.DaoSession;
import com.example.geeknews.dao.ShujudaoDao;

import java.util.List;

/**
 * Created by 马明祥 on 2019/1/3.
 */

public class ShujuKu {
    private static ShujuKu sShujuKu;
        private ShujudaoDao mShujudaoDao;

        public ShujuKu() {
            DaoMaster.DevOpenHelper openHelper = new DaoMaster.DevOpenHelper(Myapp.getMyapp(), "xcvf.db");
            DaoMaster daoMaster = new DaoMaster(openHelper.getWritableDatabase());
            DaoSession daoSession = daoMaster.newSession();
            mShujudaoDao = daoSession.getShujudaoDao();
        }

        public static ShujuKu getInsh() {
            if (sShujuKu == null) {
                synchronized (ShujuKu.class) {
                    if (sShujuKu == null) {
                        sShujuKu = new ShujuKu();
                    }
                }
            }
            return sShujuKu;
        }

        public void insert(List<Shujudao> shujudaos){
            mShujudaoDao.insertInTx(shujudaos);
        }

        public void delect(Shujudao student){
            mShujudaoDao.delete(student);
        }

        public void updata(Shujudao student){
            mShujudaoDao.update(student);
        }

        public List<Shujudao>selectAll(){
            return mShujudaoDao.queryBuilder().list();
        }

        public List<Shujudao>selectPage(int page,int count){
            return mShujudaoDao.queryBuilder().offset(page * count).limit(count).list();
        }

        public List<Shujudao>selectS(String s){
            return mShujudaoDao.queryBuilder().where(ShujudaoDao.Properties.Title.eq(s)).list();
        }
}
