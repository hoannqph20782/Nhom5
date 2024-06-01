/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interface;

import Model.ChiTietGiay;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public interface SanPhamChiTietImpl {

    ArrayList<ChiTietGiay> getAllChiTietGiay();

    ArrayList<ChiTietGiay> getChiTietSanPham();
    public List<String> getChatLieu();

    public List<String> getDanhMuc();

    public List<String> getSanPham();

    public List<String> getMauSac();

    public List<String> getSize();

    public List<String> getNSX();

    public List<String> getDe();
}
