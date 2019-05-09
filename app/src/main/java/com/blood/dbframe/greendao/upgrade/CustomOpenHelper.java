package com.blood.dbframe.greendao.upgrade;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.blood.dbframe.greendao.db.DaoMaster;
import com.blood.dbframe.greendao.db.UserDao;

import org.greenrobot.greendao.database.Database;

/**
 *  背景：原升级，查看源码可知，是把老版本全删掉，然后重新建表
 *
 *  处理方案：
 *  创建临时表TMP_,复制原来的数据库到临时表中；
 *  删除之前的原表；
 *  创建新表；
 *  将临时表中的数据复制到新表中，最后将TMP_表删除掉；
 */
public class CustomOpenHelper extends DaoMaster.OpenHelper {

    private static final String TAG = "CustomOpenHelper";

    public CustomOpenHelper(Context context, String name) {
        super(context, name);
    }

    public CustomOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        super.onUpgrade(db, oldVersion, newVersion);
        MigrationHelper.migrate(db, new MigrationHelper.ReCreateAllTableListener() {
            @Override
            public void onCreateAllTables(Database db, boolean ifNotExists) {
                DaoMaster.createAllTables(db, ifNotExists);
            }

            @Override
            public void onDropAllTables(Database db, boolean ifExists) {
                DaoMaster.dropAllTables(db, ifExists);
            }
        }, UserDao.class);
        Log.i(TAG, "onUpgrade: " + oldVersion + " newVersion = " + newVersion);
    }
}
