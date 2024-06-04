/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repo;

import Model.ChiTietGiay;
import Model.GioHang;
import Model.HoaDon;
import Model.HoaDonChiTiet;
import Model.KhachHang;
import Model.KhuyenMai;
import Model.NguoiDung;
import Utilities.DBConnext;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Windows
 */
public class HoaDonChoRepo {

    public long generateRandomId() {
        return 10000 + new Random().nextInt(90000);
    }

    public ArrayList<HoaDon> getAllHoaDonCho() {
        ArrayList<HoaDon> listHD = new ArrayList<>();

        try {
            Connection con = DBConnext.getConnection();
            String sql = "SELECT HOADON.Id, HOADON.Ma, HOADON.TongTien, HOADON.ThanhTien, HOADON.NgayTao, HOADON.TrangThai,\n"
                    + "    NGUOIDUNG.Id AS IDND, NGUOIDUNG.Ma AS MAND, NGUOIDUNG.Ten AS TENND,\n"
                    + "    KHACHHANG.Id AS IDKH, KHACHHANG.Ten AS TENKH, KHACHHANG.Sdt AS SDTKH,\n"
                    + "    KHUYENMAI.Id AS IDKM, KHUYENMAI.Ma AS MAKM, KHUYENMAI.PhanTramGiam AS PTGKM\n"
                    + "FROM HOADON\n"
                    + "LEFT JOIN NGUOIDUNG ON HOADON.IdND = NGUOIDUNG.Id\n"
                    + "LEFT JOIN KHACHHANG ON HOADON.IdKH = KHACHHANG.Id\n"
                    + "LEFT JOIN KHUYENMAI ON HOADON.IdKM = KHUYENMAI.Id\n"
                    + "WHERE HOADON.TrangThai = 0  ORDER by Id desc;";
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                HoaDon hd = new HoaDon();
                hd.setId(rs.getString("Id"));
                hd.setMa(rs.getString("Ma"));
                hd.setNgayTao(rs.getDate("NgayTao"));
                hd.setTrangThai(rs.getInt("TrangThai"));
                hd.setIdKhachHang(rs.getString("TENKH"));
                hd.setIdKhuyenMai(rs.getString("MAKM"));

                listHD.add(hd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listHD;

    }

    public ArrayList<HoaDonChiTiet> getAllHoaDonTatCa() {
        ArrayList<HoaDonChiTiet> listHD = new ArrayList<>();
        try {
            Connection con = DBConnext.getConnection();
            String sql = "SELECT * FROM HOADONCHITIET ";
            PreparedStatement st = con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                HoaDonChiTiet hd = new HoaDonChiTiet();
                hd.setId(rs.getString("Id"));
                hd.setIdCTG(rs.getString("IdCTD"));
                hd.setSoLuong(rs.getInt("SoLuong"));
                hd.setNgayTao(rs.getDate("NgayTao"));
                hd.setTrangThai(rs.getInt("TrangThai"));

                listHD.add(hd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listHD;

    }

    public NguoiDung getNguoiDungById(Integer id) {
        NguoiDung nguoiDung = null;
        try {
            Connection con = DBConnext.getConnection();
            String sql = "SELECT * FROM NGUOIDUNG WHERE Id =?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                nguoiDung = new NguoiDung();
                nguoiDung.setIdNguoiDung(rs.getInt("Id"));
                nguoiDung.setTenNguoiDung(rs.getString("Ten"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nguoiDung;
    }

    public String getIdKMbyMa(String maKM) {
        String idKM = null;
        try {
            Connection con = DBConnext.getConnection();
            String sql = "SELECT Id from KHUYENMAI WHERE Ma = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, maKM);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                idKM = rs.getString("Id");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return idKM;
    }

    public KhuyenMai getKhuyenMaibyMa(String makm) {
        KhuyenMai km = null;
        String sql = "SELECT * FROM KHUYENMAI WHERE Ma = ?";
        try {
            Connection con = DBConnext.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, makm);
            ResultSet rs = ps.executeQuery();
            try {
                if (rs.next()) {
                    km = new KhuyenMai();
                    km.setId(rs.getInt("Id"));
                    km.setMa(rs.getString("Ma"));
                    km.setHinhThucGiam(rs.getInt("HinhThucGiam"));
                    km.setGiaGiam(rs.getDouble("GiaGiam"));
                    km.setPhanTramGiam(rs.getDouble("PhanTramGiam"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return km;
    }

    public KhuyenMai getHinThucGiamibyMa(String makm) {
        KhuyenMai km = null;
        String sql = "SELECT HinhThucGiam  FROM KHUYENMAI WHERE Ma = ?";
        try {
            Connection con = DBConnext.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, makm);
            ResultSet rs = ps.executeQuery();
            try {
                if (rs.next()) {
                    km = new KhuyenMai();
                    km.setHinhThucGiam(rs.getInt("HinhThucGiam"));
                }
            } catch (Exception e) {
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return km;
    }

    public Double getGiaTienHDCTtoTongTienHDbyIdHD(String idHD) {
        Double row = null;
        String sql = "SELECT SUM(DonGia) FROM HOADONCHITIET WHERE IdHD = ?";
        try {
            Connection con = DBConnext.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, idHD);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                row = rs.getDouble(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }

    public void capNhatTongTienHoaDon(String idHD, Double tongTien) {
        String sql = "UPDATE HOADON SET TongTien = TongTien+? WHERE Id = ?";
        try {
            Connection con = DBConnext.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDouble(1, tongTien);
            ps.setString(2, idHD);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getSDTKHbyIDHD(String idHD) {
        String sdt = null;
        try {
            Connection con = DBConnext.getConnection();
            String sql = "SELECT KHACHHANG.Sdt FROM HOADON JOIN KHACHHANG ON HOADON.IdKH = KHACHHANG.Id WHERE HOADON.Id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, idHD);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                sdt = rs.getString("Sdt");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sdt;
    }

    public String getPhanTramGiambyIdHD(String idHD) {
        String ptg = null;
        try {
            Connection con = DBConnext.getConnection();
            String sql = "SELECT KHUYENMAI.PhanTramGiam FROM HOADON JOIN KHUYENMAI ON HOADON.IdKM = KHUYENMAI.Id WHERE HOADON.Id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, idHD);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ptg = rs.getString("PhanTramGiam");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ptg;
    }

    public String getGiaGiambyIdHD(String idHD) {
        String ptg = null;
        try {
            Connection con = DBConnext.getConnection();
            String sql = "SELECT KHUYENMAI.GiaGiam FROM HOADON JOIN KHUYENMAI ON HOADON.IdKM = KHUYENMAI.Id WHERE HOADON.Id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, idHD);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ptg = rs.getString("GiaGiam");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ptg;
    }

    public void updateKhuyenMaibyIdHoaDon(String idHD, String idKM) {

        String sql = "UPDATE HOADON SET IdKM = ? WHERE Id = ?";
        try {
            Connection con = DBConnext.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, idKM);
            ps.setString(2, idHD);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList getAllGioHang(String idHD) {
        ArrayList<GioHang> list = new ArrayList<>();
        try {
            Connection con = DBConnext.getConnection();
            String sql = "SELECT HOADONCHITIET.Id, HOADONCHITIET.IdHD, HOADONCHITIET.IdCTD,HOADONCHITIET.SoLuong,HOADONCHITIET.DonGia, HOADONCHITIET.NgayTao,HOADONCHITIET.NgaySua,HOADONCHITIET.TrangThai,CHITIETGIAY.Id,SANPHAM.Ten AS TENSP , NSX.Ten AS TENNSX, SIZE.KichCo , DANHMUC.Ten AS TENDM, CHATLIEU.Ten AS TENCL , MAUSAC.MauSac, DE.Ten AS TENDE, CHITIETGIAY.GiaBan\n"
                    + "\n"
                    + "FROM HOADONCHITIET \n"
                    + "					JOIN CHITIETGIAY ON HOADONCHITIET.IdCTD = CHITIETGIAY.Id\n"
                    + "					JOIN  SANPHAM ON CHITIETGIAY.IdSanPham = SANPHAM.Id\n"
                    + "					JOIN NSX ON CHITIETGIAY.IdNSX = NSX.Id\n"
                    + "					JOIN DANHMUC ON CHITIETGIAY.IdDanhMuc = DANHMUC.Id\n"
                    + "					JOIN CHATLIEU ON CHITIETGIAY.IdChatLieu = CHATLIEU.Id\n"
                    + "					JOIN MAUSAC ON CHITIETGIAY.IdMauSac = MAUSAC.Id\n"
                    + "					JOIN DE ON CHITIETGIAY.IdDe = DE.Id\n"
                    + "					JOIN SIZE ON CHITIETGIAY.IdSize = SIZE.Id\n"
                    + "					WHERE IdHD = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, idHD);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                GioHang gh = new GioHang();
                gh.setIdCTSP(rs.getString("Id"));
                gh.setSoLuong(rs.getInt("SoLuong"));

                gh.setDonGia(rs.getDouble("DonGia"));
                gh.setTrangThai(rs.getInt("TrangThai"));
                gh.setTenSanPham(rs.getString("TENSP"));

                gh.setChatLieu(rs.getString("TENCL"));
                gh.setSize(rs.getString("KichCo"));
                gh.setHang(rs.getNString("TENNSX"));

                gh.setMauSac(rs.getString("MauSac"));
                list.add(gh);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<HoaDonChiTiet> getAllHoaDonChiTietTheoIdHD(String idHD) {
        ArrayList<HoaDonChiTiet> listHD = new ArrayList<>();
        try {
            Connection con = DBConnext.getConnection();
            String sql = "SELECT * FROM HOADONCHITIET WHERE IdHD = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, idHD);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                HoaDonChiTiet hd = new HoaDonChiTiet();
                hd.setId(rs.getString("Id"));
                hd.setIdCTG(rs.getString("IdCTD"));
                hd.setSoLuong(rs.getInt("SoLuong"));
                hd.setNgayTao(rs.getDate("NgayTao"));
                hd.setTrangThai(rs.getInt("TrangThai"));

                listHD.add(hd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listHD;
    }

    public String getIdHoaDonByMa(String maHD) {
        String idHD = null;

        try {
            Connection con = DBConnext.getConnection();
            String sql = "SELECT Id FROM HOADON WHERE Ma = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, maHD);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                idHD = rs.getString("Id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return idHD;
    }

    public String getIdCTSPbyIdHDCT(String idHDCT) {
        String idCTD = null;

        String sql = "SELECT IdCTD FROM HOADONCHITIET WHERE Id = ?";
        try {
            Connection con = DBConnext.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, idHDCT);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                idCTD = rs.getString("IdCTD");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return idCTD;
    }

    public KhachHang getKhachHangById(String id) {
        KhachHang khach = null;
        String sql = "SELECT * FROM KHACHHANG WHERE Id = ?";
        try ( Connection con = DBConnext.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, id);
            try ( ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    khach = new KhachHang();
                    khach.setId(rs.getInt("Id"));
                    khach.setTen(rs.getString("Ten"));
                    khach.setSdt(rs.getString("Sdt"));
                    // Thêm các trường thông tin khác nếu cần
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return khach;
    }

    public String getIDKHbyMa(String maKH) {
        String idKH = null;
        try {
            Connection con = DBConnext.getConnection();
            String sql = "SELECT Id from KHACHHANG where Ma = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, maKH);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                idKH = rs.getString("Id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return idKH;
    }

    public Integer addHoaDonCho() {
        Integer row = null;
        String sql = "INSERT INTO HOADON (Ma, NgayTao, TrangThai) VALUES(?,?,?)";
        try {
            Connection con = DBConnext.getConnection();
            PreparedStatement ptm = con.prepareStatement(sql);
            Long maHD = generateRandomId();
            ptm.setString(1, "HD" + maHD);
            ptm.setDate(2, java.sql.Date.valueOf(LocalDate.now()));
            ptm.setInt(3, 0);
            row = ptm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }

    public Integer addKhachHang(KhachHang kh) {
        Integer row = null;
        String sql = "INSERT INTO KHACHHANG (Ma, Ten, Sdt,TrangThai) VALUES (?,?,?,?)";
        try {
            Connection con = DBConnext.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            Long ma = generateRandomId();
            ps.setString(1, "KH" + ma);
            ps.setString(2, kh.getTen());
            ps.setString(3, kh.getSdt());
            ps.setInt(4, 1);
            row = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }

    public void xoaKhachHangKhoiHoaDon(String maHD) {
        String sql = "UPDATE HOADON SET IdKH = NULL WHERE Ma = ?";
        try {
            Connection con = DBConnext.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, maHD);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Integer checkGioHang(String idHD, String idCTSP) {
        Integer row = 0;
        String sql = "SELECT * FROM HOADONCHITIET AS HDCT WHERE IdHD = ? AND HDCT.IdCTD =?";
        try {
            Connection con = DBConnext.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, idHD);
            ps.setString(2, idCTSP);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                row = 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }

    public void xoaGioHang(String id) {
        String sql = "  DELETE HOADONCHITIET WHERE IdCTD = ? AND TrangThai = 0";
        try {
            Connection con = DBConnext.getConnection();
            PreparedStatement ptm = con.prepareStatement(sql);
            ptm.setString(1, id);
            ptm.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Integer updateDSSP(Integer slsp, String Id) {
        Integer row = null;
        String sql = "UPDATE CHITIETGIAY SET SoLuong = ? WHERE Id =?";
        try {
            Connection n = DBConnext.getConnection();
            PreparedStatement ptm = n.prepareCall(sql);
            ptm.setInt(1, slsp);
            ptm.setString(2, Id);
            row = ptm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }

    public void updateKHinHoaDon(String idHD, String idKH) {
        String sql = "UPDATE HOADON set IdKH =? where Id = ?";
        try {
            Connection con = DBConnext.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, idKH);
            ps.setString(2, idHD);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Integer updateSLGH(Integer slsp, String Id) {
        Integer row = null;
        String sql = "UPDATE HOADONCHITIET SET SoLuong = ? WHERE Id = ?";
        try {
            Connection conn = DBConnext.getConnection();
            PreparedStatement stm = conn.prepareCall(sql);
            stm.setInt(1, slsp);
            stm.setString(2, Id);
            row = stm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }

    public Integer addHoaDonChiTiet(HoaDonChiTiet hdct) {
        Integer row = null;
        String sql = "INSERT INTO HOADONCHITIET(IdHD,IdCTD,SoLuong,DonGia,NgayTao,TrangThai) VALUES (?,?,?,?,?,?)";

        try {
            Connection con = DBConnext.getConnection();
            PreparedStatement ptm = con.prepareStatement(sql);

            ptm.setString(1, hdct.getIdHD());
            ptm.setString(2, hdct.getIdCTG());
            ptm.setInt(3, hdct.getSoLuong());
            ptm.setDouble(4, hdct.getDonGia());
            ptm.setDate(5, java.sql.Date.valueOf(LocalDate.now()));
            ptm.setInt(6, 0);

            row = ptm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }

    public void xoaHoaDonChiTiet(String id) {
        String sql = "DELETE  FROM HOADONCHITIET WHERE IdHD =? AND  TrangThai =0";
        try {
            Connection con = DBConnext.getConnection();
            PreparedStatement ptm = con.prepareStatement(sql);
            ptm.setString(1, id);
            ptm.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void xoaKhuyenMaiKhoiHoaDon(String maHD) {
        String sql = "UPDATE HOADON SET IdKM = NULL WHERE Ma = ?";
        try {
            Connection con = DBConnext.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, maHD);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void xoaHoaDonCho(String ma) {
        String sql = "DELETE FROM HOADON WHERE Ma = ?";
        try {
            Connection con = DBConnext.getConnection();
            PreparedStatement ptm = con.prepareStatement(sql);
            ptm.setString(1, ma);
            ptm.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void capNhatSoLuongChiTietSanPham(String idCTSP, int slXoa) {

        String sql = "UPDATE CHITIETGIAY SET SoLuong = SoLuong+? WHERE Id= ?";
        try {
            Connection connection = DBConnext.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, slXoa);
            ps.setString(2, idCTSP);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Integer updateSoLuongChiTietSanPham(Integer slsp, String Id) {
        Integer row = null;
        String sql = "UPDATE CHITIETGIAY SET SoLuong = ? WHERE Id =?";
        try {
            Connection n = DBConnext.getConnection();
            PreparedStatement ptm = n.prepareCall(sql);
            ptm.setInt(1, slsp);
            ptm.setString(2, Id);
            row = ptm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }

    public Integer updateSoLuongChiTietHoaDonbyId(Integer sl, Double donGia, String idCTHD) {
        Integer row = null;
        String sql = "UPDATE HOADONCHITIET SET SoLuong =?, DonGia =? WHERE Id =?";
        try {
            Connection con = DBConnext.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, sl);
            ps.setDouble(2, donGia);
            ps.setString(3, idCTHD);
            row = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }

    public void updateTrangThaiHoaDon(String idHD) {
        String sql = "UPDATE HOADON SET TrangThai =1 WHERE Id = ?";
        try {
            Connection con = DBConnext.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, idHD);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void updateNguoiDung(String idHD, Integer idND) {
        String sql = "UPDATE HOADON SET IdND = ? WHERE Id = ?";
        try {
            Connection con = DBConnext.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idND); // Đặt tham số IdND
            ps.setString(2, idHD); // Đặt tham số Id
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Integer getIdNguoiDungByTen(String tenND) {
        Integer idNguoiDung = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            // Kết nối tới cơ sở dữ liệu
            connection = DBConnext.getConnection();

            // Chuẩn bị truy vấn SQL
            String sql = "SELECT id FROM NguoiDung WHERE ten = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, tenND);

            // Thực hiện truy vấn
            resultSet = statement.executeQuery();

            // Kiểm tra kết quả
            if (resultSet.next()) {
                idNguoiDung = resultSet.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Xử lý ngoại lệ
        } finally {
            // Đóng kết nối và tài nguyên
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return idNguoiDung;
    }

    public void ngayThanhToanHoaDon(String idHD) {
        String sql = "UPDATE HOADON SET NgayThanhToan = ? WHERE Id = ?";
        try {
            Connection con = DBConnext.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setDate(1, java.sql.Date.valueOf(LocalDate.now()));
            ps.setString(2, idHD);
            ps.executeUpdate();
            ps.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateTienHoaDon(String idHD, Double tongTien, Double thanhTien) {
        String sql = "UPDATE [HOADON] SET [TongTien] = ?, [ThanhTien] = ? WHERE [Id] = ?";
        try {
            Connection connection = DBConnext.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setDouble(1, tongTien);
            ps.setDouble(2, thanhTien);
            ps.setString(3, idHD);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateTienHoaDonChiTiet(String idHD, Double tongTien, Double thanhTien) {
        String sql = "UPDATE [dbo].[HOADONCHITIET]"
                + "   SET [IdHD] = ?"
                + "      ,[IdCTD] = ?"
                + "      ,[DonGia] = ?"
                + "      ,[SoLuong] = ?"
                + "      ,[NgayTao] = ?"
                + "      ,[NgaySua] = ?"
                + "      ,[TrangThai] = ?"
                + " WHERE "
                ;
        try {
            Connection connection = DBConnext.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setDouble(1, tongTien);
            ps.setDouble(2, thanhTien);
            ps.setString(3, idHD);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Integer getSoLuongByIdCTSP(String idCTSP) {
        Integer soLuong = null;
        String sql = "SELECT SoLuong FROM CHITIETGIAY WHERE Id = ?";
        try {
            Connection con = DBConnext.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, idCTSP);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                soLuong = rs.getInt("SoLuong");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return soLuong;
    }


    public void xoaHoaDonCT(String id) {
        String sql = "DELETE FROM HOADONCHITIET WHERE Id = ?";
        try {
            Connection con = DBConnext.getConnection();
            PreparedStatement ptm = con.prepareStatement(sql);
            ptm.setString(1, id);
            ptm.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    public String getIdHDCT(String idHD, String idCTG) {
        String idHDCT = null;
        try {
            Connection con = DBConnext.getConnection();
            String sql = "SELECT Id FROM HOADONCHITIET WHERE IdCTD = ? AND IdHD = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, idCTG);
            ps.setString(2, idHD);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                idHDCT = rs.getString("Id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return idHDCT;
    }

    public static void main(String[] args) {
        HoaDonChoRepo hd = new HoaDonChoRepo();
        System.out.println(hd.getSoLuongByIdCTSP("1"));
    }

}
