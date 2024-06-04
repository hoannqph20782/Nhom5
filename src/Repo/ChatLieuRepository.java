/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repo;

import Model.ChatLieu;
import Model.SanPham;
import Utilities.DBConnext;
import Utilities.JDBCHelper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ChatLieuRepository {
     DBConnext dbConnext;

       public ArrayList<ChatLieu> getallChatLieus() {
        ArrayList<ChatLieu> chatLieus = new ArrayList<>();
        String sql = "select * from SANPHAM";
        try ( Connection con = dbConnext.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ChatLieu cl = new ChatLieu();
                cl.setId(rs.getInt("Id"));
                cl.setMa(rs.getString("Ma"));
                cl.setTen(rs.getString("Ten"));
                cl.setTrangThai(rs.getInt("TrangThai"));
                chatLieus.add(cl);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return chatLieus;
    }
          public ChatLieu getChatLieuByID(String id) {

        String sql = "SELECT * FROM ChatLieu WHERE Id=?";
        ResultSet rs = JDBCHelper.excuteQuery(sql, id);
        try {
            while (rs.next()) {
                return new ChatLieu(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4));
            }
        } catch (SQLException ex) {

            Logger.getLogger(ChatLieuRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ChatLieu getChatLieuTen(String ten) {

        String sql = "SELECT * FROM ChatLieu WHERE Ten =?";
        ResultSet rs = JDBCHelper.excuteQuery(sql, ten);
        try {
            while (rs.next()) {
                return new ChatLieu(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4));
            }
        } catch (SQLException ex) {

            Logger.getLogger(ChatLieuRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean insertChatLieu(ChatLieu chatLieu) {
        try (Connection connection = dbConnext.getConnection()) {
            String sql = "INSERT INTO CHATLIEU (Ma, Ten) VALUES (?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, generateUniqueCode());
                preparedStatement.setString(2, chatLieu.getTen());

                int rowsAffected = preparedStatement.executeUpdate();
                return rowsAffected > 0;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ChatLieuRepository.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    public boolean insertChatLieuCrud(ChatLieu chatLieu) {
        try (Connection connection = dbConnext.getConnection()) {
            String sql = "INSERT INTO CHATLIEU (Ma, Ten,TrangThai) VALUES (?,?,?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, generateUniqueCode());
                preparedStatement.setString(2, chatLieu.getTen());
                preparedStatement.setInt(3, chatLieu.getTrangThai());
                int rowsAffected = preparedStatement.executeUpdate();
                return rowsAffected > 0;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ChatLieuRepository.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    private String generateUniqueCode() {
        String randomCode = String.valueOf((int) (Math.random() * 10000));
        return "CL" + String.format("%04d", Integer.parseInt(randomCode));
    }


    public boolean updateChatLieu(ChatLieu chatLieu) {
        try {
            Connection connection = dbConnext.getConnection();
            String sql = "update CHATLIEU set Ten = ? , Ma = ? where Id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setObject(1, chatLieu.getTen());
            ps.setObject(2, chatLieu.getMa());
            ps.setObject(3, chatLieu.getId());
            ps.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ChatLieuRepository.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }
    
}
