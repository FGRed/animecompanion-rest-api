package database;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.springframework.stereotype.Component;

import java.io.File;
import java.sql.*;



/**
 *
 * @author Niko
 */

public class JDBC {

    final static String driver = "org.sqlite.JDBC";
    static String url;
    private static String user;

    public void setUrl(String aUrl) {
        this.url = "jdbc:sqlite:" + aUrl;
        System.out.println("connection is " + aUrl);
    }

    public String getUrl() {
        return this.url;
    }

    public Connection getConnection() throws ClassNotFoundException, SQLException {// Metodi tietokanta yhteyden muodostamista varten
        Class.forName(driver);
        Connection con = DriverManager.getConnection(url);
        con.setAutoCommit(true);
        return con;
    }

    public void putData(String sql) throws ClassNotFoundException, SQLException {// Metodi datan lisäämiseen tietokantaan
        Connection con = getConnection();
        Statement state = con.createStatement();
        state.executeUpdate(sql);
        con.close();
    }

    public Statement state;

    public ResultSet getData(String sql) throws ClassNotFoundException, SQLException {// Metodi datan hakemiseen tietokannasta
        Connection con = getConnection();
        state = con.createStatement();
        ResultSet rset = state.executeQuery(sql);
        return rset;

    }

    public void formatTable(String table) throws ClassNotFoundException, SQLException {
        Connection con = getConnection();
        Statement state = con.createStatement();
        state.executeUpdate("delete from " + table);
        state.close();
    }

    public void createNewTable(String url) throws ClassNotFoundException, SQLException {
        // SQL statement for creating a new table
        String sqlForShows = "create table shows(showID integer, showName varchar, showSummary varchar, showGenres, episodes varchar, cover varchar, synonyms TEXT, type varchar, japaneseName TEXT, link TEXT);";
        String sqlForUsers = "create table users(user_Id integer, user_name TEXT, user_password TEXT, user_email, user_database TEXT, user_showrepo VARCHAR, user_companion_girl TEXT);";
        Statement stmt = getConnection().createStatement();
        stmt.execute(sqlForShows);
        stmt.execute(sqlForUsers);
        stmt.close();

    }

    public void createNewDB(String dbName, String databaseLocation) throws ClassNotFoundException, SQLException {
        new File(databaseLocation).mkdirs();
        String url0 = "jdbc:sqlite:" + databaseLocation + "\\" + dbName;

        this.url = url0;
        createNewTable(url);

    }

    /**
     * @return the user
     */
    public static String getUser() {
        return user;
    }

    public void setUser(String name) {
        this.user = name;
    }



    /**
     * @return the database
     */
}

