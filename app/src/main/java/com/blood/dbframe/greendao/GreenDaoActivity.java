package com.blood.dbframe.greendao;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.blood.dbframe.R;
import com.blood.dbframe.greendao.db.DaoMaster;
import com.blood.dbframe.greendao.db.DaoSession;
import com.blood.dbframe.greendao.db.PersonDao;
import com.blood.dbframe.greendao.db.UserDao;
import com.blood.dbframe.greendao.table.Person;
import com.blood.dbframe.greendao.table.User;
import com.blood.dbframe.greendao.upgrade.CustomOpenHelper;
import com.blood.dbframe.log.LLog;

import org.greenrobot.greendao.database.Database;

import java.util.List;
import java.util.Random;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class GreenDaoActivity extends AppCompatActivity {

    private DaoSession mDaoSession;
    private UserDao mUserDao;
    private PersonDao mPersonDao;
    private Random mRandom = new Random();
    private Database mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_green_dao);
        ButterKnife.bind(this);
        initDB();
        initTable();
    }

    @OnClick({R.id.insert, R.id.delete, R.id.update, R.id.query})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.insert:
                mUserDao.insert(new User(null, "user" + mRandom.nextInt(1000), "nick" + mRandom.nextInt(1000)));
                mPersonDao.insert(new Person(null, "user" + mRandom.nextInt(1000), mRandom.nextInt(1000)));
                break;
            case R.id.delete:
                break;
            case R.id.update:
                break;
            case R.id.query:
                query();
                break;
        }
    }

    private void initDB() {
//        DaoMaster.DevOpenHelper openHelper = new DaoMaster.DevOpenHelper(this, "greendao.db");

        // 自定义OpenHelper
        CustomOpenHelper openHelper = new CustomOpenHelper(this, "greendao.db");

//        DaoMaster daoMaster = new DaoMaster(openHelper.getWritableDb());

        // 数据库加密密码为“aserbao"的写法
        mDb = openHelper.getEncryptedWritableDb("aserbao");
        DaoMaster daoMaster = new DaoMaster(mDb);

        mDaoSession = daoMaster.newSession();
    }

    private void initTable() {
        mUserDao = mDaoSession.getUserDao();
        mPersonDao = mDaoSession.getPersonDao();
    }

    private void query() {
        List<User> list1 = mUserDao.queryBuilder()
//                .where(UserDao.Properties.Id.between(200, 600))
//                .limit(5) // 从筛选的数据中取5条数据
                .build()
                .list();
        for (int i = 0; i < list1.size(); i++) {
            LLog.i(list1.get(i).toString());
        }
        List<Person> list2 = mPersonDao.queryBuilder()
//                .where(PersonDao.Properties.Id.between(200, 600))
//                .limit(5)
                .build()
                .list();
        for (int i = 0; i < list2.size(); i++) {
            LLog.i(list2.get(i).toString());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDb.close();
    }
}
