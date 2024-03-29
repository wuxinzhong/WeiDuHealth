package com.wd.common.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.wd.common.bean.User;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "USER".
*/
public class UserDao extends AbstractDao<User, Long> {

    public static final String TABLENAME = "USER";

    /**
     * Properties of entity User.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Age = new Property(1, int.class, "age", false, "AGE");
        public final static Property Email = new Property(2, String.class, "email", false, "EMAIL");
        public final static Property HeadPic = new Property(3, String.class, "headPic", false, "HEAD_PIC");
        public final static Property Height = new Property(4, int.class, "height", false, "HEIGHT");
        public final static Property UserId = new Property(5, int.class, "userId", false, "USER_ID");
        public final static Property NickName = new Property(6, String.class, "nickName", false, "NICK_NAME");
        public final static Property SessionId = new Property(7, String.class, "sessionId", false, "SESSION_ID");
        public final static Property Sex = new Property(8, int.class, "sex", false, "SEX");
        public final static Property UserName = new Property(9, String.class, "userName", false, "USER_NAME");
        public final static Property Weight = new Property(10, int.class, "weight", false, "WEIGHT");
        public final static Property WhetherBingWeChat = new Property(11, int.class, "whetherBingWeChat", false, "WHETHER_BING_WE_CHAT");
    }


    public UserDao(DaoConfig config) {
        super(config);
    }
    
    public UserDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"USER\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"AGE\" INTEGER NOT NULL ," + // 1: age
                "\"EMAIL\" TEXT," + // 2: email
                "\"HEAD_PIC\" TEXT," + // 3: headPic
                "\"HEIGHT\" INTEGER NOT NULL ," + // 4: height
                "\"USER_ID\" INTEGER NOT NULL ," + // 5: userId
                "\"NICK_NAME\" TEXT," + // 6: nickName
                "\"SESSION_ID\" TEXT," + // 7: sessionId
                "\"SEX\" INTEGER NOT NULL ," + // 8: sex
                "\"USER_NAME\" TEXT," + // 9: userName
                "\"WEIGHT\" INTEGER NOT NULL ," + // 10: weight
                "\"WHETHER_BING_WE_CHAT\" INTEGER NOT NULL );"); // 11: whetherBingWeChat
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"USER\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, User entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getAge());
 
        String email = entity.getEmail();
        if (email != null) {
            stmt.bindString(3, email);
        }
 
        String headPic = entity.getHeadPic();
        if (headPic != null) {
            stmt.bindString(4, headPic);
        }
        stmt.bindLong(5, entity.getHeight());
        stmt.bindLong(6, entity.getUserId());
 
        String nickName = entity.getNickName();
        if (nickName != null) {
            stmt.bindString(7, nickName);
        }
 
        String sessionId = entity.getSessionId();
        if (sessionId != null) {
            stmt.bindString(8, sessionId);
        }
        stmt.bindLong(9, entity.getSex());
 
        String userName = entity.getUserName();
        if (userName != null) {
            stmt.bindString(10, userName);
        }
        stmt.bindLong(11, entity.getWeight());
        stmt.bindLong(12, entity.getWhetherBingWeChat());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, User entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getAge());
 
        String email = entity.getEmail();
        if (email != null) {
            stmt.bindString(3, email);
        }
 
        String headPic = entity.getHeadPic();
        if (headPic != null) {
            stmt.bindString(4, headPic);
        }
        stmt.bindLong(5, entity.getHeight());
        stmt.bindLong(6, entity.getUserId());
 
        String nickName = entity.getNickName();
        if (nickName != null) {
            stmt.bindString(7, nickName);
        }
 
        String sessionId = entity.getSessionId();
        if (sessionId != null) {
            stmt.bindString(8, sessionId);
        }
        stmt.bindLong(9, entity.getSex());
 
        String userName = entity.getUserName();
        if (userName != null) {
            stmt.bindString(10, userName);
        }
        stmt.bindLong(11, entity.getWeight());
        stmt.bindLong(12, entity.getWhetherBingWeChat());
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public User readEntity(Cursor cursor, int offset) {
        User entity = new User( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getInt(offset + 1), // age
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // email
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // headPic
            cursor.getInt(offset + 4), // height
            cursor.getInt(offset + 5), // userId
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // nickName
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // sessionId
            cursor.getInt(offset + 8), // sex
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // userName
            cursor.getInt(offset + 10), // weight
            cursor.getInt(offset + 11) // whetherBingWeChat
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, User entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setAge(cursor.getInt(offset + 1));
        entity.setEmail(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setHeadPic(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setHeight(cursor.getInt(offset + 4));
        entity.setUserId(cursor.getInt(offset + 5));
        entity.setNickName(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setSessionId(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setSex(cursor.getInt(offset + 8));
        entity.setUserName(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setWeight(cursor.getInt(offset + 10));
        entity.setWhetherBingWeChat(cursor.getInt(offset + 11));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(User entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(User entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(User entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
