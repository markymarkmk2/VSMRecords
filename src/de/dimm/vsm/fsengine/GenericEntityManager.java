/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.dimm.vsm.fsengine;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Administrator
 */
public interface GenericEntityManager
{

    void check_commit_transaction() throws SQLException;

    void check_open_transaction();

    void close_entitymanager();

    void close_transaction() throws SQLException;

    void commit_transaction() throws SQLException;

    <T> List<T> createQuery( String string, Class<T> aClass ) throws SQLException;
    <T> List<T> createQuery( String string, Class<T> aClass, int maxResults ) throws SQLException;
    <T> List<T> createQuery( String string, Class<T> aClass, int maxResults, boolean distinct ) throws SQLException;
    <T> List<T> createQuery( String string, Class<T> aClass, int maxResults, int maxSeconds ) throws SQLException;
    <T> List<T> createQuery( String string, Class<T> aClass, int maxResults, boolean distinct, int maxSeconds ) throws SQLException;

    public Long getIdx( Object o );

    <T> T createSingleResultQuery( String string, Class<T> aClass ) throws SQLException;

    void em_detach( Object o );

    <T> T em_find( Class<T> t, long idx );

    <T> T em_merge( T t ) throws SQLException;

    void em_persist( Object o ) throws SQLException;
    void em_persist( Object o, boolean noCache ) throws SQLException;

    void em_refresh( Object o );

    void em_remove( Object o ) throws SQLException;

    boolean is_transaction_active();

    void rollback_transaction();

    public List<Object[]> createNativeQuery( String string, int qryCount ) throws SQLException;
    public List<Object[]> createNativeQuery( String string, int maxResults, int maxSeconds ) throws SQLException;

    public boolean nativeCall( String string );

    public int nativeUpdate( String string );

}
