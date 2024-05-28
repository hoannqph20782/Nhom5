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
public class KhuyenMai {
    
    
    private Integer id;

    private String ma;

    private String ten;

    private Double phanTramGiam;

    private Double giaGiam;

    private Integer soLuong;

    private Date ngayBatDau;

    private Date ngayKetThuc;

    private Integer hinhThucGiam;

    private Integer trangThai;

    private String moTa;

    public KhuyenMai() {
    }

    public KhuyenMai(Integer id, String ma, String ten, Double phanTramGiam, Double giaGiam, Integer soLuong, Date ngayBatDau, Date ngayKetThuc, Integer hinhThucGiam, Integer trangThai, String moTa) {
        this.id = id;
        this.ma = ma;
        this.ten = ten;
        this.phanTramGiam = phanTramGiam;
        this.giaGiam = giaGiam;
        this.soLuong = soLuong;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.hinhThucGiam = hinhThucGiam;
        this.trangThai = trangThai;
        this.moTa = moTa;
    }

    public KhuyenMai(String ma, String ten, Double phanTramGiam, Double giaGiam, Integer soLuong, Date ngayBatDau, Date ngayKetThuc, Integer hinhThucGiam, Integer trangThai, String moTa) {
        this.ma = ma;
        this.ten = ten;
        this.phanTramGiam = phanTramGiam;
        this.giaGiam = giaGiam;
        this.soLuong = soLuong;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.hinhThucGiam = hinhThucGiam;
        this.trangThai = trangThai;
        this.moTa = moTa;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public Double getPhanTramGiam() {
        return phanTramGiam;
    }

    public void setPhanTramGiam(Double phanTramGiam) {
        this.phanTramGiam = phanTramGiam;
    }

    public Double getGiaGiam() {
        return giaGiam;
    }

    public void setGiaGiam(Double giaGiam) {
        this.giaGiam = giaGiam;
    }

    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }

    public Date getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(Date ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public Date getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(Date ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public Integer getHinhThucGiam() {
        return hinhThucGiam;
    }

    public void setHinhThucGiam(Integer hinhThucGiam) {
        this.hinhThucGiam = hinhThucGiam;
    }

    public Integer getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Integer trangThai) {
        this.trangThai = trangThai;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    @Override
    public String toString() {
        return "KhuyenMai{" + "id=" + id + ", ma=" + ma + ", ten=" + ten + ", phanTramGiam=" + phanTramGiam + ", giaGiam=" + giaGiam + ", soLuong=" + soLuong + ", ngayBatDau=" + ngayBatDau + ", ngayKetThuc=" + ngayKetThuc + ", hinhThucGiam=" + hinhThucGiam + ", trangThai=" + trangThai + ", moTa=" + moTa + '}';
    }

    
   
    
}
