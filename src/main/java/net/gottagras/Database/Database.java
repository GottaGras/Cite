package net.gottagras.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Database {

    public Database() throws Exception {
        String url = "jdbc:sqlite:database.db";
        Connection conn = DriverManager.getConnection(url);

        Statement stm = conn.createStatement();
        String sql =
            "CREATE TABLE IF NOT EXISTS market (material TEXT PRIMARY KEY, price INTEGER, volume INTEGER)";
        stm.executeUpdate(sql);

        stm.close();
        conn.close();
    }
}
