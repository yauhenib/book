package com.igels.book;

import com.igels.book.sesion.SessionFactory;

/**
 * Utility class to work with sessions.
 */
public class DbUtil {

    /**
     * Session factory.
     */
    private static final SessionFactory sessionFactory = new SessionFactory();

    /**
     * Get session factory.
     * @return SessionFactory
     */
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
