package com.blood.dbframe.greendao.db;

import org.greenrobot.greendao.test.AbstractDaoTestLongPk;

import com.blood.dbframe.greendao.table.Person;
import com.blood.dbframe.greendao.db.PersonDao;

public class PersonTest extends AbstractDaoTestLongPk<PersonDao, Person> {

    public PersonTest() {
        super(PersonDao.class);
    }

    @Override
    protected Person createEntity(Long key) {
        Person entity = new Person();
        entity.setId(key);
        entity.setAge(0);
        return entity;
    }

}
