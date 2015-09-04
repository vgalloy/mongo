package com.vgalloy.mongo.dao;

import com.vgalloy.mongo.dao.impl.UserDaoImpl;
import com.vgalloy.mongo.model.User;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;


public class TestUserDao {

    private static UserDao dao = UserDaoImpl.getInstance();
    private User person;

    @Before
    public void init() {
        dao.removeAll();
        person = new User();
        person.setName("test");
        person.setAge(99);
    }

    @Test
    public void testCreate() {
        int number = dao.getAll().size();
        dao.create(person);
        assertEquals("Object not add", number + 1, dao.getAll().size());
        assertNotNull("Null id", person.getId());
    }

    @Test
    public void testGetById() {
        dao.create(person);
        User result = dao.getById(person.getId());
        assertEquals(person, result);
    }

    @Test
    public void testGetAll() {
        User person2 = new User();
        person2.setName("test_all");
        person2.setAge(65);

        dao.create(person);
        dao.create(person2);

        List<User> personList = dao.getAll();
        assertTrue(personList.size() >= 2);
        assertTrue(personList.contains(person));
        assertTrue(personList.contains(person2));
    }

    @Test
    public void testDelete() {
        dao.create(person);
        int number = dao.getAll().size();
        dao.delete(person.getId());
        assertEquals("Object not delete", number - 1, dao.getAll().size());
    }

    @Test
    public void testUpdate() {
        dao.create(person);
        person.setAge(98);
        person.setName("toto");
        dao.update(person);
        User result = dao.getById(person.getId());
        assertEquals(person, result);
    }
}
