/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repo;

import Model.GioHang;
import Model.HoaDon;
import Model.HoaDonChiTiet;
import Model.HoaDonSearch;
import Model.KhachHang;
import Model.KhuyenMai;
import Model.NguoiDung;
import Utilities.DBConnext;
import java.util.ArrayList;
import java.sql.*;
import java.time.LocalDate;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ADMIN
 */
public class HoaDonRepo {

    public int randomMa() {
        return 10000 + new Random().nextInt(90000);
    }

    public ArrayList getGioHang(String idHD) {
        ArrayList<GioHang> listGH = new ArrayList<>();
        try {
            Connection conn = DBConnext.getConnection();
            String sql = "  SELECT ctg.Id as ID, sp.Ten as TEN, hdct.SoLuong as SL, cl.Ten as CL, ms.MauSac as MS, nsx.Ten as HANG, s.KichCo as SIZE, hdct.DonGia, sp.TrangThai\n"
                    + "  FROM HOADONCHITIET as hdct JOIN CHITIETGIAY as ctg on hdct.IdCTD = ctg.Id\n"
                    + "  JOIN SANPHAM as sp on ctg.IdSanPham = sp.Id JOIN HOADON as hd on hdct.IdHD = hd.Id JOIN CHATLIEU as cl on ctg.IdChatLieu = cl.Id\n"
                    + "  JOIN MAUSAC as ms on ctg.IdMauSac = ms.Id JOIN NSX as nsx on ctg.IdNSX = nsx.Id JOIN SIZE as s on ctg.IdSize = s.Id\n"
                    + "  WHERE hd.Id = ?";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, idHD);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                GioHang gh = new GioHang();
                gh.setIdCTSP(rs.getString("ID"));
                gh.setTenSanPham(rs.getString("TEN"));
                gh.setSoLuong(rs.getInt("SL"));
                gh.setChatLieu(rs.getString("CL"));
                gh.setMauSac(rs.getString("MS"));
                gh.setHang(rs.getString("HANG"));
                gh.setSize(rs.getString("SIZE"));
                gh.setDonGia(rs.getDouble("DONGIA"));
                gh.setTrangThai(rs.getInt("TRANGTHAI"));
                listGH.add(gh);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listGH;
    }

    public String getIdHoaDon(String maHD) {
        String idHD = null;
        try {
            Connection conn = DBConnext.getConnection();
            String sql = "SELECT Id FROM HOADON WHERE Ma = ?";
            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, maHD);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                idHD = rs.getString("Id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return idHD;
    }
     public ArrayList<HoaDon> getAllHoaDon() {
        ArrayList<HoaDon> hoaDons = new ArrayList<>();
        String sql = "SELECT hd.ID, hd.Ma as MaHoaDon, nd.Ma as maNguoiDung, kh.Sdt, hd.NgayTao,  hd.TongTien\n"
                + "FROM HoaDon hd JOIN NguoiDung ND ON hd.IDND = ND.ID\n"
                + "JOIN  KhachHang kh ON hd.IDKH = kh.ID;";
        try ( Connection con = DBConnext.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HoaDon hd = new HoaDon();
                hd.setId(rs.getString("Id"));
                hd.setMa(rs.getString("MaHoaDon"));
                hd.setNgayTao(rs.getDate("NgayTao"));
                hd.setTongTien(rs.getDouble("TongTien"));
                NguoiDung nd = new NguoiDung();
                nd.setMaNguoiDung(rs.getString("maNguoiDung"));
                hd.setIdKhachHang(rs.getString("Sdt"));
                hd.setIdNguoiDung(nd);
                hoaDons.add(hd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hoaDons;
    }

    public ArrayList<HoaDonChiTiet> getAllHoaDonChiTiet() {
        ArrayList<HoaDonChiTiet> listHoaDonChiTiet = new ArrayList<>();
        String sql = "SELECT hd.Ma as MaHoaDon, nd.Ma as MaNguoiDung, kh.Sdt as SdtKhachHang,kh.Ma as MaKhachHang,hd.NgayTao as NgayTaoHoaDon,sp.Ten as TenSanPham, hct.SoLuong,hct.DonGia\n"
                + "FROM HoaDon hd\n"
                + "JOIN HoaDonChiTiet hct ON hd.ID = hct.IDHD\n"
                + "JOIN NguoiDung nd ON hd.IDND = nd.ID\n"
                + "JOIN KhachHang kh ON hd.IDKH = kh.ID\n"
                + "JOIN ChiTietGiay ctg ON hct.IDCTD = ctg.ID\n"
                + "JOIN SanPham sp ON ctg.IdSanPham = sp.ID;";

        try ( Connection con = DBConnext.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();

                // Giả sử bạn có các setter tương ứng trong class HoaDonChiTiet
                hoaDonChiTiet.setIdHD(rs.getString("MaHoaDon"));
                hoaDonChiTiet.setIdNguoiDung(rs.getString("MaNguoiDung"));
                hoaDonChiTiet.setMaKhachHang(rs.getString("MaKhachHang"));
                hoaDonChiTiet.setIdKhachHang(rs.getString("SdtKhachHang"));
                hoaDonChiTiet.setNgayTao(rs.getDate("NgayTaoHoaDon"));
                hoaDonChiTiet.setIdCTG(rs.getString("TenSanPham"));
                hoaDonChiTiet.setSoLuong(rs.getInt("SoLuong"));
                hoaDonChiTiet.setDonGia(rs.getDouble("DonGia"));

                listHoaDonChiTiet.add(hoaDonChiTiet);
            }
        } catch (Exception e) {
            e.printStackTrace(); // In ra ngoại lệ nếu có lỗi
        }
        return listHoaDonChiTiet;
    }
     public ArrayList<HoaDonChiTiet> getHoaDonChiTietByMaHoaDon(String maHoaDon) {
        ArrayList<HoaDonChiTiet> listHoaDonChiTiet = new ArrayList<>();
        String sql = "SELECT hd.Ma as MaHoaDon, nd.Ma as MaNguoiDung,kh.Ma as MaKhachHang, kh.Sdt, hd.NgayTao, sp.Ten, hdc.SoLuong, hdc.DonGia\n"
                + "FROM HoaDonChiTiet hdc\n"
                + "JOIN HoaDon hd ON hdc.IDHD = hd.ID\n"
                + "JOIN NguoiDung nd ON hd.IDND = nd.ID\n"
                + "JOIN KhachHang kh ON hd.IDKH = kh.ID\n"
                + "JOIN ChiTietGiay ctg ON hdc.IdCTD = ctg.ID\n"
                + "JOIN SanPham sp ON ctg.Id = sp.ID\n"
                + "WHERE \n"
                + "    hd.Ma = ?;";
        try ( Connection con = DBConnext.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, maHoaDon);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HoaDonChiTiet hoaDonChiTiet = new HoaDonChiTiet();
                hoaDonChiTiet.setIdHD(rs.getString("MaHoaDon"));
                hoaDonChiTiet.setIdNguoiDung(rs.getString("MaNguoiDung"));
                hoaDonChiTiet.setMaKhachHang(rs.getString("MaKhachHang"));
                hoaDonChiTiet.setIdKhachHang(rs.getString("Sdt"));
                hoaDonChiTiet.setNgayTao(rs.getDate("NgayTao"));
                hoaDonChiTiet.setIdCTG(rs.getString("Ten"));
                hoaDonChiTiet.setSoLuong(rs.getInt("SoLuong"));
                hoaDonChiTiet.setDonGia(rs.getDouble("DonGia"));
                listHoaDonChiTiet.add(hoaDonChiTiet);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listHoaDonChiTiet;
    }

   public ArrayList<HoaDonSearch> search(String maKH) {
        ArrayList<HoaDonSearch> listTimKiem = new ArrayList<>();
        String sql = "select HOADON.Id, HOADON.Ma, NGUOIDUNG.Ma As MaNV , KHACHHANG.Ma As maKH,KHACHHANG.Sdt As sdtKH , NgayTao, TongTien, HOADON.TrangThai, MoTa\n"
                + "from HOADON join NGUOIDUNG On HOADON.IdND=NGUOIDUNG.Id join KHACHHANG On HOADON.IdKH= KHACHHANG.Id\n";
        try {
            Connection con = DBConnext.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listTimKiem.add(new HoaDonSearch(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getDate(6),
                        rs.getFloat(7),
                        rs.getInt(8),
                        rs.getString(9)));
            }
        } catch (Exception e) {
            System.out.println("Lỗi Tim Kiem hoa don!");
        }
        return listTimKiem;
    }

  public ArrayList<HoaDonSearch> searchDate(String ngayTao, String ngayThanhToan) {
        ArrayList<HoaDonSearch> listSearch = new ArrayList<>();
        String sql = "select HOADON.Id, HOADON.Ma, NGUOIDUNG.Ma As MaNV , KHACHHANG.Ma As maKH,KHACHHANG.Sdt As sdtKH , NgayTao, TongTien, HOADON.TrangThai, MoTa\n"
                + "from HOADON join NGUOIDUNG On HOADON.IdND=NGUOIDUNG.Id join KHACHHANG On HOADON.IdKH= KHACHHANG.Id\n"
                + "WHERE CONVERT(date , NgayTao )  >= ? and CONVERT(date , NgayThanhToan )  <= ? ";
        try {
            Connection con = DBConnext.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, ngayTao);
            ps.setString(2, ngayThanhToan);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listSearch.add(new HoaDonSearch(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getDate(6),
                        rs.getFloat(7),
                        rs.getInt(8),
                        rs.getString(9)));
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Lỗi Search Date hoa don!");
        }
        return listSearch;

    }

}
