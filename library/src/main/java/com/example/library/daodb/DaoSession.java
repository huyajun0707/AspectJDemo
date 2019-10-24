package com.example.library.daodb;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.example.library.entity.TraceInfo;

import com.example.library.daodb.TraceInfoDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig traceInfoDaoConfig;

    private final TraceInfoDao traceInfoDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        traceInfoDaoConfig = daoConfigMap.get(TraceInfoDao.class).clone();
        traceInfoDaoConfig.initIdentityScope(type);

        traceInfoDao = new TraceInfoDao(traceInfoDaoConfig, this);

        registerDao(TraceInfo.class, traceInfoDao);
    }
    
    public void clear() {
        traceInfoDaoConfig.clearIdentityScope();
    }

    public TraceInfoDao getTraceInfoDao() {
        return traceInfoDao;
    }

}