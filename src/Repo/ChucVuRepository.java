/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repo;


import Model.ChucVu;
import Utilities.DBConnext;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author ADMIN
 */
public class ChucVuRepository {
    public ChucVu getDanhMucByID(String id) {
   String sql = "SELECT * FROM CHUCVU WHERE Id=?";
         try ( Connection con = DBConnext.getConnection();  PreparedStatement ps = con.prepareStatement(sql)){
              ps.setObject(1, id);
               ResultSet rs = ps.executeQuery();
            while (rs.next()) {
               return new ChucVu(rs.getInt(1), rs.getString(2), rs.getString(3));
            }
        } catch (SQLException ex) {

             ex.printStackTrace(System.out);
        }
        return null;
    }
}
