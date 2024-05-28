/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repo;

import Interface.NhanVienServiceImpl;
import Model.NguoiDung;
import Utilities.DBConnext;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Windows
 */
public class NhanVienRepository implements NhanVienServiceImpl{
    @Override
    public ArrayList<NguoiDung> getAll(){
    ArrayList<NguoiDung> listnd = new ArrayList<>();
    
    String sql = "Select Ma, Ten, NgaySinh, Email, Sdt, GioiTinh, DiaChi, MatKhau, TrangThai from NguoiDung";
        try {
            Connection con = DBConnext.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
              listnd.add(new NguoiDung(rs.getString(1),
                      rs.getString(2),
                      rs.getDate(3),
                      rs.getString(4),
                      rs.getString(5),
                      rs.getInt(6),
                      rs.getString(7),
                      rs.getString(8),
                      rs.getInt(9)));
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
       return listnd;
    
   }
    @Override
    public boolean add(NguoiDung nd) {
        String sql = "INSERT INTO NGUOIDUNG (Ma, Ten, NgaySinh, Email, Sdt, GioiTinh, DiaChi, MatKhau, TrangThai)\n"
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?,?)";
        try {
            Connection con = DBConnext.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setObject(1, nd.getMaNguoiDung());
            ps.setObject(2, nd.getTenNguoiDung());
            ps.setObject(3, nd.getNgaySinhNguoiDung());
            ps.setObject(4, nd.getEmailNguoiDung());
            ps.setObject(5, nd.getSdtNguoiDung());
            ps.setObject(6, nd.getGioiTinhNguoiDung());
            ps.setObject(7, nd.getDiaChiNguoiDung());
            ps.setObject(8, nd.getMatKhauNguoiDung());
            ps.setObject(9, nd.getTrangThaiNguoiDung());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("Lỗi ADD NhanVien!");
        }
        return false;
    }
    @Override
     public boolean update(String ma, NguoiDung nd) {
        String sql = "Update NGUOIDUNG Set Ten= ?, NgaySinh = ?, Email = ?, Sdt = ?, GioiTinh = ?, "
                + "DiaChi = ?, MatKhau = ?, TrangThai = ? where Ma = ?";
        try {
            Connection con = DBConnext.getConnection();
            PreparedStatement ps = con.prepareStatement(sql); 
            ps.setObject(1, nd.getTenNguoiDung());
            ps.setObject(2, nd.getNgaySinhNguoiDung());
            ps.setObject(3, nd.getEmailNguoiDung());
            ps.setObject(4, nd.getSdtNguoiDung());
            ps.setObject(5, nd.getGioiTinhNguoiDung());
            ps.setObject(6, nd.getDiaChiNguoiDung());
            ps.setObject(7, nd.getMatKhauNguoiDung());
            ps.setObject(8, nd.getTrangThaiNguoiDung());
            ps.setObject(9, ma);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("Lỗi UPDATE NHAN VIEN!");
        }

        return false;
    }
     @Override
    public ArrayList<NguoiDung> phanTrang(Integer phantu) {
        ArrayList<NguoiDung> listPhanTrang = new ArrayList<>();

        try {
            Connection con = DBConnext.getConnection();
            String where_condition = "select Ma, Ten, NgaySinh, Email, Sdt, GioiTinh, DiaChi, MatKhau, TrangThai ,  count(0) over() as total_row \n"
                    + "from NGUOIDUNG \n"
                    + "\n"
                    + "order by Ma\n"
                    + "offset " + phantu + " rows fetch next 5 rows only";
            String query = where_condition;
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String maNguoiDung = rs.getString("Ma");

                String tenNguoiDung = rs.getString("Ten");

                Date ngaySinhNguoiDung = rs.getDate("NgaySinh");

                String emailNguoiDung = rs.getString("Email");

                String sdtNguoiDung = rs.getString("Sdt");

                Integer gioiTinhNguoiDung = rs.getInt("GioiTinh");

                String diaChiNguoiDung = rs.getString("DiaChi");

                String matKhauNguoiDung = rs.getString("MatKhau");

                Integer trangThaiNguoiDung = rs.getInt("TrangThai");

                NguoiDung nd = new NguoiDung();
                nd.setMaNguoiDung(maNguoiDung);
                nd.setTenNguoiDung(tenNguoiDung);
                nd.setNgaySinhNguoiDung(ngaySinhNguoiDung);
                nd.setEmailNguoiDung(emailNguoiDung);
                nd.setSdtNguoiDung(sdtNguoiDung);
                nd.setGioiTinhNguoiDung(gioiTinhNguoiDung);
                nd.setDiaChiNguoiDung(diaChiNguoiDung);
                nd.setMatKhauNguoiDung(matKhauNguoiDung);
                nd.setTrangThaiNguoiDung(trangThaiNguoiDung);

                listPhanTrang.add(nd);

            }
        } catch (Exception e) {
            System.out.println("Lỗi Phan Trang NhanVienRepo");
        }
        return listPhanTrang;
    }
    @Override
    public ArrayList<NguoiDung> timKiemPhanTrang(String ma, int phantu, int tt) {
        ArrayList<NguoiDung> customers = new ArrayList<>();

        try {
            Connection con = DBConnext.getConnection();
            String tthai = "trangthai";
            if (tt == -1) {
                tthai = tthai;
            } else {
                tthai = String.valueOf(tt);
            }

            String where_condition = "where (Ma like '%" + ma + "%' or Ten like '%" + ma + "%' or DiaChi like '%" + ma + "%' )" + "and TrangThai like   " + tthai + " ";
            String phantrang = "order by Ma\n"
                    + "offset " + phantu + " rows fetch next 5 rows only";
            String query = """
                           select Ma, Ten, NgaySinh, Email, Sdt, GioiTinh, DiaChi, MatKhau, TrangThai from NGUOIDUNG
                           """ + where_condition
                    + phantrang;

            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                String maNguoiDung = rs.getString("Ma");

                String tenNguoiDung = rs.getString("Ten");

                Date ngaySinhNguoiDung = rs.getDate("NgaySinh");

                String emailNguoiDung = rs.getString("Email");

                String sdtNguoiDung = rs.getString("Sdt");

                Integer gioiTinhNguoiDung = rs.getInt("GioiTinh");

                String diaChiNguoiDung = rs.getString("DiaChi");

                String matKhauNguoiDung = rs.getString("MatKhau");

                Integer trangThaiNguoiDung = rs.getInt("TrangThai");

                NguoiDung nd = new NguoiDung();
                nd.setMaNguoiDung(maNguoiDung);
                nd.setTenNguoiDung(tenNguoiDung);
                nd.setNgaySinhNguoiDung(ngaySinhNguoiDung);
                nd.setEmailNguoiDung(emailNguoiDung);
                nd.setSdtNguoiDung(sdtNguoiDung);
                nd.setGioiTinhNguoiDung(gioiTinhNguoiDung);
                nd.setDiaChiNguoiDung(diaChiNguoiDung);
                nd.setMatKhauNguoiDung(matKhauNguoiDung);
                nd.setTrangThaiNguoiDung(trangThaiNguoiDung);

                customers.add(nd);
            }
        } catch (Exception e) {
        }
        return customers;
    }
    public static void main(String[] args) {
        NhanVienRepository nv = new NhanVienRepository();
        System.out.println(nv.getAll());
    }

    
    
}
