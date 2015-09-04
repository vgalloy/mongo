package com.vgalloy.mongo.dao.impl;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.vgalloy.mongo.dao.UserDao;
import com.vgalloy.mongo.factory.DBFactory;
import com.vgalloy.mongo.model.User;
import org.mongojack.JacksonDBCollection;
import org.mongojack.WriteResult;

import java.util.List;

public enum UserDaoImpl implements UserDao {
    INSTANCE;

    public static final String DATABASE = "Example";
    public static final String COLLECTION = "people";

    private final DBCollection dbCollection;
    private final JacksonDBCollection<User, String> collection;

    UserDaoImpl() {
        DB database = DBFactory.getDatabase(DATABASE);
        dbCollection = database.getCollection(COLLECTION);
        collection = JacksonDBCollection.wrap(dbCollection, User.class, String.class);
    }

    /**
     * Static method returning the instance of UserManagerImpl.
     *
     * @return The instance of UserDaoImpl.
     */
    public static UserDao getInstance() {
        return INSTANCE;
    }

    @Override
    public List<User> getAll() {
        return collection.find().toArray();
    }

    @Override
    public void create(User element) {
        WriteResult writeResult = collection.insert(element);
        element.setId(writeResult.getSavedId().toString());
    }

    @Override
    public User getById(String id) {
        return collection.findOneById(id);
    }

    @Override
    public void update(User element) {
        collection.save(element);
    }

    @Override
    public void delete(String id) {
        collection.removeById(id);
    }

    @Override
    public void removeAll(){
        dbCollection.remove(new BasicDBObject());
    }
}
