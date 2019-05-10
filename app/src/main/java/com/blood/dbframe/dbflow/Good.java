package com.blood.dbframe.dbflow;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

@Table(database = AppDatabase.class)
public class Good extends BaseModel {

    @PrimaryKey(autoincrement = true)//ID自增
    public long id;

    @Column
    public String gName;
    @Column
    public String gDes;
    @Column
    public int num;

    @Column
    public String content;//增加的字段

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getgName() {
        return gName;
    }

    public void setgName(String gName) {
        this.gName = gName;
    }

    public String getgDes() {
        return gDes;
    }

    public void setgDes(String gDes) {
        this.gDes = gDes;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "Good{" +
                "id=" + id +
                ", gName='" + gName + '\'' +
                ", gDes='" + gDes + '\'' +
                ", num=" + num +
                ", content='" + content + '\'' +
                '}';
    }
}
