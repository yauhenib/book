package com.igels.book.persistency;

import com.igels.book.common.Entity;

import java.sql.SQLException;
import java.util.List;

/**
 * Persistency interface
 *
 * @param <T> type
 */
public interface IPersistency<T extends Entity> {

    /**
     * Enumerate all items
     *
     * @return List<T>
     */
    List<T> enumerateItems() throws SQLException;

    /**
     * Get item by id
     *
     * @param itemId
     * @return T
     */
    T getItemById(Integer itemId) throws SQLException;

    /**
     * Add item
     *
     * @param itemInfo
     * @return Integer
     */
    Integer addItem(T itemInfo) throws SQLException;

    /**
     * Update item
     *
     * @param itemInfo
     * @return Integer
     */
    Integer updateItem(T itemInfo) throws SQLException;

    /**
     * Delete item
     *
     * @param itemId
     * @return Integer
     */
    Integer deleteItem(Integer itemId) throws SQLException;
}
