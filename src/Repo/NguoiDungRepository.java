/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repo;


import Model.ChucVu;
import Model.NguoiDung;
import Utilities.DBConnext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public class NguoiDungRepository {
 
    ChucVuRepository cvr = new ChucVuRepository();
    public ArrayList<NguoiDung> checkLogin(NguoiDung user) {
        String sql = "SELECT * FROM NguoiDung WHERE Email = ? AND MatKhau = ? AND TrangThai = 1";
        ArrayList<NguoiDung> userList = new ArrayList<>();

        try (Connection conn = DBConnext.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, user.getEmailNguoiDung());
            ps.setString(2, user.getMatKhauNguoiDung());

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                 ChucVu    cv = cvr.getDanhMucByID(rs.getString(2));
                    userList.add(new NguoiDung(rs.getInt(1), cv, rs.getString(3), rs.getString(4), rs.getDate(5), rs.getString(6), rs.getString(7), rs.getInt(8), rs.getString(9),rs.getString(10),rs.getInt(11)));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return userList;
    }

    public String getIDLoaiNguoiDung() {
        String sql = "SELECT Id FROM CHUCVU WHERE Ten = N'Quản lý'";
        String idLoaiNguoiDung = "";
        try (Connection conn = DBConnext.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                idLoaiNguoiDung = rs.getString("Id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return idLoaiNguoiDung;
    }

    public String getIDByEmail(String email) {
        String sql = "SELECT Id FROM NguoiDung WHERE Email = ? AND TrangThai = 1";
        String idNguoiDung = "";
        try (Connection conn = DBConnext.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setObject(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                idNguoiDung = rs.getString("Id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return idNguoiDung;
    }
}
