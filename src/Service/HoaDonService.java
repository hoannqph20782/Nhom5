/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Interface.HoaDonImpl;
import Model.HoaDon;
import Model.HoaDonChiTiet;
import Model.HoaDonSearch;
import Repo.HoaDonRepo;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public class HoaDonService implements HoaDonImpl {

    HoaDonRepo hoaDonRepo = new HoaDonRepo();

    @Override
    public ArrayList<HoaDon> getAllSanPham() {
        return hoaDonRepo.getAllHoaDon();
    }

    @Override
    public ArrayList<HoaDonChiTiet> getAllHoaDonChiTiet() {
        return hoaDonRepo.getAllHoaDonChiTiet();
    }

    @Override
    public ArrayList<HoaDonSearch> search(String maKH) {
      return hoaDonRepo.search(maKH);
    }

    @Override
    public ArrayList<HoaDonSearch> searchDate(String ngayBatDau, String ngayKetThuc) {
      return hoaDonRepo.searchDate(ngayBatDau, ngayKetThuc);
    }
}
