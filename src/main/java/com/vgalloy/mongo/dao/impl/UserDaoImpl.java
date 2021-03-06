package com.vgalloy.mongo.dao.impl;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBObject;
import com.vgalloy.mongo.dao.UserDao;
import com.vgalloy.mongo.factory.DBFactory;
import com.vgalloy.mongo.model.User;
import org.bson.types.ObjectId;
import org.jongo.Jongo;
import org.jongo.MongoCollection;
import org.jongo.MongoCursor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public enum UserDaoImpl implements UserDao {
    INSTANCE;

    public static final String DATABASE = "Example";
    public static final String COLLECTION = "people";

    private final MongoCollection collection;

    UserDaoImpl() {
        DB database = DBFactory.getDatabase(DATABASE);
        Jongo jongo = new Jongo(database);
        collection = jongo.getCollection(COLLECTION);
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
        MongoCursor<User> mongoCursor = collection.find().as(User.class);
        Iterator<User> personIterator = mongoCursor.iterator();
        List<User> copy = new ArrayList<>();
        while (personIterator.hasNext()) {
            copy.add(personIterator.next());
        }
        return copy;
    }

    @Override
    public void create(User element) {
        collection.save(element);
    }

    @Override
    public User getById(String id) {
        return collection.findOne(new ObjectId(id)).as(User.class);
    }

    @Override
    public void update(User element) {
        collection.update(new ObjectId(element.getId())).with(element);
    }

    @Override
    public void delete(String id) {
        collection.remove(new ObjectId(id));
    }

    @Override
    public void removeAll() {
        collection.remove();
    }

    @Override
    public void remove(List<String> idList) {
        List<ObjectId> listObjectId = idList.stream().map(ObjectId::new).collect(Collectors.toList());
        BasicDBList docIds = new BasicDBList();
        docIds.addAll(listObjectId);
        DBObject inClause = new BasicDBObject("$in", docIds);
        DBObject query = new BasicDBObject("_id", inClause);
        collection.remove(query.toString());
    }
}
