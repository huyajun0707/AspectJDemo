package com.example.library.daodb;

import com.example.library.base.BaseApplication;
import com.example.library.daodb.launch.TraceDatabaseHelper;

import org.greenrobot.greendao.query.QueryBuilder;

import static com.example.library.constant.AopConstant.DB_NAME;


/**
 * =========================================================
 *
 * @author :   HuYajun     <13426236872@163.com>
 * @version :
 * @date :   2019/4/18 15:02
 * @description :
 * =========================================================
 */
public class DaoManager {
    private DaoSession mDaoSession;
    MyOpenHelper mSQLiteOpenHelper;
    DaoMaster mDaoMaster;

    private static DaoManager mDamoManager;

    public static DaoManager getInstance() {

        if (mDamoManager == null) {
            synchronized (DaoManager.class) {
                if (mDamoManager == null) {
                    mDamoManager = new DaoManager();
                }
            }

        }
        return mDamoManager;
    }


    //获取DaoSession，从而获取各个表的操作DAO类
    public DaoSession getDaoSession() {
        if (mDaoSession == null) {
            initDataBase();
        }
        return mDaoSession;
    }

    //初始化数据库及相关类
    private void initDataBase() {
        setDebugMode(true);//默认开启Log打印
        mSQLiteOpenHelper = new MyOpenHelper(BaseApplication.getInstance(), DB_NAME, null);//建库
        mDaoMaster = new DaoMaster(mSQLiteOpenHelper.getWritableDatabase());
        mDaoSession = mDaoMaster.newSession();
        mDaoSession.clear();//清空所有数据表的缓存
    }

    //是否开启Log
    public void setDebugMode(boolean flag) {
//        MigrationHelper.DEBUG = true;//如果查看数据库更新的Log，请设置为true
        QueryBuilder.LOG_SQL = flag;
        QueryBuilder.LOG_VALUES = flag;
    }


}
