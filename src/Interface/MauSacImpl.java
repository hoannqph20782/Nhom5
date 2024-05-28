/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interface;

import Model.MauSac;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public interface MauSacImpl {

    public ArrayList<MauSac> getAllMauSac();

    public boolean insert(MauSac ms);

    public boolean update(MauSac ms);

    public MauSac getIDByName(String mauSac);

    public void updateTrangThai(String idMauSac);
}
