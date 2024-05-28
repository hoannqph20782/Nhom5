/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interface;

import Model.DanhMuc;
import Repo.DanhMucRepository;
import java.util.ArrayList;

/**
 *
 * @author ADMIN
 */
public interface DanhMucImpl {
    public ArrayList<DanhMuc>getAllDanhMuc();
    public boolean insert(DanhMuc dm);
    
    public boolean update(DanhMuc dm);
    
    public DanhMuc getIDByName(String danhMuc);
    
    public void updateTrangThai(String idDanhMuc);
}
