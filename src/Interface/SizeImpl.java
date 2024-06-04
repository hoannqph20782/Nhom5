/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interface;

import Model.SIZE;
import Model.Size2;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public interface SizeImpl {

    public ArrayList<SIZE> getAllSize();

    public boolean insert(SIZE sz);

    public boolean update(SIZE sz);

    public SIZE getIDByName(String size);

    public void updateTrangThai(String idSize);

    public boolean insertSizeCRUD(Size2 size2);

    public boolean updateSizeCRUD(Size2 size2);

}
