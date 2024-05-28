/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Interface.SanPhamImpl;
import Model.SanPham;
import Repo.SanPhamRepository;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public class SanPhamService implements SanPhamImpl {

    SanPhamRepository sanPhamRepository = new SanPhamRepository();
    
    @Override
    public ArrayList<SanPham> getAllSanPhamService() {
        return sanPhamRepository.getAllSanPham();
    }
    
    @Override
    public Integer insert(SanPham sp) {
        return sanPhamRepository.insertSanPham(sp);
    }
    
    @Override
    public boolean update(SanPham sp) {
        return sanPhamRepository.updateSanPham(sp);
    }
    
    @Override
    public SanPham getSanPhamMa(String ma) {
        return sanPhamRepository.getSanPhamMa(ma);
    }
    
    @Override
    public SanPham getIDByName(String sanPham) {
        return sanPhamRepository.getSanPhamByID(sanPham);
    }
    
    @Override
    public void updateTrangThai(String idSanPham) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
