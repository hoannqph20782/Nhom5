/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Repo.KMRepository;
import java.util.ArrayList;
import Interface.KMServiceImpl;
import Model.KM;

/**
 *
 * @author acer
 */
public class KMService implements KMServiceImpl {

    KMRepository km = new KMRepository();

    @Override
    public ArrayList<KM> getAll() {
        return km.getAllKM();
    }

    @Override
    public boolean add(KM km) {
        return this.km.add(km);
    }

    @Override
    public boolean update(String ma, KM km) {
        return this.km.update(ma, km);
    }

    @Override
    public ArrayList<KM> searchDate(String ngayBatDau, String ngayKetThuc) {
        return this.km.searchDate(ngayBatDau, ngayKetThuc);
    }

    @Override
    public ArrayList<KM> searchTinhTrang(int tinhTrang) {
        return this.km.searchTinhTrang(tinhTrang);
    }

    @Override
    public ArrayList<KM> search(String maKM) {
        return this.km.search(maKM);
    }

    @Override
    public ArrayList<KM> searchHinhThuc(int hinhThuc) {
        return this.km.searchHinhThuc(hinhThuc);
    }

}
