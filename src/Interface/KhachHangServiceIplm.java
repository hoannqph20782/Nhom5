/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interface;

import Model.KhachHang;

import java.util.ArrayList;

/**
 *
 * @author UyTin
 */
public interface KhachHangServiceIplm {
    
       public ArrayList<KhachHang> getList();

    public String them(KhachHang kh);

    public String update(String Ma, KhachHang kh);
    
    public ArrayList<KhachHang> TimKiem( String Ten, String sdt);
}
