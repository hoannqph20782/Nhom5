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
public class GioHang {

    private String idCTSP;
    private String tenSanPham;
    private int soLuong;
    private String chatLieu;
    private String mauSac;
    private String hang;
    private String size;
    private Double donGia;
    private int trangThai;

    public GioHang() {
    }

    public GioHang(String idCTSP, String tenSanPham, int soLuong, String chatLieu, String mauSac, String hang, String size, Double donGia, int trangThai) {
        this.idCTSP = idCTSP;
        this.tenSanPham = tenSanPham;
        this.soLuong = soLuong;
        this.chatLieu = chatLieu;
        this.mauSac = mauSac;
        this.hang = hang;
        this.size = size;
        this.donGia = donGia;
        this.trangThai = trangThai;
    }

    public String getIdCTSP() {
        return idCTSP;
    }

    public void setIdCTSP(String idCTSP) {
        this.idCTSP = idCTSP;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getChatLieu() {
        return chatLieu;
    }

    public void setChatLieu(String chatLieu) {
        this.chatLieu = chatLieu;
    }

    public String getMauSac() {
        return mauSac;
    }

    public void setMauSac(String mauSac) {
        this.mauSac = mauSac;
    }

    public String getHang() {
        return hang;
    }

    public void setHang(String hang) {
        this.hang = hang;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Double getDonGia() {
        return donGia;
    }

    public void setDonGia(Double donGia) {
        this.donGia = donGia;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }
    

}
