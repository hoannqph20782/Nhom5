/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interface;

import Model.HoaDon;
import Model.HoaDonChiTiet;
import Model.HoaDonSearch;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public interface HoaDonImpl {

    public ArrayList<HoaDon> getAllSanPham();
    public ArrayList<HoaDonChiTiet>getAllHoaDonChiTiet();
    public ArrayList<HoaDonSearch> search(String maKH );
     public ArrayList<HoaDonSearch> searchDate(String ngayBatDau, String ngayKetThuc);
}
