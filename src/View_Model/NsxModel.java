/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View_Model;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADMIN
 */
public class NsxModel extends DefaultTableModel {

    public NsxModel() {
        super();
        this.addColumn("Id NSX");
        this.addColumn("Mã NSX");
        this.addColumn("Tên NSX");
        this.addColumn("Trạng Thái");
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }

    @Override
    public Class getColumnClass(int column) {
        switch (column) {
            case 0:
                return String.class;
            case 1:
                return String.class;
            case 2:
                return String.class;
            case 3:
                return String.class;
            default:
                return String.class;
        }
    }

}
