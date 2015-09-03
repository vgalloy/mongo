package com.vgalloy.mongo.dao.impl;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.vgalloy.mongo.dao.UserDao;
import com.vgalloy.mongo.factory.DBFactory;
import com.vgalloy.mongo.mapper.UserMapper;
import com.vgalloy.mongo.model.User;

import java.util.List;

public enum UserDaoImpl implements UserDao {
    INSTANCE;

    public static final String DATABASE = "Example";
    public static final String COLLECTION = "user";

    private final DBCollection dbCollection;

    UserDaoImpl() {
        DB database = DBFactory.getDatabase(DATABASE);
        dbCollection = database.getCollection(COLLECTION);
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
        DBCursor dbCursor = dbCollection.find();
        return UserMapper.toJavaObjectList(dbCursor.toArray());
    }

    @Override
    public void create(User element) {
        DBObject person = UserMapper.toDBObject(element);
        dbCollection.insert(person);
    }

    @Override
    public User getById(Long id) {
        BasicDBObject query = new BasicDBObject();
        query.put("id", id);
        DBObject dbObject = dbCollection.findOne(query);
        return UserMapper.toJavaObject(dbObject);
    }

    @Override
    public void update(User element) {
        DBObject person = UserMapper.toDBObject(element);
        BasicDBObject searchQuery = new BasicDBObject().append("id", element.getId());
        dbCollection.update(searchQuery, person);
    }

    @Override
    public void delete(Long id) {
        BasicDBObject query = new BasicDBObject();
        query.put("id", id);
        dbCollection.remove(query);
    }

    @Override
    public void removeAll(){
        dbCollection.remove(new BasicDBObject());
    }
}
