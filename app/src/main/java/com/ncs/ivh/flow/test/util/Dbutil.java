package com.ncs.ivh.flow.test.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Dbutil
{
    public static void main(String[] args) {
        Connection conn = null;
        try {
            String userName = "root";
            String password = "123456";
            String jdbcurl = "jdbc:mysql://192.168.56.101:3306/ivh?useUnicode=true&characterEncoding=UTF-8";
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(jdbcurl, userName, password);
            String sql = "select * from ivh_events";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            String result = "";
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String status = rs.getString("description");
                result += id + "\t" + name + "\t" + status + "\n";
            }
            System.out.println(result);
            pstmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                    conn = null;
                } catch (Exception e) { /* ignore close errors */
                }

            }

        }

    }


}
