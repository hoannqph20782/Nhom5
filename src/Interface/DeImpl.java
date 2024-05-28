/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interface;

import Model.De;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public interface DeImpl {

    ArrayList<De> getAllDe();

    public boolean insert(De cl);

    public boolean update(De cl);

    public De getIDByName(String de);

    public void updateTrangThai(String idDe);
}
