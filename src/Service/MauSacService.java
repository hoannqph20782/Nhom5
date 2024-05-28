/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Interface.MauSacImpl;
import Model.MauSac;
import Repo.MauSacRepository;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public class MauSacService implements MauSacImpl {
    
    MauSacRepository mauSacRepositorys = new MauSacRepository();
    
    @Override
    public ArrayList<MauSac> getAllMauSac() {
        return mauSacRepositorys.getAllMauSac();
    }
    
    @Override
    public boolean insert(MauSac ms) {
        return mauSacRepositorys.insertMauSac(ms);
    }
    
    @Override
    public boolean update(MauSac ms) {
        return mauSacRepositorys.updateMauSac(ms);
    }
    
    @Override
    public MauSac getIDByName(String mauSac) {
        return mauSacRepositorys.getMauSacID(mauSac);
    }
    
    @Override
    public void updateTrangThai(String idMauSac) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
