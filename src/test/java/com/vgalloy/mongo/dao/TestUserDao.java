package com.vgalloy.mongo.dao;

import com.vgalloy.mongo.dao.impl.UserDaoImpl;
import com.vgalloy.mongo.manager.UserManager;
import com.vgalloy.mongo.manager.impl.UserManagerImpl;
import com.vgalloy.mongo.model.User;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;


public class TestUserDao {

    private static UserDao dao = UserDaoImpl.getInstance();
    private UserManager userManager = UserManagerImpl.getInstance();
    private User user;

    @Before
    public void init() {
        dao.removeAll();
        user = new User();
        user.setId(userManager.getNextId());
        user.setName("test");
        user.setAge(99);
    }

    @Test
    public void testCreate() {
        int number = dao.getAll().size();
        dao.create(user);
        assertEquals("Object not add", number + 1, dao.getAll().size());
        assertNotNull("Null id", user.getId());
    }

    @Test
    public void testGetById() {
        dao.create(user);
        User result = dao.getById(user.getId());
        assertEquals(user, result);
    }

    @Test
    public void testGetAll(){
        User user2 = new User();
        user2.setId(userManager.getNextId());
        user2.setName("test_all");
        user2.setAge(65);

        dao.create(user);
        dao.create(user2);

        List<User> userList = dao.getAll();
        assertTrue(userList.size() >= 2);
        assertTrue(userList.contains(user));
        assertTrue(userList.contains(user2));
    }

    @Test
    public void testDelete() {
        dao.create(user);
        int number = dao.getAll().size();
        dao.delete(user.getId());
        assertEquals("Object not delete", number - 1, dao.getAll().size());
    }

    @Test
    public void testUpdate() {
        dao.create(user);
        user.setAge(98);
        user.setName("toto");
        dao.update(user);
        User result = dao.getById(user.getId());
        assertEquals(user, result);
    }
}
