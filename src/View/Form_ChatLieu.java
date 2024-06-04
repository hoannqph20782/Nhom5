/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import Model.ChatLieu;
import Model.ChatLieu;
import Service.ChatLieuService;
import Utilities.DBConnext;
import Utilities.DBConnext;
import View_Model.ChatLieuModel;
import View_Model.ChatLieuModel;
import java.sql.*;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.TableModel;

/**
 *
 * @author ADMIN
 */
public class Form_ChatLieu extends javax.swing.JFrame {

    ChatLieuService chatLieuService = new ChatLieuService();

    ChatLieuModel cl;
    long count, soTrang, trang = 1;

    public Form_ChatLieu() {
        initComponents();
        setLocationRelativeTo(null);
        titleTable();
        countDB();
        if (count % 5 == 0) {
            soTrang = count / 5;
        } else {
            soTrang = count / 5 + 1;
        }
        loadData(1);
        lbSoTrang.setText("1/" + soTrang);
        lbTrang.setText("1");
    }

    public void titleTable() {
        cl = new ChatLieuModel();
        tblDanhSach.setModel(cl);
        tblDanhSach.setShowHorizontalLines(true);
        tblDanhSach.setShowVerticalLines(true);
    }

    public void countDB() {
        try {
            String query = "Select count(*) from CHATLIEU";
            Connection cn = DBConnext.getConnection();
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                count = rs.getLong(1);
            }

            rs.close();
            st.close();
            cn.close();
        } catch (Exception ex) {
            Logger.getLogger(Form_ChatLieu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void loadData(long trang) {
        titleTable();
        cl.getDataVector().removeAllElements();
        try {
            String query = "Select top 5 * from CHATLIEU where Ten not in (Select top " + (trang * 5 - 5) + " Ten from CHATLIEU)";
            Connection cn = DBConnext.getConnection();
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Vector v = new Vector();
                Integer id = rs.getInt(1);
                String Ma = rs.getString(2);
                String Ten = rs.getString(3);
                Integer trangThai = rs.getInt(4);
                v.add(id);
                v.add(Ma);
                v.add(Ten);
                v.add(trangThai);
                cl.addRow(v);
            }
            rs.close();
            st.close();
            cn.close();
        } catch (Exception ex) {
            Logger.getLogger(Form_ChatLieu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel5 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtTenChatLieu = new javax.swing.JTextField();
        btnThem = new javax.swing.JButton();
        btnCapNhat = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblDanhSach = new javax.swing.JTable();
        btLonMax = new javax.swing.JButton();
        btNhoMax = new javax.swing.JButton();
        btNho = new javax.swing.JButton();
        btLon = new javax.swing.JButton();
        lbTrang = new javax.swing.JLabel();
        lbSoTrang = new javax.swing.JLabel();
        lbClose = new javax.swing.JLabel();
        btnReset = new javax.swing.JButton();
        txtMaChatLieu = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        lblId = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        rdoCon = new javax.swing.JRadioButton();
        rdoHet = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel5.setBackground(new java.awt.Color(204, 255, 255));

        jLabel17.setText("Mã Chất Liệu");

        jLabel18.setText("Tên Chất Liệu");

        btnThem.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnCapNhat.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnCapNhat.setText("Cập Nhật");
        btnCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatActionPerformed(evt);
            }
        });

        tblDanhSach.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id Chất Liệu", "Mã Chất Liệu", "Tên Chất liệu"
            }
        ));
        tblDanhSach.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDanhSachMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblDanhSach);

        btLonMax.setText(">>");
        btLonMax.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btLonMaxActionPerformed(evt);
            }
        });

        btNhoMax.setText("<<");
        btNhoMax.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btNhoMaxActionPerformed(evt);
            }
        });

        btNho.setText("<");
        btNho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btNhoActionPerformed(evt);
            }
        });

        btLon.setText(">");
        btLon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btLonActionPerformed(evt);
            }
        });

        lbTrang.setText("jLabel1");

        lbSoTrang.setText("jLabel2");

        lbClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icons8-back-40.png"))); // NOI18N
        lbClose.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbCloseMouseClicked(evt);
            }
        });

        btnReset.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnReset.setText("Reset");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        txtMaChatLieu.setText("_________");

        jLabel19.setText("Id Chất Liệu");

        lblId.setText("_____");

        jLabel20.setText("Trạng Thái");

        buttonGroup1.add(rdoCon);
        rdoCon.setText("Còn ");

        buttonGroup1.add(rdoHet);
        rdoHet.setText("Hết");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(lbClose)
                                .addGap(48, 48, 48)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel18)
                                    .addComponent(jLabel19)
                                    .addComponent(jLabel20))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(lblId)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel17)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtMaChatLieu)
                                        .addGap(11, 11, 11))
                                    .addComponent(txtTenChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(rdoCon)
                                        .addGap(18, 18, 18)
                                        .addComponent(rdoHet))))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(78, 78, 78)
                                .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(39, 39, 39)
                                .addComponent(btnCapNhat)
                                .addGap(41, 41, 41)
                                .addComponent(btnReset)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(92, 92, 92)
                .addComponent(btNhoMax)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btNho, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbTrang)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btLon, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btLonMax)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbSoTrang)
                .addContainerGap(69, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(lbClose, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMaChatLieu)
                            .addComponent(jLabel17)
                            .addComponent(jLabel19)
                            .addComponent(lblId))
                        .addGap(18, 18, 18)))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTenChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(rdoCon)
                    .addComponent(rdoHet))
                .addGap(17, 17, 17)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btNhoMax)
                    .addComponent(btNho)
                    .addComponent(lbTrang)
                    .addComponent(btLon)
                    .addComponent(btLonMax)
                    .addComponent(lbSoTrang))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btNhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNhoActionPerformed
        if (trang > 1) {
            trang--;
            loadData(trang);
            lbTrang.setText("" + trang);
            lbSoTrang.setText(trang + "/" + soTrang);
        }
    }//GEN-LAST:event_btNhoActionPerformed

    private void btLonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btLonActionPerformed
        if (trang < soTrang) {
            trang++;
            loadData(trang);
            lbTrang.setText("" + trang);
            lbSoTrang.setText(trang + "/" + soTrang);
        }
    }//GEN-LAST:event_btLonActionPerformed

    private void btNhoMaxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNhoMaxActionPerformed
        trang = 1;
        loadData(trang);
        lbTrang.setText("1");
        lbSoTrang.setText("1/" + soTrang);
    }//GEN-LAST:event_btNhoMaxActionPerformed

    private void btLonMaxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btLonMaxActionPerformed
        trang = soTrang;
        loadData(trang);
        lbTrang.setText("" + soTrang);
        lbSoTrang.setText(soTrang + "/" + soTrang);
    }//GEN-LAST:event_btLonMaxActionPerformed

    private void lbCloseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbCloseMouseClicked
        this.dispose();
    }//GEN-LAST:event_lbCloseMouseClicked

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        if (txtTenChatLieu.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Moi ban nhap day du thong tin");
            return;
        }
        try {
            ChatLieu chatLieu = new ChatLieu();
            chatLieu.setMa(String.valueOf(txtMaChatLieu.getText()));
            chatLieu.setTen(String.valueOf(txtTenChatLieu.getText()));
             if (rdoCon.isSelected()) {
                chatLieu.setTrangThai(1);
            }else{
                chatLieu.setTrangThai(0);
            }
            chatLieu.setId(Integer.parseInt(lblId.getText()));
            chatLieuService.insertCrud(chatLieu);
            loadData(trang);
            clearFrom();
            JOptionPane.showMessageDialog(this, "Add thanh cong.");
            View_SanPhamChiTiet ViewSPCT = new View_SanPhamChiTiet();
            ViewSPCT.fillCboChiTiet();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Add that bai.");
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatActionPerformed
        if (txtTenChatLieu.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Moi ban chon ban ghi.");
            return;
        }

        ChatLieu chatLieu = new ChatLieu();
        chatLieu.setMa(String.valueOf(txtMaChatLieu.getText()));
        chatLieu.setTen(String.valueOf(txtTenChatLieu.getText()));
          if (rdoCon.isSelected()) {
                chatLieu.setTrangThai(1);
            }else{
                chatLieu.setTrangThai(0);
            }
        chatLieu.setId(Integer.parseInt(lblId.getText()));
        JOptionPane.showMessageDialog(this, "Cap nhat thanh cong.");
        if (!chatLieuService.update(chatLieu)) {
            JOptionPane.showMessageDialog(this, "Cap nhat that bai.");
            return;
        }
        loadData(trang);
        clearFrom();


    }//GEN-LAST:event_btnCapNhatActionPerformed

    private void tblDanhSachMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDanhSachMouseClicked
        int index = tblDanhSach.getSelectedRow();
        if (index < 0) {
            return;
        }

        lblId.setText(tblDanhSach.getValueAt(index, 0).toString());
        txtMaChatLieu.setText(tblDanhSach.getValueAt(index, 1).toString());
        txtTenChatLieu.setText(tblDanhSach.getValueAt(index, 2).toString());
        if (tblDanhSach.getValueAt(index, 3).toString().equals("1")) {
            rdoCon.setSelected(true);
        } else {
            rdoHet.setSelected(true);
        }
        System.out.println(tblDanhSach.getValueAt(index, 3).toString());
    }//GEN-LAST:event_tblDanhSachMouseClicked

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        clearFrom();
    }//GEN-LAST:event_btnResetActionPerformed

    public void clearFrom() {
        lblId.setText("");
        txtMaChatLieu.setText("");
        txtTenChatLieu.setText("");
        rdoCon.setSelected(true);
    }

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(Form_ChatLieu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(Form_ChatLieu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(Form_ChatLieu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(Form_ChatLieu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new Form_ChatLieu().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btLon;
    private javax.swing.JButton btLonMax;
    private javax.swing.JButton btNho;
    private javax.swing.JButton btNhoMax;
    private javax.swing.JButton btnCapNhat;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnThem;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbClose;
    private javax.swing.JLabel lbSoTrang;
    private javax.swing.JLabel lbTrang;
    private javax.swing.JLabel lblId;
    private javax.swing.JRadioButton rdoCon;
    private javax.swing.JRadioButton rdoHet;
    private javax.swing.JTable tblDanhSach;
    private javax.swing.JLabel txtMaChatLieu;
    private javax.swing.JTextField txtTenChatLieu;
    // End of variables declaration//GEN-END:variables
}
