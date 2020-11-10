package com.blood.dbframe.dbflow;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.blood.dbframe.R;
import com.blood.dbframe.log.LLog;
import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DBFlowActivity extends AppCompatActivity {

    @BindView(R.id.insert)
    Button mInsert;
    @BindView(R.id.delete)
    Button mDelete;
    @BindView(R.id.update)
    Button mUpdate;
    @BindView(R.id.query)
    Button mQuery;

    private Random mRandom = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dbflow);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
//      FlowManager.init(this); // 这句也可以初始化，相当于下面的
        FlowManager.init(new FlowConfig.Builder(this).build());
    }

    @OnClick({R.id.insert, R.id.delete, R.id.update, R.id.query})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.insert:
                insert();
                break;
            case R.id.delete:
                delete();
                break;
            case R.id.update:
                break;
            case R.id.query:
                query();
                break;
        }
    }

    private void insert() {
        Good good = new Good();
        good.setgName("good" + mRandom.nextInt(1000));
        good.setgDes("des" + mRandom.nextInt(1000));
        good.setNum(mRandom.nextInt(1000));
        good.setContent("content" + mRandom.nextInt(1000));
        long insert = good.insert();
        LLog.i("insert: " + insert);
    }

    private void delete() {
        SQLite.delete()
                .from(Good.class)
                .where(Good_Table.num.greaterThan(600))
                .query();
    }

    private void query() {
        List<Good> goods = SQLite.select().from(Good.class).queryList();
        for (Good good : goods) {
            LLog.i("query: " + good.toString());
        }
    }

}
