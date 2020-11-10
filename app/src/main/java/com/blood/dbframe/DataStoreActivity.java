package com.blood.dbframe;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.datastore.DataStoreFactory;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DataStoreActivity extends AppCompatActivity {

    @BindView(R.id.put)
    Button mPut;
    @BindView(R.id.get)
    Button mGet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_store);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.put, R.id.get})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.put:
                break;
            case R.id.get:
                break;
        }
    }
}
