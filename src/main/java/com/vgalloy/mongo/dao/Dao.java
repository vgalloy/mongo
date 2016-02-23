package com.vgalloy.mongo.dao;

import java.util.List;

public interface Dao<T> {
    /**
     * Get all item.
     *
     * @return All the element of the collection
     */
    List<T> getAll();

    /**
     * Create a new item but don't set a correct id.
     *
     * @param element The element to update
     */
    void create(T element);

    /**
     * Get an item with the given id.
     *
     * @param id The id of the item
     * @return The element matching with the id
     */
    T getById(String id);

    /**
     * Update an element according an id.
     *
     * @param element The element to update
     */
    void update(T element);

    /**
     * Delete an element.
     *
     * @param id The id of the element to delete
     */
    void delete(String id);

    /**
     * Remove all the entities in the table.
     */
    void removeAll();

    /**
     * Remove entities with the id specify.
     *
     * @param idList The id List of remove
     */
    void remove(List<String> idList);
}
