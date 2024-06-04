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
import Utilities.JDBCHelper;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.sql.SQLException;
import java.util.List;

public class SanPhamChiTietRepository {

    DBConnext dBConnext;
    ChiTietGiay ctg = new ChiTietGiay();
    SanPhamRepository spr = new SanPhamRepository();
    DanhMucRepository dmr = new DanhMucRepository();
    SizeRepository sizer = new SizeRepository();
    MauSacRepository msr = new MauSacRepository();
    ChatLieuRepository clr = new ChatLieuRepository();
    DeRepository der = new DeRepository();
    NSXRepository nsxr = new NSXRepository();

    public ArrayList<ChiTietGiay> getAllChiTietGiay() {
        ArrayList<ChiTietGiay> listctg = new ArrayList<>();
        String sql = "SELECT dbo.CHITIETGIAY.Id, dbo.SANPHAM.Ma, dbo.SanPham.Ten, dbo.DANHMUC.Ten as TenDM,\n"
                + "dbo.CHITIETGIAY.GiaBan, dbo.CHITIETGIAY.MoTa,\n"
                + "dbo.CHITIETGIAY.TrangThai, dbo.CHITIETGIAY.HinhAnh\n"
                + "FROM dbo.CHITIETGIAY\n"
                + "INNER JOIN dbo.SanPham ON dbo.CHITIETGIAY.IdSanPham = dbo.SanPham.Id\n"
                + "INNER JOIN dbo.DANHMUC ON dbo.CHITIETGIAY.IdDanhMuc = dbo.DANHMUC.Id;";
        try (Connection con = dBConnext.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
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
        try (Connection con = dBConnext.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
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

    public List<String> getDanhMuc() {
        List<String> lstDanhMuc = new ArrayList<>();
        String sql = "select * from DANHMUC where TrangThai = 1";
        try (Connection conn = dBConnext.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lstDanhMuc.add(rs.getString("Ten"));
            }
            return lstDanhMuc;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<String> getSanPham() {
        List<String> lstDanhMuc = new ArrayList<>();
        String sql = "select * from SANPHAM where TrangThai = 1";
        try (Connection conn = dBConnext.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lstDanhMuc.add(rs.getString("Ten"));
            }
            return lstDanhMuc;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<String> getChatLieu() {
        List<String> lstDanhMuc = new ArrayList<>();
        String sql = "select * from CHATLIEU where TrangThai = 1";
        try (Connection conn = dBConnext.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lstDanhMuc.add(rs.getString("Ten"));
            }
            return lstDanhMuc;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<String> getMauSac() {
        List<String> lstDanhMuc = new ArrayList<>();
        String sql = "select * from MAUSAC where TrangThai = 1";
        try (Connection conn = dBConnext.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lstDanhMuc.add(rs.getString("MauSac"));
            }
            return lstDanhMuc;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<String> getSize() {
        List<String> lstDanhMuc = new ArrayList<>();
        String sql = "select * from SIZE where TrangThai = 1";
        try (Connection conn = dBConnext.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lstDanhMuc.add(rs.getString("KichCo"));
            }
            return lstDanhMuc;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<String> getNSX() {
        List<String> lstDanhMuc = new ArrayList<>();
        String sql = "select * from NSX where TrangThai = 1";
        try (Connection conn = dBConnext.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lstDanhMuc.add(rs.getString("Ten"));
            }
            return lstDanhMuc;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<String> getDe() {
        List<String> lstDanhMuc = new ArrayList<>();
        String sql = "select * from DE where TrangThai = 1";
        try (Connection conn = dBConnext.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lstDanhMuc.add(rs.getString("Ten"));
            }
            return lstDanhMuc;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<ChiTietGiay> getAllSanPham() {
        ArrayList<ChiTietGiay> listctg = new ArrayList<>();
        String sql = "SELECT * FROM CHITIETGIAY";
        ResultSet rs = null;

        try {
            rs = JDBCHelper.excuteQuery(sql);
            while (rs.next()) {
                int id = rs.getInt(1);
                DanhMuc dm = dmr.getDanhMucByID(rs.getString(2));
                SanPham sp = spr.getSanPhamByID(rs.getString(3));
                SIZE s = sizer.getSizeByID(rs.getString(4));
                MauSac ms = msr.getMauSacID(rs.getString(5));
                ChatLieu cl = clr.getChatLieuByID(rs.getString(6));
                NSX nsx = nsxr.getNSXByID(rs.getString(7));
                De d = der.getDeByID(rs.getString(8));
                int soLuong = rs.getInt(9);
                BigDecimal giaNhap = rs.getBigDecimal(10);
                BigDecimal giaBan = rs.getBigDecimal(11);
                String moTa = rs.getString(12);
                String hinhAnh = rs.getString(13);
                int trangThai = rs.getInt(14);

                ChiTietGiay ctg = new ChiTietGiay(id, dm, sp, s, ms, cl, nsx, d, soLuong, giaNhap, giaBan, moTa, hinhAnh, trangThai);
                listctg.add(ctg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return listctg;
    }

    public boolean addChiTietSanPham(ChiTietGiay ctg) {
        String sql = "insert into CHITIETGIAY (IdSanPham,IdDanhMuc,GiaBan,MoTa,HinhAnh,TrangThai) values \n"
                + " (?,?,?,?,?,?)";
        try (Connection con = DBConnext.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, ctg.getIdSanPham().getIdSanPham());
            ps.setObject(2, ctg.getIdDanhMuc().getId());
            ps.setObject(3, ctg.getGiaBan());
            ps.setObject(4, ctg.getMota());
            ps.setObject(5, ctg.getHinhAnh());
            ps.setObject(6, ctg.getTrangThai());
            int result = ps.executeUpdate();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateChiTietSanPham(ChiTietGiay ctg) {
        String sql = "UPDATE CHITIETGIAY SET IdDanhMuc = ? , IdSanPham = ? ,GiaBan = ? ,MoTa = ? ,TrangThai = ?, HinhAnh = ? where Id = ?";
        try (Connection con = dBConnext.getConnection()) {
            CallableStatement ps = con.prepareCall(sql);

            ps.setObject(1, ctg.getIdDanhMuc().getId());
            ps.setObject(2, ctg.getIdSanPham().getIdSanPham());
            ps.setObject(3, ctg.getGiaBan());
            ps.setObject(4, ctg.getMota());
            ps.setObject(5, ctg.getTrangThai());
            ps.setObject(6, ctg.getHinhAnh());
            ps.setObject(7, ctg.getId());
            int result = ps.executeUpdate();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<ChiTietGiay> locSanPham(String keyword, Float giaB) {
        List<ChiTietGiay> ctgList = new ArrayList<>();
        String sql = "SELECT * FROM CHITIETGIAY ctg "
                + "JOIN DanhMuc dm ON ctg.IdDanhMuc = dm.Id "
                + "WHERE dm.Ten LIKE ? AND (? IS NULL OR ctg.giaBan = ?)";
        try (Connection connection = dBConnext.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, "%" + keyword + "%"); // Use wildcard for LIKE
            if (giaB == null) {
                preparedStatement.setNull(2, java.sql.Types.FLOAT);
                preparedStatement.setNull(3, java.sql.Types.FLOAT);
            } else {
                preparedStatement.setFloat(2, giaB);
                preparedStatement.setFloat(3, giaB);
            }

            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    DanhMuc dm = dmr.getDanhMucByID(rs.getString("IdDanhMuc"));
                    SanPham sp = spr.getSanPhamByID(rs.getString("IdSanPham"));
                    SIZE s = sizer.getSizeByID(rs.getString("IdSize"));
                    MauSac ms = msr.getMauSacID(rs.getString("IdMauSac"));
                    ChatLieu cl = clr.getChatLieuByID(rs.getString("IdChatLieu"));
                    NSX nsx = nsxr.getNSXByID(rs.getString("IdNSX"));
                    De d = der.getDeByID(rs.getString("IdDe"));

                    ChiTietGiay ctg = new ChiTietGiay(
                            rs.getInt("Id"),
                            dm, sp, s, ms, cl, nsx, d,
                            rs.getInt("SoLuong"),
                            rs.getBigDecimal("GiaNhap"),
                            rs.getBigDecimal("GiaBan"),
                            rs.getString("MoTa"),
                            rs.getString("HinhAnh"),
                            rs.getInt("TrangThai")
                    );

                    ctgList.add(ctg);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ctgList;
    }
    public ArrayList<ChiTietGiay> getDSSPCT(int idSanPham) {
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
                + "                INNER JOIN dbo.DE ON dbo.CHITIETGIAY.[IdDe] = dbo.De.Id WHERE dbo.CHITIETGIAY.IdSanPham = ? ;";
        try (Connection con = dBConnext.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idSanPham);  // Set the idSanPham parameter in the query
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
