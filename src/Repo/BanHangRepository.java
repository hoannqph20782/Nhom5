/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repo;

import Model.ChatLieu;
import Model.ChiTietGiay;

import Model.DanhMuc;
import Model.De;
import Model.MauSac;
import Model.NSX;
import Model.SanPham;
import Model.SIZE;
import Utilities.DBConnext;
import java.math.BigDecimal;
import java.util.ArrayList;

import java.sql.*;

/**
 *
 * @author asus
 */
public class BanHangRepository {

    private static final int PAGESIZE = 4;

    SanPhamRepository spr = new SanPhamRepository();
    DanhMucRepository dmr = new DanhMucRepository();
    SizeRepository sizer = new SizeRepository();
    MauSacRepository msr = new MauSacRepository();
    ChatLieuRepository clr = new ChatLieuRepository();
    DeRepository der = new DeRepository();
    NSXRepository nsxr = new NSXRepository();

    public ArrayList<ChiTietGiay> getAllChiTietGiayForPage(int page) {
        ArrayList<ChiTietGiay> listChiTietGiay = new ArrayList<>();
        try {
            Connection con = DBConnext.getConnection();
            int offset = (page - 1) * PAGESIZE;

            String sql = "SELECT * FROM CHITIETGIAY WHERE TrangThai = 1 ORDER BY SoLuong OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, offset);
            st.setInt(2, PAGESIZE);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                SanPham sp = spr.getSanPhamByID(rs.getString(3));
                DanhMuc dm = dmr.getDanhMucByID(rs.getString(2));
                SIZE s = sizer.getSizeByID(rs.getString(4));
                MauSac ms = msr.getMauSacID(rs.getString(5));
                ChatLieu cl = clr.getChatLieuByID(rs.getString(6));
                NSX nsx = nsxr.getNSXByID(rs.getString(7));
                De d = der.getDeByID(rs.getString(8));
                listChiTietGiay.add(new ChiTietGiay(rs.getInt(1), dm, sp, s, ms, cl, nsx, d, offset, rs.getBigDecimal(10), rs.getBigDecimal(11), rs.getString(12), rs.getString(13), rs.getInt(14)));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listChiTietGiay;

    }

    public int tongSoItem() {
        int tongSoTrang = 0;
        try {
            Connection con = DBConnext.getConnection();
            String sql = "SELECT COUNT(*) FROM CHITIETGIAY ;";
            PreparedStatement pstm = con.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();

            if (rs.next()) {
                tongSoTrang = rs.getInt(1);
            }
            rs.close();
            pstm.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tongSoTrang;
    }

    public ArrayList<ChiTietGiay> search(String tenSP) {
        ArrayList<ChiTietGiay> listChiTietGiay = new ArrayList<>();
        try {
            Connection con = DBConnext.getConnection();

            String sql = "SELECT * FROM CHITIETGIAY WHERE TrangThai = 1";

            PreparedStatement st = con.prepareStatement(sql);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                SanPham sp = spr.getSanPhamByID(rs.getString(3));
                DanhMuc dm = dmr.getDanhMucByID(rs.getString(2));
                SIZE s = sizer.getSizeByID(rs.getString(4));
                MauSac ms = msr.getMauSacID(rs.getString(5));
                ChatLieu cl = clr.getChatLieuByID(rs.getString(6));
                NSX nsx = nsxr.getNSXByID(rs.getString(7));
                De d = der.getDeByID(rs.getString(8));
              listChiTietGiay.add(new ChiTietGiay(rs.getInt(1), dm, sp, s, ms, cl, nsx, d, rs.getInt(9), rs.getBigDecimal(10), rs.getBigDecimal(11), rs.getString(12), rs.getString(13), rs.getInt(14)));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listChiTietGiay;
    }
}
