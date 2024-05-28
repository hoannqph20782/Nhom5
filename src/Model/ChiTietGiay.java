/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.math.BigDecimal;

/**
 *
 * @author Windows
 */
public class ChiTietGiay {
    private Integer id;
    private DanhMuc idDanhMuc;
    private SanPham idSanPham;
    private SIZE idSize;
    private MauSac idMauSac;
    private ChatLieu idChatLieu;
    private NSX idNSX;
    private De idDe;
    private int soLuong;
    private BigDecimal gianhap;
    private BigDecimal giaBan;
    private String mota;
    private String hinhAnh;
    private int trangThai;

    public ChiTietGiay() {
    }

    public ChiTietGiay(Integer id, DanhMuc idDanhMuc, SanPham idSanPham, SIZE idSize, MauSac idMauSac, ChatLieu idChatLieu, NSX idNSX, De idDe, int soLuong, BigDecimal gianhap, BigDecimal giaBan, String mota, String hinhAnh, int trangThai) {
        this.id = id;
        this.idDanhMuc = idDanhMuc;
        this.idSanPham = idSanPham;
        this.idSize = idSize;
        this.idMauSac = idMauSac;
        this.idChatLieu = idChatLieu;
        this.idNSX = idNSX;
        this.idDe = idDe;
        this.soLuong = soLuong;
        this.gianhap = gianhap;
        this.giaBan = giaBan;
        this.mota = mota;
        this.hinhAnh = hinhAnh;
        this.trangThai = trangThai;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public DanhMuc getIdDanhMuc() {
        return idDanhMuc;
    }

    public void setIdDanhMuc(DanhMuc idDanhMuc) {
        this.idDanhMuc = idDanhMuc;
    }

    public SanPham getIdSanPham() {
        return idSanPham;
    }

    public void setIdSanPham(SanPham idSanPham) {
        this.idSanPham = idSanPham;
    }

    public SIZE getIdSize() {
        return idSize;
    }

    public void setIdSize(SIZE idSize) {
        this.idSize = idSize;
    }

    public MauSac getIdMauSac() {
        return idMauSac;
    }

    public void setIdMauSac(MauSac idMauSac) {
        this.idMauSac = idMauSac;
    }

    public ChatLieu getIdChatLieu() {
        return idChatLieu;
    }

    public void setIdChatLieu(ChatLieu idChatLieu) {
        this.idChatLieu = idChatLieu;
    }

    public NSX getIdNSX() {
        return idNSX;
    }

    public void setIdNSX(NSX idNSX) {
        this.idNSX = idNSX;
    }

    public De getIdDe() {
        return idDe;
    }

    public void setIdDe(De idDe) {
        this.idDe = idDe;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public BigDecimal getGianhap() {
        return gianhap;
    }

    public void setGianhap(BigDecimal gianhap) {
        this.gianhap = gianhap;
    }

    public BigDecimal getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(BigDecimal giaBan) {
        this.giaBan = giaBan;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

   
    
}
