/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repo;

import Model.SIZE;
import Utilities.DBConnext;
import Utilities.JDBCHelper;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SizeRepository {
    DBConnext dBConnext;
    public ArrayList<SIZE> getAllSize() {
        ArrayList<SIZE> size = new ArrayList<>();
        String sql = "select*from Size";
        try ( Connection con = dBConnext.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SIZE sz = new SIZE();
                sz.setId(rs.getInt("id"));
                sz.setMa(rs.getString("Ma"));
                sz.setKichCo(rs.getInt("KichCo"));
                sz.setTrangThai(rs.getInt("TrangThai"));
                size.add(sz);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return size;
    }
      public SIZE getSizeByID(String id) {

        String sql = "SELECT * FROM SIZE WHERE Id=?";
        ResultSet rs = JDBCHelper.excuteQuery(sql, id);
        try {
            while (rs.next()) {
                return new SIZE(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4));
            }
        } catch (SQLException ex) {

            Logger.getLogger(SizeRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public SIZE getSizeTen(String ten) {

        String sql = "SELECT * FROM SIZE WHERE KichCo=?";
        ResultSet rs = JDBCHelper.excuteQuery(sql, ten);
        try {
            while (rs.next()) {
                return new SIZE(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4));
            }
        } catch (SQLException ex) {

            Logger.getLogger(SizeRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean insertSize(SIZE size) {
        try (Connection connection = dBConnext.getConnection()) {
            String sql = "insert into SIZE (Ma , KichCo) values (?,?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, generateUniqueCode());
                preparedStatement.setInt(2, size.getKichCo());

                int rowsAffected = preparedStatement.executeUpdate();
                return rowsAffected > 0;
            }
        } catch (SQLException ex) {
            Logger.getLogger(SizeRepository.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    private String generateUniqueCode() {
        String randomCode = String.valueOf((int) (Math.random() * 10000));
        return "SZ" + String.format("%04d", Integer.parseInt(randomCode));
    }

    public boolean updateSize(SIZE size) {
        try {
            Connection connection = dBConnext.getConnection();
            String sql = "update SIZE set KichCo = ? , Ma = ? where Id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, size.getKichCo());
            ps.setString(2, size.getMa());
            ps.setInt(3, size.getId());
            ps.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(SizeRepository.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
