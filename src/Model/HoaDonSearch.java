/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class HoaDonSearch {
      private Integer id;

    private String maHoaDon;

    private String maNV;
    
    private String maKH;
    
    private String sdtKH;

    private Date ngayTao;

    private Float tongTien;

    private Integer trangThai;

    private String ghiChu;

    public HoaDonSearch() {
    }

    public HoaDonSearch(Integer id, String maHoaDon, String maNV, String sdtKH, Date ngayTao, Float tongTien, Integer trangThai, String ghiChu) {
        this.id = id;
        this.maHoaDon = maHoaDon;
        this.maNV = maNV;
        this.sdtKH = sdtKH;
        this.ngayTao = ngayTao;
        this.tongTien = tongTien;
        this.trangThai = trangThai;
        this.ghiChu = ghiChu;
    }

    public HoaDonSearch(Integer id, String maHoaDon, String maNV, String maKH, String sdtKH, Date ngayTao, Float tongTien, Integer trangThai, String ghiChu) {
        this.id = id;
        this.maHoaDon = maHoaDon;
        this.maNV = maNV;
        this.maKH = maKH;
        this.sdtKH = sdtKH;
        this.ngayTao = ngayTao;
        this.tongTien = tongTien;
        this.trangThai = trangThai;
        this.ghiChu = ghiChu;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getSdtKH() {
        return sdtKH;
    }

    public void setSdtKH(String sdtKH) {
        this.sdtKH = sdtKH;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }


    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public Float getTongTien() {
        return tongTien;
    }

    public void setTongTien(Float tongTien) {
        this.tongTien = tongTien;
    }

    public Integer getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Integer trangThai) {
        this.trangThai = trangThai;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public HoaDonSearch(String maHoaDon, String maNV, String sdtKH, Date ngayTao, Float tongTien, Integer trangThai, String ghiChu) {
        this.maHoaDon = maHoaDon;
        this.maNV = maNV;
        this.sdtKH = sdtKH;
        this.ngayTao = ngayTao;
        this.tongTien = tongTien;
        this.trangThai = trangThai;
        this.ghiChu = ghiChu;
    }

    @Override
    public String toString() {
        return "HoaDon{" + "id=" + id + ", maHoaDon=" + maHoaDon + ", maNV=" + maNV + ", sdtKH=" + sdtKH + ", ngayTao=" + ngayTao + ", tongTien=" + tongTien + ", trangThai=" + trangThai + ", ghiChu=" + ghiChu + '}';
    }

}
