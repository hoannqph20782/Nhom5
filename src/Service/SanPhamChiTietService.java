/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Interface.SanPhamChiTietImpl;
import Model.ChiTietGiay;
import Repo.SanPhamChiTietRepository;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<String> getDanhMuc() {
        return sanPhamChiTietRepository.getDanhMuc();
    }

    @Override
    public List<String> getSanPham() {
         return sanPhamChiTietRepository.getSanPham();
    }

    @Override
    public List<String> getMauSac() {
         return sanPhamChiTietRepository.getMauSac();
    }

    @Override
    public List<String> getSize() {
         return sanPhamChiTietRepository.getSize();
    }

    @Override
    public List<String> getNSX() {
         return sanPhamChiTietRepository.getNSX();
    }

    @Override
    public List<String> getDe() {
         return sanPhamChiTietRepository.getDe();
    }

    @Override
    public List<String> getChatLieu() {
        return sanPhamChiTietRepository.getChatLieu();
    }
    
}
