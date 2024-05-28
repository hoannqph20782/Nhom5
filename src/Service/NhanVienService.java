/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Interface.NhanVienServiceImpl;
import Model.NguoiDung;
import Repo.NhanVienRepository;
import java.util.ArrayList;

/**
 *
 * @author Windows
 */
public class NhanVienService implements NhanVienServiceImpl{
    NhanVienRepository nhanVien= new NhanVienRepository();
    @Override
    public ArrayList<NguoiDung> getAll() {
        return nhanVien.getAll();
    }

    @Override
    public boolean add(NguoiDung nd) {
        return nhanVien.add(nd);
    }

    @Override
    public boolean update(String ma, NguoiDung nd) {
        return nhanVien.update(ma, nd);
    }

    @Override
    public ArrayList<NguoiDung> phanTrang(Integer phantu) {
        return nhanVien.phanTrang(phantu);
    }

    @Override
    public ArrayList<NguoiDung> timKiemPhanTrang(String ma, int phantu, int tt) {
        return nhanVien.timKiemPhanTrang(ma, phantu, tt);
    }
}
