/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Interface.SanPhamChiTietImpl;
import Model.ChiTietGiay;
import Repo.SanPhamChiTietRepository;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public class SanPhamChiTietService implements SanPhamChiTietImpl{
    SanPhamChiTietRepository sanPhamChiTietRepository = new SanPhamChiTietRepository();

    @Override
    public ArrayList<ChiTietGiay> getAllChiTietGiay() {
  return sanPhamChiTietRepository.getAllChiTietGiay();
    }

    @Override
    public ArrayList<ChiTietGiay> getChiTietSanPham() {
      return sanPhamChiTietRepository.getAllChiTietSanPham();
    }
}
