/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interface;

import Model.KhuyenMai;
import java.util.ArrayList;

/**
 *
 * @author acer
 */
public interface KhuyenMaiServiceImpl {

    public ArrayList<KhuyenMai> getAll();

    public boolean add(KhuyenMai km);

    public boolean update(String ma, KhuyenMai km);

    public ArrayList<KhuyenMai> timKiem(String ma, String ten, float mucGiam);

}
