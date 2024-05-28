/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interface;

import Model.NSX;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public interface NSXImpl {

    ArrayList<NSX> getAllNsx();

    public boolean insert(NSX nsx);

    public boolean update(NSX nsx);

    public NSX getIDByName(String nsx);

    public void updateTrangThai(String idNSX);
}
