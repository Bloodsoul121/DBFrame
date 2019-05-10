package com.blood.dbframe.dbflow;

import com.raizlabs.android.dbflow.annotation.Migration;
import com.raizlabs.android.dbflow.sql.SQLiteType;
import com.raizlabs.android.dbflow.sql.migration.AlterTableMigration;

@Migration(version = AppDatabase.VERSION, database = AppDatabase.class)
public class Migration2UserData extends AlterTableMigration<Good> {

    public Migration2UserData(Class<Good> table) {
        super(table);
    }

    @Override
    public void onPreMigrate() {
        super.onPreMigrate();
        addColumn(SQLiteType.TEXT, "content");
    }
}
