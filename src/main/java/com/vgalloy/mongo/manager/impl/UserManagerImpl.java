package com.vgalloy.mongo.manager.impl;

import com.vgalloy.mongo.dao.UserDao;
import com.vgalloy.mongo.dao.impl.UserDaoImpl;
import com.vgalloy.mongo.manager.UserManager;
import com.vgalloy.mongo.model.User;

import java.util.List;

public enum UserManagerImpl implements UserManager {
    INSTANCE;

    private UserDao dao = UserDaoImpl.getInstance();

    /**
     * Static method returning the instance of UserManagerImpl.
     *
     * @return The instance of UserManagerImpl
     */
    public static UserManagerImpl getInstance() {
        return INSTANCE;
    }

    @Override
    public Long getNextId() {
        List<User> userList = dao.getAll();
        Long max = 0L;
        for (User user : userList) {
            if (user.getId() > max) {
                max = user.getId();
            }
        }
        return max + 1;
    }
}
