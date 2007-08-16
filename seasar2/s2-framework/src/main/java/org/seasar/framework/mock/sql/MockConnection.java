/*
 * Copyright 2004-2007 the Seasar Foundation and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, 
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.seasar.framework.mock.sql;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Savepoint;
import java.sql.Statement;
import java.util.Map;

/**
 * {@link Connection}用のモッククラスです。
 * 
 * @author higa
 * 
 */
public class MockConnection implements Connection {

    private boolean closed = false;

    private boolean committed = false;

    private boolean rolledback = false;

    public void clearWarnings() throws SQLException {
    }

    public void close() throws SQLException {
        closed = true;
    }

    public void commit() throws SQLException {
        committed = true;
    }

    /**
     * コミットしているかどうかを返します。
     * 
     * @return コミットしているかどうか
     */
    public boolean isCommitted() {
        return committed;
    }

    /**
     * コミットしているかどうかを設定します。
     * 
     * @param committed
     *            コミットしているかどうか
     */
    public void setCommitted(boolean committed) {
        this.committed = committed;
    }

    public Statement createStatement() throws SQLException {
        return null;
    }

    public Statement createStatement(int resultSetType,
            int resultSetConcurrency, int resultSetHoldability)
            throws SQLException {
        return null;
    }

    public Statement createStatement(int resultSetType, int resultSetConcurrency)
            throws SQLException {
        return null;
    }

    public boolean getAutoCommit() throws SQLException {
        return false;
    }

    public String getCatalog() throws SQLException {
        return null;
    }

    public int getHoldability() throws SQLException {
        return 0;
    }

    public DatabaseMetaData getMetaData() throws SQLException {
        return null;
    }

    public int getTransactionIsolation() throws SQLException {
        return TRANSACTION_READ_COMMITTED;
    }

    public Map getTypeMap() throws SQLException {
        return null;
    }

    public SQLWarning getWarnings() throws SQLException {
        return null;
    }

    public boolean isClosed() throws SQLException {
        return closed;
    }

    /**
     * 閉じているかどうかを設定します。
     * 
     * @param closed
     *            閉じているかどうか
     */
    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    public boolean isReadOnly() throws SQLException {
        return false;
    }

    public String nativeSQL(String sql) throws SQLException {
        return null;
    }

    public CallableStatement prepareCall(String sql, int resultSetType,
            int resultSetConcurrency, int resultSetHoldability)
            throws SQLException {
        return null;
    }

    public CallableStatement prepareCall(String sql, int resultSetType,
            int resultSetConcurrency) throws SQLException {
        return null;
    }

    public CallableStatement prepareCall(String sql) throws SQLException {
        return null;
    }

    public PreparedStatement prepareStatement(String sql, int resultSetType,
            int resultSetConcurrency, int resultSetHoldability)
            throws SQLException {
        return null;
    }

    public PreparedStatement prepareStatement(String sql, int resultSetType,
            int resultSetConcurrency) throws SQLException {
        return null;
    }

    public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys)
            throws SQLException {
        return null;
    }

    public PreparedStatement prepareStatement(String sql, int[] columnIndexes)
            throws SQLException {
        return null;
    }

    public PreparedStatement prepareStatement(String sql, String[] columnNames)
            throws SQLException {
        return null;
    }

    public PreparedStatement prepareStatement(String sql) throws SQLException {
        return null;
    }

    public void releaseSavepoint(Savepoint savepoint) throws SQLException {
    }

    public void rollback() throws SQLException {
        rolledback = true;
    }

    /**
     * ロールバックしたかどうかを返します。
     * 
     * @return Returns ロールバックしたかどうか
     */
    public boolean isRolledback() {
        return rolledback;
    }

    /**
     * ロールバックしたかどうかを設定します。
     * 
     * @param rolledback
     *            ロールバックしたかどうか
     */
    public void setRolledback(boolean rolledback) {
        this.rolledback = rolledback;
    }

    public void rollback(Savepoint savepoint) throws SQLException {
    }

    public void setAutoCommit(boolean autoCommit) throws SQLException {
    }

    public void setCatalog(String catalog) throws SQLException {
    }

    public void setHoldability(int holdability) throws SQLException {
    }

    public void setReadOnly(boolean readOnly) throws SQLException {
    }

    public Savepoint setSavepoint() throws SQLException {
        return null;
    }

    public Savepoint setSavepoint(String name) throws SQLException {
        return null;
    }

    public void setTransactionIsolation(int level) throws SQLException {
    }

    public void setTypeMap(Map arg0) throws SQLException {
    }
}