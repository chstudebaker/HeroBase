/**
 * Interface for generic data access operations.
 * @param <T> The type of entity.
 */
package com.chstudebaker.herobase.persistance;

import java.util.List;

public interface GenericDao<T> {

    /**
     * Retrieves all entities of type T from the database.
     * @return A list of all entities.
     */
    List<T> getAll();

    /**
     * Retrieves an entity of type T by its ID from the database.
     * @param id The ID of the entity to retrieve.
     * @return The entity with the specified ID, or null if not found.
     */
    T getById(int id);

    /**
     * Inserts a new entity into the database.
     * @param entity The entity to insert.
     * @return The ID of the inserted entity.
     */
    int insert(T entity);

    /**
     * Updates an existing entity in the database.
     * @param entity The entity to update.
     * @return True if the update is successful, false otherwise.
     */
    boolean update(T entity);

    /**
     * Deletes an entity from the database.
     * @param entity The entity to delete.
     * @return True if the deletion is successful, false otherwise.
     */
    boolean delete(T entity);
}
