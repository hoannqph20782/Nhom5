/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repo;

import Model.DanhMuc;
import Model.SanPham;
import Utilities.DBConnext;
import Utilities.JDBCHelper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SanPhamRepository {

    DBConnext dbConnext;
     public long generateRandomId() {
        return 10000 + new Random().nextInt(90000);

    }

       public ArrayList<SanPham> getAllSanPham() {
        ArrayList<SanPham> sanPhams = new ArrayList<>();
        String sql = "select * from SANPHAM";
        try ( Connection con = dbConnext.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SanPham sp = new SanPham();
                sp.setIdSanPham(rs.getInt("Id"));
                sp.setMa(rs.getString("Ma"));
                sp.setTen(rs.getString("Ten"));
                sp.setTrangThai(rs.getInt("TrangThai"));
                sanPhams.add(sp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sanPhams;
    }
          public SanPham getSanPhamByID(String id) {

        String sql = "SELECT * FROM SANPHAM WHERE Id=?";
        ResultSet rs = JDBCHelper.excuteQuery(sql, id);
        try {
            while (rs.next()) {
                return new SanPham(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4));
            }
        } catch (SQLException ex) {

            Logger.getLogger(SanPhamRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public SanPham getSanPhamTen(String ten) {

        String sql = "SELECT * FROM SANPHAM WHERE Ten = ?";
        ResultSet rs = JDBCHelper.excuteQuery(sql, ten);
        try {
            while (rs.next()) {
                return new SanPham(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4));
            }
        } catch (SQLException ex) {

            Logger.getLogger(SanPhamRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public SanPham getSanPhamMa(String ma) {

        String sql = "SELECT * FROM SANPHAM WHERE Ma = ?";
        ResultSet rs = JDBCHelper.excuteQuery(sql, ma);
        try {
            while (rs.next()) {
                return new SanPham(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4));
            }
        } catch (SQLException ex) {

            Logger.getLogger(SanPhamRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Integer insertSanPham(SanPham sp) {
        Integer row = null;
        String sql = "insert into SANPHAM (Ma, Ten) values (?,?)";
        try {
            Connection connection = dbConnext.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            // Generate a short unique identifier
            String shortUUID = UUID.randomUUID().toString().substring(0, 8); // Adjust the length as needed

            // Combine the prefix "SP" with the short UUID and pad to a total length of 10 characters
            String uniqueMa = "SP" + String.format("%-8s", shortUUID).replace(' ', '0');
            ps.setString(1, uniqueMa);
            sp.setMa(uniqueMa);

            ps.setString(2, sp.getTen());

            int result = ps.executeUpdate();
            if (result > 0) {
                ResultSet generatedKeys = ps.getGeneratedKeys();
                if (generatedKeys.next()) {
                    row = generatedKeys.getInt(1);
                    sp.setIdSanPham(row);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return row;
    }

    public boolean updateSanPham(SanPham sanPham) {
        try {
            Connection connection = dbConnext.getConnection();
            String sql = "update SANPHAM set Ten = ?, Ma = ?  where Id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setObject(1, sanPham.getTen());
            ps.setObject(2, sanPham.getMa());

            ps.setObject(3, sanPham.getIdSanPham());
            ps.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamRepository.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }


}
