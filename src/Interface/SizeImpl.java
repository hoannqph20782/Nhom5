/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interface;

import Model.SIZE;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public interface SizeImpl {
    public ArrayList<SIZE>getAllSize();
    public boolean insert(SIZE sz);
    
    public boolean update(SIZE sz);
    
    public SIZE getIDByName(String size);
    
    public void updateTrangThai(String idSize);
}
