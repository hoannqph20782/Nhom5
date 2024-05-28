/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Interface.DanhMucImpl;
import Model.DanhMuc;
import Repo.DanhMucRepository;
import java.util.ArrayList;

public class DanhMucService implements DanhMucImpl{
    DanhMucRepository danhmucRepository = new DanhMucRepository();

    @Override
    public ArrayList<DanhMuc> getAllDanhMuc() {
        return danhmucRepository.getAllDanhMuc();
         }

    @Override
    public boolean insert(DanhMuc dm) {
        return danhmucRepository.insertDanhMuc(dm);
    }

    @Override
    public boolean update(DanhMuc dm) {
        return danhmucRepository.updateDanhMuc(dm);
    }

    @Override
    public DanhMuc getIDByName(String danhMuc) {
        return danhmucRepository.getDanhMucByID(danhMuc);
    }

    @Override
    public void updateTrangThai(String idDanhMuc) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
