package com.blood.dbframe.greendao;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.blood.dbframe.R;
import com.blood.dbframe.greendao.db.DaoMaster;
import com.blood.dbframe.greendao.db.DaoSession;
import com.blood.dbframe.greendao.db.PersonDao;
import com.blood.dbframe.greendao.db.UserDao;
import com.blood.dbframe.greendao.table.User;
import com.blood.dbframe.greendao.upgrade.CustomOpenHelper;

import org.greenrobot.greendao.database.Database;

import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GreenDaoActivity extends AppCompatActivity {

    @BindView(R.id.insert)
    Button mInsert;
    @BindView(R.id.delete)
    Button mDelete;
    @BindView(R.id.update)
    Button mUpdate;
    @BindView(R.id.query)
    Button mQuery;
    @BindView(R.id.result)
    TextView mResult;
    @BindView(R.id.query1)
    Button mQuery1;
    @BindView(R.id.query2)
    Button mQuery2;
    @BindView(R.id.query3)
    Button mQuery3;

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

    private void initDB() {
//        DaoMaster.DevOpenHelper openHelper = new DaoMaster.DevOpenHelper(this, "greendao.db");

        // 自定义OpenHelper
        CustomOpenHelper openHelper = new CustomOpenHelper(this);

        mDb = openHelper.getWritableDb();

        // 链接库
        DaoMaster daoMaster = new DaoMaster(mDb);

//        // 数据库加密密码为“aserbao"的写法
//        mDb = openHelper.getEncryptedWritableDb("aserbao");
//        DaoMaster daoMaster = new DaoMaster(mDb);

        // 会话
        mDaoSession = daoMaster.newSession();
    }

    private void initTable() {
        mUserDao = mDaoSession.getUserDao();
        mPersonDao = mDaoSession.getPersonDao();
    }

    @OnClick({R.id.insert, R.id.delete, R.id.update, R.id.query, R.id.query1, R.id.query2, R.id.query3, R.id.delete1})
    public void onViewClicked(View view) {
        User user;
        switch (view.getId()) {
            case R.id.insert:
                user = new User();
                user.setName("name" + randomInt());
                user.setAddress("addr" + randomInt());
                user.setAge(randomInt());
                mUserDao.insert(user);
                query();
                break;
            case R.id.delete:
                mUserDao.deleteAll();
                query();
                break;
            case R.id.delete1:
                mUserDao.queryBuilder()
                        .where(UserDao.Properties.Age.ge(500))
                        .buildDelete()
                        .executeDeleteWithoutDetachingEntities();
                query();
                break;
            case R.id.update:
                break;
            case R.id.query:
                query();
                break;
            case R.id.query1:
                user = mUserDao.queryBuilder()
                        .where(UserDao.Properties.Age.eq(195))
                        .build()
                        .unique();
                showResult(user);
                break;
            case R.id.query2:
                user = mUserDao.queryBuilder()
                        .where(UserDao.Properties.Age.eq("195"))
                        .build()
                        .unique();
                showResult(user);
                break;
            case R.id.query3:
                user = mUserDao.queryBuilder()
                        .where(UserDao.Properties.Age.like("195"))
                        .build()
                        .unique();
                showResult(user);
                break;
        }
    }

    private void query() {
        List<User> list = mUserDao.queryBuilder()
//                .where(UserDao.Properties.Id.between(200, 600))
//                .limit(5) // 从筛选的数据中取5条数据
                .build()
                .list();
        showResult(list);
    }

    private int randomInt() {
        return mRandom.nextInt(1000);
    }

    private void showResult(List<User> list) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i).toString());
            sb.append("\n");
        }
        mResult.setText(sb);
    }

    private void showResult(User user) {
        if (user == null) {
            mResult.setText("no such user");
            return;
        }
        mResult.setText(user.toString());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDb.close();
    }

}
