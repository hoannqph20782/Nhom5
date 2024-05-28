package Repo;

import Model.KM;
import Utilities.DBConnext;
import java.util.ArrayList;
import java.sql.*;
import Interface.KMRepositoryImpl;

public class KMRepository implements KMRepositoryImpl {

    DBConnext cdao = new DBConnext();

    @Override
    public ArrayList<KM> getAllKM() {
        ArrayList<KM> dskm = new ArrayList<>();
        String sql = "Select Ma, Ten, PhanTramGiam, GiaGiam , SoLuong, NgayBatDau, NgayKetThuc, HinhThucGiam, TrangThai, MoTa from KHUYENMAI";
        try {
            Connection con = cdao.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                dskm.add(new KM(rs.getString(1),
                        rs.getString(2),
                        rs.getFloat(3),
                        rs.getDouble(4),
                        rs.getInt(5),
                        rs.getDate(6),
                        rs.getDate(7),
                        rs.getInt(8),
                        rs.getInt(9),
                        rs.getString(10)));
            }
        } catch (Exception e) {
            System.out.println("Lỗi GetAll KhuyenMai!");
        }
        return dskm;
    }

    @Override
    public boolean add(KM km) {
        String sql = "INSERT INTO KHUYENMAI (Ma, Ten, PhanTramGiam, GiaGiam, SoLuong, NgayBatDau, NgayKetThuc, HinhThucGiam, TrangThai, MoTa)\n"
                + "VALUES \n"
                + "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            Connection con = cdao.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setObject(1, km.getMa());
            ps.setObject(2, km.getTen());
            ps.setObject(3, km.getPhanTramGiam());
            ps.setObject(4, km.getGiaGiam());
            ps.setObject(5, km.getSoLuong());
            ps.setObject(6, km.getNgayBatDau());
            ps.setObject(7, km.getNgayKetThuc());
            ps.setObject(8, km.getHinhThucGiam());
            ps.setObject(9, km.getTrangThai());
            ps.setObject(10, km.getMoTa());

            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("Lỗi ADD KhuyenMaiiii");
        }
        return false;
    }

    @Override
    public boolean update(String ma, KM km) {
        String sql = "Update KHUYENMAI Set Ten= ?, PhanTramGiam = ?, GiaGiam = ?,"
                + " SoLuong = ?, NgayBatDau = ?, NgayKetThuc = ?, HinhThucGiam = ?, TrangThai= ?, MoTa = ? where Ma= ?";
        try {
            Connection con = cdao.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setObject(1, km.getTen());
            ps.setObject(2, km.getPhanTramGiam());
            ps.setObject(3, km.getGiaGiam());
            ps.setObject(4, km.getSoLuong());
            ps.setObject(5, km.getNgayBatDau());
            ps.setObject(6, km.getNgayKetThuc());
            ps.setObject(7, km.getHinhThucGiam());
            ps.setObject(8, km.getTrangThai());
            ps.setObject(9, km.getMoTa());
            ps.setObject(10, ma);

            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("Lỗi update KHUYEN MAIiii");
        }

        return false;
    }

    @Override
    public ArrayList<KM> searchDate(String ngayBatDau, String ngayKetThuc) {
        ArrayList<KM> listSearch = new ArrayList<>();
        String sql = "Select Ma, Ten, PhanTramGiam, GiaGiam , SoLuong, NgayBatDau, NgayKetThuc, HinhThucGiam, TrangThai, MoTa from KHUYENMAI\n"
                + "WHERE NgayBatDau >= ? and NgayKetThuc <= ? ";
        try {
            Connection con = cdao.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, ngayBatDau);
            ps.setString(2, ngayKetThuc);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listSearch.add(new KM(rs.getString(1),
                        rs.getString(2),
                        rs.getFloat(3),
                        rs.getDouble(4),
                        rs.getInt(5),
                        rs.getDate(6),
                        rs.getDate(7),
                        rs.getInt(8),
                        rs.getInt(9),
                        rs.getString(10)));
            }
        } catch (Exception e) {
            System.out.println("Lỗi SearchDate KhuyenMai!");
        }
        return listSearch;
    }

    @Override
    public ArrayList<KM> searchTinhTrang(int tinhTrang) {
        ArrayList<KM> listSeachTT = new ArrayList<>();
        String sql = "Select Ma, Ten, PhanTramGiam, GiaGiam , SoLuong, NgayBatDau,"
                + " NgayKetThuc, HinhThucGiam, TrangThai, MoTa from KHUYENMAI where TrangThai =?";
        try {
            Connection con = cdao.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, tinhTrang);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listSeachTT.add(new KM(rs.getString(1),
                        rs.getString(2),
                        rs.getFloat(3),
                        rs.getDouble(4),
                        rs.getInt(5),
                        rs.getDate(6),
                        rs.getDate(7),
                        rs.getInt(8),
                        rs.getInt(9),
                        rs.getString(10)));
            }
        } catch (Exception e) {
            System.out.println("Lỗi SearchTinhTrang KhuyenMai!");
        }
        return listSeachTT;
    }

    @Override
    public ArrayList<KM> search(String maKM) {
        ArrayList<KM> listTimKiem = new ArrayList<>();
        String sql = "Select Ma, Ten, PhanTramGiam, GiaGiam , SoLuong, NgayBatDau, NgayKetThuc, HinhThucGiam, TrangThai, MoTa from KHUYENMAI";
        try {
            Connection con = cdao.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listTimKiem.add(new KM(rs.getString(1),
                        rs.getString(2),
                        rs.getFloat(3),
                        rs.getDouble(4),
                        rs.getInt(5),
                        rs.getDate(6),
                        rs.getDate(7),
                        rs.getInt(8),
                        rs.getInt(9),
                        rs.getString(10)));
            }
        } catch (Exception e) {
            System.out.println("Lỗi Tim Kiem Khuyến Mại!");
        }
        return listTimKiem;
    }

    @Override
    public ArrayList<KM> searchHinhThuc(int hinhThuc) {
        ArrayList<KM> listSeachHT = new ArrayList<>();
        String sql = "Select Ma, Ten, PhanTramGiam, GiaGiam , SoLuong, "
                + "NgayBatDau, NgayKetThuc, HinhThucGiam, TrangThai, MoTa from KHUYENMAI where HinhThucGiam =?";
        try {
            Connection con = cdao.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, hinhThuc);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listSeachHT.add(new KM(rs.getString(1),
                        rs.getString(2),
                        rs.getFloat(3),
                        rs.getDouble(4),
                        rs.getInt(5),
                        rs.getDate(6),
                        rs.getDate(7),
                        rs.getInt(8),
                        rs.getInt(9),
                        rs.getString(10)));
            }
        } catch (Exception e) {
            System.out.println("Lỗi SearchHinhThuc KhuyenMai!");
        }
        return listSeachHT;
    }

}
