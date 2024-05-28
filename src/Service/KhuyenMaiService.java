/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Model.KhuyenMai;
import Interface.KhuyenMaiServiceImpl;
import Repo.KhuyenMaiRepository;
import java.util.ArrayList;

/**
 *
 * @author acer
 */
public class KhuyenMaiService implements KhuyenMaiServiceImpl{
    
    KhuyenMaiRepository km = new KhuyenMaiRepository();
    
    @Override
    public ArrayList<KhuyenMai> getAll() {
        return km.getAllKM();
    }

    @Override
    public boolean add(KhuyenMai km) {
       return this.km.add(km);
    }

    @Override
    public boolean update(String ma, KhuyenMai km) {
        return this.km.update(ma, km);
    }

    @Override
    public ArrayList<KhuyenMai> timKiem(String ma, String ten, float mucGiam) {
        return this.km.timKiem(ma, ten, mucGiam);
    }
    
}
