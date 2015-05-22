/*
 * --| ADAPTIVE RUNTIME PLATFORM |----------------------------------------------------------------------------------------
 *
 * (C) Copyright 2013-2015 Carlos Lozano Diez t/a Adaptive.me <http://adaptive.me>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0 . Unless required by appli-
 * -cable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS,  WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the  License  for the specific language governing
 * permissions and limitations under the License.
 *
 * Original author:
 *
 *     * Carlos Lozano Diez
 *             <http://github.com/carloslozano>
 *             <http://twitter.com/adaptivecoder>
 *             <mailto:carlos@adaptive.me>
 *
 * Contributors:
 *
 *     * Ferran Vila Conesa
 *              <http://github.com/fnva>
 *              <http://twitter.com/ferran_vila>
 *              <mailto:ferran.vila.conesa@gmail.com>
 *
 *     * See source code files for contributors.
 *
 * Release:
 *
 *     * @version v2.0.2
 *
 * -------------------------------------------| aut inveniam viam aut faciam |--------------------------------------------
 */
package me.adaptive.arp.impl;

import me.adaptive.arp.api.*;
import me.adaptive.tools.nibble.common.AbstractApp;
import me.adaptive.tools.nibble.common.AbstractEmulator;
import me.adaptive.tools.nibble.common.utils.Utils;

import java.io.File;
import java.sql.*;
import java.text.MessageFormat;

/**
 * Interface for Managing the Cloud operations
 * Auto-generated implementation of IDatabase specification.
 */
public class DatabaseDelegate extends BaseDataDelegate implements IDatabase {

    /**
     * Logging Tag for this Implementation
     */
    private static final String LOG_TAG = "DatabaseDelegate";

    /**
     * Logger reference
     */
    private ILogging logger;

    /**
     * Default Constructor.
     */
    public DatabaseDelegate() {
        super();
        logger = AppRegistryBridge.getInstance().getLoggingBridge();
    }

    /**
     * Creates a database on default path for every platform.
     *
     * @param callback Asynchronous callback
     * @param database Database object to create
     * @since v2.0
     */
    public void createDatabase(Database database, IDatabaseResultCallback callback) {

        AbstractApp app = AbstractEmulator.getCurrentEmulator().getApp();
        Connection c;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:" + app.getTempDirectory() + "/" + database.getName() + ".db");
            callback.onResult(database);
            c.close();
        } catch (Exception e) {
            logger.log(ILoggingLogLevel.Error, LOG_TAG, e.getMessage());
            callback.onError(IDatabaseResultCallbackError.SqlException);
        }
    }

    /**
     * Deletes a database on default path for every platform.
     *
     * @param database Database object to delete
     * @param callback Asynchronous callback
     * @since v2.0
     */
    public void deleteDatabase(Database database, IDatabaseResultCallback callback) {

        AbstractApp app = AbstractEmulator.getCurrentEmulator().getApp();

        try {
            File file = new File(app.getTempDirectory() + "/" + database.getName() + ".db");
            if (file.delete()) {
                logger.log(ILoggingLogLevel.Debug, LOG_TAG, "deleteDatabase: " + database.getName() + " Deleted!");
                callback.onResult(database);
            } else {
                logger.log(ILoggingLogLevel.Error, LOG_TAG, "deleteDatabase: " + database.getName() + " NOT Deleted!");
                callback.onError(IDatabaseResultCallbackError.NotDeleted);
            }
        } catch (Exception e) {
            logger.log(ILoggingLogLevel.Error, LOG_TAG, "deleteDatabase: Error " + e.getLocalizedMessage());
            callback.onError(IDatabaseResultCallbackError.NotDeleted);
        }
    }

    /**
     * Checks if database exists by given database name.
     *
     * @param database Database Object to check if exists
     * @return True if exists, false otherwise
     * @since v2.0
     */
    public boolean existsDatabase(Database database) {

        AbstractApp app = AbstractEmulator.getCurrentEmulator().getApp();

        try {
            File file = new File(app.getTempDirectory() + "/" + database.getName() + ".db");
            return file.exists();
        } catch (Exception e) {
            logger.log(ILoggingLogLevel.Error, LOG_TAG, "deleteDatabase: Error " + e.getLocalizedMessage());
            return false;
        }
    }

    /**
     * Creates a databaseTable inside a database for every platform.
     *
     * @param database      Database for databaseTable creating.
     * @param databaseTable DatabaseTable object with the name of the databaseTable inside.
     * @param callback      DatabaseTable callback with the response
     * @since v2.0
     */
    public void createTable(Database database, DatabaseTable databaseTable, IDatabaseTableResultCallback callback) {

        AbstractApp app = AbstractEmulator.getCurrentEmulator().getApp();
        Connection c;
        Statement stmt;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:" + app.getTempDirectory() + "/" + database.getName() + ".db");

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < databaseTable.getDatabaseColumns().length; i++) {
                if (i != 0) {
                    sb.append(",");
                }
                sb.append(databaseTable.getDatabaseColumns()[i]);
            }
            String columns = sb.toString();
            columns = columns.replace("\"", "");

            stmt = c.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS " + databaseTable.getName() + " (" + columns + ")";
            stmt.executeUpdate(sql);

            logger.log(ILoggingLogLevel.Debug, LOG_TAG, "createTable: " + databaseTable.getName() + " IS created");
            callback.onResult(databaseTable);
            stmt.close();
            c.close();

        } catch (Exception e) {
            logger.log(ILoggingLogLevel.Error, LOG_TAG, e.getMessage());
            callback.onError(IDatabaseTableResultCallbackError.SqlException);
        }
    }

    /**
     * Checks if databaseTable exists by given database name.
     *
     * @param database      Database for databaseTable consulting.
     * @param databaseTable DatabaseTable object with the name of the databaseTable inside.
     * @return True if exists, false otherwise
     * @since v2.0
     */
    public boolean existsTable(Database database, DatabaseTable databaseTable) {

        AbstractApp app = AbstractEmulator.getCurrentEmulator().getApp();
        boolean result = false;
        Connection c;
        Statement stmt;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:" + app.getTempDirectory() + "/" + database.getName() + ".db");

            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT DISTINCT tbl_name FROM sqlite_master WHERE tbl_name = '" + databaseTable.getName() + "'");

            while (rs.next()) {
                logger.log(ILoggingLogLevel.Debug, LOG_TAG, "existsTable: " + databaseTable.getName() + " DOES exist");
                result = true;
            }

            stmt.close();
            c.close();

        } catch (Exception e) {
            logger.log(ILoggingLogLevel.Error, LOG_TAG, e.getMessage());
        }

        return result;
    }

    /**
     * Deletes a databaseTable inside a database for every platform.
     *
     * @param database      Database for databaseTable removal.
     * @param databaseTable DatabaseTable object with the name of the databaseTable inside.
     * @param callback      DatabaseTable callback with the response
     * @since v2.0
     */
    public void deleteTable(Database database, DatabaseTable databaseTable, IDatabaseTableResultCallback callback) {

        AbstractApp app = AbstractEmulator.getCurrentEmulator().getApp();
        Connection c;
        Statement stmt;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:" + app.getTempDirectory() + "/" + database.getName() + ".db");

            stmt = c.createStatement();
            String sql = "DROP TABLE " + databaseTable.getName();
            stmt.executeUpdate(sql);

            logger.log(ILoggingLogLevel.Debug, LOG_TAG, "createTable: " + databaseTable.getName() + " IS created");
            callback.onResult(databaseTable);
            stmt.close();
            c.close();

        } catch (Exception e) {
            logger.log(ILoggingLogLevel.Error, LOG_TAG, e.getMessage());
            callback.onError(IDatabaseTableResultCallbackError.SqlException);
        }
    }

    /**
     * Executes SQL statement into the given database. The replacements
     * should be passed as a parameter
     *
     * @param database     The database object reference.
     * @param statement    SQL statement.
     * @param replacements List of SQL statement replacements.
     * @param callback     DatabaseTable callback with the response.
     * @since v2.0
     */
    public void executeSqlStatement(Database database, String statement, String[] replacements, IDatabaseTableResultCallback callback) {

        AbstractApp app = AbstractEmulator.getCurrentEmulator().getApp();
        Connection c;
        Statement stmt;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:" + app.getTempDirectory() + "/" + database.getName() + ".db");

            stmt = c.createStatement();

            if ((replacements != null) && (replacements.length > 0)) {
                String sql = getFormattedSQL(statement, replacements);
                ResultSet rs = stmt.executeQuery(sql);

                callback.onResult(this.resultSetToTable(rs));
            }

            stmt.close();
            c.close();

        } catch (Exception e) {
            logger.log(ILoggingLogLevel.Error, LOG_TAG, e.getMessage());
            callback.onError(IDatabaseTableResultCallbackError.SqlException);
        }
    }

    /**
     * Executes SQL transaction (some statements chain) inside given database.
     *
     * @param database     The database object reference.
     * @param statements   The statements to be executed during transaction.
     * @param rollbackFlag Indicates if rollback should be performed when any
     *                     statement execution fails.
     * @param callback     DatabaseTable callback with the response.
     * @since v2.0
     */
    public void executeSqlTransactions(Database database, String[] statements, boolean rollbackFlag, IDatabaseTableResultCallback callback) {

        AbstractApp app = AbstractEmulator.getCurrentEmulator().getApp();
        Connection c = null;
        PreparedStatement preparedStatement = null;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:" + app.getTempDirectory() + "/" + database.getName() + ".db");

            c.setAutoCommit(false); //transaction block start

            for (String statement : statements) {

                preparedStatement = c.prepareStatement(statement);
                preparedStatement.executeUpdate();
            }

            c.commit();

        } catch (Exception e) {
            try {
                logger.log(ILoggingLogLevel.Error, LOG_TAG, e.getMessage());
                if (c != null) {
                    c.rollback();
                }
                callback.onError(IDatabaseTableResultCallbackError.SqlException);
            } catch (SQLException e1) {
                logger.log(ILoggingLogLevel.Error, LOG_TAG, e1.getMessage());
            }
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (c != null) {
                    c.close();
                }
                callback.onResult(new DatabaseTable());
            } catch (SQLException e) {
                logger.log(ILoggingLogLevel.Error, LOG_TAG, e.getMessage());
                callback.onError(IDatabaseTableResultCallbackError.SqlException);
            }
        }
    }

    /**
     * Prepare the string as Android database sqlite statement
     *
     * @param sql    statement string
     * @param params values to be replaced
     * @return sqlite-like string properly formatted
     */
    private String getFormattedSQL(String sql, String[] params) {
        String result;

        if (params != null) {
            sql = sql.replaceAll("'", "\"");
            for (int i = 0; i < params.length; i++) {
                if (params[i] != null) {
                    params[i] = params[i].replace("\"", "");
                }
            }
            result = MessageFormat.format(sql, (Object[]) params);
        } else {
            result = sql;
        }

        return result;
    }

    /**
     * Cast a native Cursor to Adaptive Database Object
     *
     * @param rs native object
     * @return Adaptive Database
     */
    private DatabaseTable resultSetToTable(ResultSet rs) throws SQLException {

        DatabaseTable table = new DatabaseTable();

        ResultSetMetaData rsmd = rs.getMetaData();

        // Columns count
        int columnCount = rsmd.getColumnCount();
        table.setColumnCount(columnCount);

        // Columns
        DatabaseColumn[] columns = new DatabaseColumn[columnCount];
        for (int i = 0; i < columnCount; i++) {
            columns[i] = new DatabaseColumn(rsmd.getColumnName(i));
        }
        table.setDatabaseColumns(columns);

        // Rows
        int rowCount = 0;
        DatabaseRow[] rows = new DatabaseRow[0];

        while (rs.next()) {
            rowCount++;
            String[] row = new String[columnCount];
            for (int i = 0; i < columnCount; i++) {
                row[i] = rs.getString(rsmd.getColumnName(1));
            }

            Utils.append(rows, new DatabaseRow(row));
        }

        // Row count
        table.setRowCount(rowCount);

        // Table name
        table.setName(rsmd.getTableName(0));

        return table;
    }

}
/**
 * ------------------------------------| Engineered with ♥ in Barcelona, Catalonia |--------------------------------------
 */
