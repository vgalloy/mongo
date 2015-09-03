package com.vgalloy.mongo.manager;

public interface UserManager {
    /**
     * Find the greater id of a collection and add one.
     *
     * @return The greater id of the collection + 1
     */
    Long getNextId();
}
