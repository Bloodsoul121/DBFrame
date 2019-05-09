package com.blood.dbframe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.blood.dbframe.dbflow.DBFlowActivity;
import com.blood.dbframe.greendao.GreenDaoActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.greendao)
    Button mGreendao;
    @BindView(R.id.dbflow)
    Button mDbflow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.greendao, R.id.dbflow})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.greendao:
                startActivity(new Intent(this, GreenDaoActivity.class));
                break;
            case R.id.dbflow:
                startActivity(new Intent(this, DBFlowActivity.class));
                break;
        }
    }
}
