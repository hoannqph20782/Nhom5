/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Interface.NSXImpl;
import Model.NSX;
import Repo.NSXRepository;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public class NSXService implements NSXImpl {

    NSXRepository nSXRepository = new NSXRepository();

    @Override
    public ArrayList<NSX> getAllNsx() {
        return nSXRepository.getAllNsxs();
    }

    @Override
    public boolean insert(NSX nsx) {
        return nSXRepository.addNhaSanXuat(nsx);
    }

    @Override
    public boolean update(NSX nsx) {
        return nSXRepository.updateNsx(nsx);
    }

    @Override
    public NSX getIDByName(String nsx) {
        return nSXRepository.getNSXByID(nsx);
    }

    @Override
    public void updateTrangThai(String idNSX) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
