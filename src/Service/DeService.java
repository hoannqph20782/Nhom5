/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Interface.DeImpl;
import Model.De;
import Repo.DeRepository;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public class DeService implements DeImpl {

    DeRepository deRepository = new DeRepository();

    @Override
    public ArrayList<De> getAllDe() {
        return deRepository.getAllde();
    }

    @Override
    public boolean insert(De cl) {
        return deRepository.insertDe(cl);
    }

    @Override
    public boolean update(De cl) {
        return deRepository.updateDe(cl);
    }

    @Override
    public De getIDByName(String de) {
        return deRepository.getDeByID(de);
    }

    @Override
    public void updateTrangThai(String idDe) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
