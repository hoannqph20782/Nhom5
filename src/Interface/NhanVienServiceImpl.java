/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interface;

import Model.NguoiDung;
import java.util.ArrayList;

/**
 *
 * @author Windows
 */
public interface NhanVienServiceImpl {
    public ArrayList<NguoiDung> getAll();

    public boolean add(NguoiDung nd);

    public boolean update(String ma, NguoiDung nd);

    public ArrayList<NguoiDung> phanTrang(Integer phantu);

    public ArrayList<NguoiDung> timKiemPhanTrang(String ma, int phantu, int tt);
}
