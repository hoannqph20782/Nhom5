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
import Model.SIZE;
import Model.SanPham;
import Utilities.DBConnext;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

public class SanPhamChiTietRepository {

    DBConnext dBConnext;

    public ArrayList<ChiTietGiay> getAllChiTietGiay() {
        ArrayList<ChiTietGiay> listctg = new ArrayList<>();
        String sql = "SELECT dbo.CHITIETGIAY.Id, dbo.SANPHAM.Ma, dbo.SanPham.Ten, dbo.DANHMUC.Ten as TenDM,\n"
                + "dbo.CHITIETGIAY.GiaBan, dbo.CHITIETGIAY.MoTa,\n"
                + "dbo.CHITIETGIAY.TrangThai, dbo.CHITIETGIAY.HinhAnh\n"
                + "FROM dbo.CHITIETGIAY\n"
                + "INNER JOIN dbo.SanPham ON dbo.CHITIETGIAY.IdSanPham = dbo.SanPham.Id\n"
                + "INNER JOIN dbo.DANHMUC ON dbo.CHITIETGIAY.IdDanhMuc = dbo.DANHMUC.Id;";
        try ( Connection con = dBConnext.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                // Tạo đối tượng ChiTietGiay và thêm vào danh sách
                ChiTietGiay ctg = new ChiTietGiay();
                ctg.setId(rs.getInt("Id"));
                ctg.setGiaBan(rs.getBigDecimal("GiaBan"));
                ctg.setMota(rs.getString("MoTa"));
                ctg.setTrangThai(rs.getInt("TrangThai"));
                ctg.setHinhAnh(rs.getString("HinhAnh"));
                // Tạo đối tượng SanPham và thêm vào ChiTietGiay
                SanPham sp = new SanPham();
                sp.setMa(rs.getString("Ma"));
                sp.setTen(rs.getString("Ten"));
                ctg.setIdSanPham(sp);
                // Tạo đối tượng DanhMuc và thêm vào ChiTietGiay
                DanhMuc dm = new DanhMuc();
                dm.setTen(rs.getString("TenDM"));
                ctg.setIdDanhMuc(dm);

                // Thêm ChiTietGiay vào danh sách
                listctg.add(ctg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listctg;
    }

    public ArrayList<ChiTietGiay> getAllChiTietSanPham() {
        ArrayList<ChiTietGiay> listctg = new ArrayList<>();
        String sql = "	SELECT dbo.CHITIETGIAY.Id,\n"
                + "              dbo.SANPHAM.Ten,\n"
                + "                dbo.DANHMUC.Ten as TenDM,\n"
                + "                dbo.Size.KichCo,\n"
                + "                 dbo.MAUSAC.MauSac,\n"
                + "                dbo.CHATLIEU.Ten as TenCL, \n"
                + "                dbo.NSX.Ten as TenNSX,\n"
                + "                 dbo.DE.Ten as TenDE,\n"
                + "                 dbo.CHITIETGIAY.SoLuong,\n"
                + "                 dbo.CHITIETGIAY.GiaBan,\n"
                + "                 dbo.CHITIETGIAY.Mota,\n"
                + "                 dbo.CHITIETGIAY.TrangThai\n"
                + "                FROM dbo.CHITIETGIAY\n"
                + "                INNER JOIN dbo.SanPham ON dbo.CHITIETGIAY.IdSanPham = dbo.SanPham.Id\n"
                + "                INNER JOIN dbo.DANHMUC ON dbo.CHITIETGIAY.IdDanhMuc = dbo.DANHMUC.Id\n"
                + "                INNER JOIN dbo.Size  ON dbo.CHITIETGIAY.IdSize = dbo.Size.Id\n"
                + "                INNER JOIN dbo.MauSac ON dbo.CHITIETGIAY.[IdMauSac] = dbo.MauSac.Id\n"
                + "                INNER JOIN dbo.ChatLieu ON dbo.CHITIETGIAY.IdChatLieu = dbo.ChatLieu.Id\n"
                + "                INNER JOIN dbo.NSX ON dbo.CHITIETGIAY.[IdNSX] = dbo.NSX.Id\n"
                + "                INNER JOIN dbo.DE ON dbo.CHITIETGIAY.[IdDe] = dbo.De.Id;";
        try ( Connection con = dBConnext.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                // Tạo đối tượng ChiTietGiay và thêm vào danh sách
                ChiTietGiay ctg = new ChiTietGiay();
                ctg.setId(rs.getInt("Id"));
                ctg.setGiaBan(rs.getBigDecimal("GiaBan"));
                ctg.setMota(rs.getString("MoTa"));
                ctg.setTrangThai(rs.getInt("TrangThai"));

                ctg.setSoLuong(rs.getInt("SoLuong"));
                // Tạo đối tượng SanPham và thêm vào ChiTietGiay
                SanPham sp = new SanPham();

                sp.setTen(rs.getString("Ten"));
                ctg.setIdSanPham(sp);
                // Tạo đối tượng DanhMuc và thêm vào ChiTietGiay
                DanhMuc dm = new DanhMuc();
                dm.setTen(rs.getString("TenDM"));
                ctg.setIdDanhMuc(dm);

                SIZE sz = new SIZE();
                sz.setKichCo(rs.getInt("KichCo"));
                ctg.setIdSize(sz);

                MauSac ms = new MauSac();
                ms.setMauSac(rs.getString("MauSac"));
                ctg.setIdMauSac(ms);

                ChatLieu cl = new ChatLieu();
                cl.setTen(rs.getString("TenCL"));
                ctg.setIdChatLieu(cl);

                NSX nsx = new NSX();
                nsx.setTen(rs.getString("TenNSX"));
                ctg.setIdNSX(nsx);

                De de = new De();
                de.setTen(rs.getString("TenDE"));
                ctg.setIdDe(de);

                listctg.add(ctg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listctg;
    }

}
