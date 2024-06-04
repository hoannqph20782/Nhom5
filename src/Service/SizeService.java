/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Interface.SizeImpl;
import Model.SIZE;
import Model.Size2;
import Repo.SizeRepository;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public class SizeService implements SizeImpl{
     SizeRepository sizeRepository = new SizeRepository();

    @Override
    public ArrayList<SIZE> getAllSize() {
    return sizeRepository.getAllSize();
            }

    @Override
    public boolean insert(SIZE sz) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean update(SIZE sz) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public SIZE getIDByName(String size) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void updateTrangThai(String idSize) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean insertSizeCRUD(Size2 size2) {
        return sizeRepository.insertSizeCRUD(size2);
        }

    @Override
    public boolean updateSizeCRUD(Size2 size2) {
      return sizeRepository.updateSizeCRUD(size2);
    }
}
