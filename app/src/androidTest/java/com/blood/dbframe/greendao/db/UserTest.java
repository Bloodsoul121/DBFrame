package com.blood.dbframe.greendao.db;

import org.greenrobot.greendao.test.AbstractDaoTestLongPk;

import com.blood.dbframe.greendao.table.User;
import com.blood.dbframe.greendao.db.UserDao;

public class UserTest extends AbstractDaoTestLongPk<UserDao, User> {

    public UserTest() {
        super(UserDao.class);
    }

    @Override
    protected User createEntity(Long key) {
        User entity = new User();
        entity.setId(key);
        return entity;
    }

}
