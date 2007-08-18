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
package org.seasar.extension.jdbc.util;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import org.seasar.framework.exception.SQLRuntimeException;

/**
 * {@link Connection}用のユーティリティです。
 * 
 * @author higa
 * 
 */
public final class ConnectionUtil {

    private ConnectionUtil() {
    }

    /**
     * コネクションを閉じます。
     * 
     * @param connection
     *            コネクション
     * @throws SQLRuntimeException
     *             SQL例外が発生した場合
     */
    public static void close(Connection connection) throws SQLRuntimeException {
        if (connection == null) {
            return;
        }
        try {
            connection.close();
        } catch (SQLException ex) {
            throw new SQLRuntimeException(ex);
        }
    }

    /**
     * {@link Statement}を作成します。
     * 
     * @param connection
     *            コネクション
     * @return {@link Statement}
     * @throws SQLRuntimeException
     *             SQL例外が発生した場合
     */
    public static Statement createStatement(Connection connection)
            throws SQLRuntimeException {
        try {
            return connection.createStatement();
        } catch (SQLException ex) {
            throw new SQLRuntimeException(ex);
        }
    }

    /**
     * {@link PreparedStatement}を作成します。
     * 
     * @param connection
     *            コネクション
     * @param sql
     *            SQL
     * @return {@link PreparedStatement}
     * @throws SQLRuntimeException
     *             SQL例外が発生した場合
     */
    public static PreparedStatement prepareStatement(Connection connection,
            String sql) throws SQLRuntimeException {

        try {
            return connection.prepareStatement(sql);
        } catch (SQLException ex) {
            throw new SQLRuntimeException(ex);
        }
    }

    /**
     * 準備されたステートメントを作成します。
     * 
     * @param connection
     *            コネクション
     * @param sql
     *            SQL
     * @param resultSetType
     *            結果セットタイプ
     * @param resultSetConcurrency
     *            結果セット同時並行性
     * @return 準備されたステートメント
     * @throws SQLRuntimeException
     *             SQL例外が発生した場合
     */
    public static PreparedStatement prepareStatement(Connection connection,
            String sql, int resultSetType, int resultSetConcurrency)
            throws SQLRuntimeException {

        try {
            return connection.prepareStatement(sql, resultSetType,
                    resultSetConcurrency);
        } catch (SQLException ex) {
            throw new SQLRuntimeException(ex);
        }
    }

    /**
     * {@link CallableStatement}を作成します。
     * 
     * @param connection
     *            コネクション
     * @param sql
     *            SQL
     * @return {@link CallableStatement}
     * @throws SQLRuntimeException
     *             SQL例外が発生した場合
     */
    public static CallableStatement prepareCall(Connection connection,
            String sql) throws SQLRuntimeException {

        try {
            return connection.prepareCall(sql);
        } catch (SQLException ex) {
            throw new SQLRuntimeException(ex);
        }
    }

    /**
     * データベースメタデータを返します。
     * 
     * @param connection
     *            コネクション
     * @return データベースメタデータ
     * @throws SQLRuntimeException
     *             SQL例外が発生した場合
     */
    public static DatabaseMetaData getMetaData(Connection connection)
            throws SQLRuntimeException {
        try {
            return connection.getMetaData();
        } catch (SQLException ex) {
            throw new SQLRuntimeException(ex);
        }
    }
}
