/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repo;

import Model.MauSac;
import Utilities.DBConnext;
import Utilities.JDBCHelper;
import java.util.ArrayList;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ADMIN
 */
public class MauSacRepository {

    DBConnext dBConnection;

    public ArrayList<MauSac> getAllMauSac() {
        ArrayList<MauSac> mauSacs = new ArrayList<>();
        String sql = "select * from MAUSAC";
        try ( Connection con = dBConnection.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                MauSac ms = new MauSac();
                ms.setId(rs.getInt("Id"));
                ms.setMa(rs.getString("Ma"));
                ms.setMauSac(rs.getString("MauSac"));
                ms.setTrangThai(rs.getInt("TrangThai"));
                mauSacs.add(ms);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mauSacs;
    }

    public MauSac getMauSacID(String id) {

        String sql = "SELECT * FROM MAUSAC WHERE Id=?";
        ResultSet rs = JDBCHelper.excuteQuery(sql, id);
        try {
            while (rs.next()) {
                return new MauSac(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4));
            }
        } catch (SQLException ex) {

            Logger.getLogger(MauSacRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public MauSac getMauSacTen(String ten) {

        String sql = "SELECT * FROM MAUSAC WHERE MauSac =?";
        ResultSet rs = JDBCHelper.excuteQuery(sql, ten);
        try {
            while (rs.next()) {
                return new MauSac(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4));
            }
        } catch (SQLException ex) {

            Logger.getLogger(MauSacRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean insertMauSac(MauSac mauSac) {
        try ( Connection connection = dBConnection.getConnection()) {
            String sql = "insert into MAUSAC (Ma , MauSac) values (?,?)";
            try ( PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, generateUniqueCode());
                preparedStatement.setString(2, mauSac.getMauSac());

                int rowsAffected = preparedStatement.executeUpdate();
                return rowsAffected > 0;
            }
        } catch (SQLException ex) {
            Logger.getLogger(MauSacRepository.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    private String generateUniqueCode() {
        String randomCode = String.valueOf((int) (Math.random() * 10000));
        return "MS" + String.format("%04d", Integer.parseInt(randomCode));
    }

    public boolean updateMauSac(MauSac mauSac) {
        try {
            Connection connection = dBConnection.getConnection();
            String sql = "update MAUSAC set MauSac = ? where Ma = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setObject(1, mauSac.getMauSac());
            ps.setObject(2, mauSac.getMa());

            ps.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(MauSacRepository.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public MauSac addMauSac(MauSac mauSac) {

        String sql = "INSERT INTO MauSac (ma, mauSac, trangThai) VALUES (?, ?, ?)";

        try ( Connection conn = dBConnection.getConnection();  PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, mauSac.getMa());
            ps.setString(2, mauSac.getMauSac());
            ps.setInt(3, mauSac.getTrangThai());

            int affectedRows = ps.executeUpdate();

            if (affectedRows > 0) {
                try ( ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        mauSac.setId(generatedKeys.getInt(1));
                    }
                }
                return mauSac;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
//     public boolean deleteMauSacById(Integer id) {
//        String sql = "DELETE FROM MauSac WHERE Id = ?";
//        
//        try (Connection conn = dBConnection.getConnection();
//             PreparedStatement ps = conn.prepareStatement(sql)) {
//            
//            ps.setInt(1, id);
//            
//            int rowsAffected = ps.executeUpdate();
//            
//            return rowsAffected > 0;
//            
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return false;
//        }
//    }

    public MauSac getMauSacById(Integer id) {
        String sql = "SELECT * FROM MauSac WHERE Id = ?";

        try ( Connection conn = dBConnection.getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            try ( ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    MauSac mauSac = new MauSac();
                    mauSac.setId(rs.getInt("Id"));
                    mauSac.setMa(rs.getString("Ma"));
                    mauSac.setMauSac(rs.getString("MauSac"));
                    mauSac.setTrangThai(rs.getInt("TrangThai"));
                    return mauSac;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean updateMauSacFull(MauSac mauSac) {
        String sql = "UPDATE MauSac SET Ma = ?, MauSac = ?, TrangThai = ? WHERE Id = ?";

        try ( Connection conn = dBConnection.getConnection();  PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, mauSac.getMa());
            pstmt.setString(2, mauSac.getMauSac());
            pstmt.setInt(3, mauSac.getTrangThai());
            pstmt.setInt(4, mauSac.getId());

            int rowsAffected = pstmt.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
