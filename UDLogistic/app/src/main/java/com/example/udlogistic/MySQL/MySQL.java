package com.example.appquanlygiaohang.MySQL;

import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQL {
    String classs = "com.mysql.jdbc.Driver";
    //Địa chỉ ip:port
    // 192.168.1.11:3308
    //+  tên database
    //     UD_Logistic
    String url = "jdbc:mysql://127.0.0.1:3308/UD_Logistic?useUnicode=true&characterEncoding=UTF-8";
    //user name pass đăng nhập mysql
    String un = "root";
    String password = "";
    Statement statement;
    Statement statement2;
    ResultSet resultSet;
    ResultSet result2;
    ResultSetMetaData rsmd;
    ResultSetMetaData rsmd2;
    Connection conn = null;

    public MySQL() {
        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            Class.forName(classs);
            conn = DriverManager.getConnection(url, un, password);
            Log.d("Alert: ", "Connected");
            statement = conn.createStatement();
            statement2 = conn.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
