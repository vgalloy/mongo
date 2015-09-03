package com.vgalloy.mongo.mapper;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.vgalloy.mongo.model.User;

import java.util.List;
import java.util.stream.Collectors;

public interface UserMapper {

    String ID = "id";
    String NAME = "name";
    String AGE = "age";

    /**
     * Map a user into DBObject.
     *
     * @param user The user to map
     * @return The DBObject representing the given user
     */
    static DBObject toDBObject(User user) {
        return new BasicDBObject()
                .append(ID, user.getId())
                .append(NAME, user.getName())
                .append(AGE, user.getAge());
    }

    /**
     * Map a DBObject into person.
     *
     * @param dbObject The DBObject to map
     * @return The person representing the given person
     */
    static User toJavaObject(DBObject dbObject) {
        User user = new User();
        user.setId((Long) dbObject.get(ID));
        user.setName((String) dbObject.get(NAME));
        user.setAge((Integer) dbObject.get(AGE));
        return user;
    }

    /**
     * Map a List of DBObject into a list of person.
     *
     * @param dBObjectList The List of DBObject to map
     * @return A List of person representing the given list of DBObject
     */
    static List<User> toJavaObjectList(List<DBObject> dBObjectList) {
        return dBObjectList.stream().map(UserMapper::toJavaObject).collect(Collectors.toList());
    }
}
