package com.vgalloy.mongo.dao;

import com.vgalloy.mongo.dao.impl.UserDaoImpl;
import com.vgalloy.mongo.model.User;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;


public class TestUserDao {

    private UserDao dao = UserDaoImpl.getInstance();
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

        assertEquals("Object not deleted", number - 1, dao.getAll().size());
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

    @Test
    public void testRemoveWithIdList() {
        User person1 = new User("Name1", 1);
        dao.create(person1);
        User person2 = new User("Name2", 2);
        dao.create(person2);
        User person3 = new User("Name3", 3);
        dao.create(person3);
        User person4 = new User("Name4", 4);
        dao.create(person4);
        User person5 = new User("Name5", 5);
        dao.create(person5);
        List<String> idListToRemove = new ArrayList<>();
        idListToRemove.add(person1.getId());
        idListToRemove.add(person2.getId());
        dao.remove(idListToRemove);

        assertEquals(3, dao.getAll().size());
    }
}
