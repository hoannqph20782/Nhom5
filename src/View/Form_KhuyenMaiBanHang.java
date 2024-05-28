/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View;

import Model.KhuyenMai;
import Repo.HoaDonChoRepo;
import Service.KhuyenMaiService;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Form_KhuyenMaiBanHang extends javax.swing.JFrame {

    public static HoaDonChoRepo hoaDonChoRepository = new HoaDonChoRepo();
    public static KhuyenMai km;
    JTable tb_giohang2;
    JTable tb_hoadon2;
    KhuyenMai khm;
    Form_BanHang banHangfr;
    KhuyenMaiService kms = new KhuyenMaiService();
    ArrayList<KhuyenMai> listKM = new ArrayList<>();

    public Form_KhuyenMaiBanHang(Form_BanHang banHangfr, JTable tb_hoadon) {
        initComponents();

        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setSize(1005, 530);
        setLocationRelativeTo(null);
        listKM = kms.getAll();
        this.tb_hoadon2 = tb_hoadon;
        this.banHangfr = banHangfr;
        loadTable();
    }

    public void loadTable() {
        DefaultTableModel model = (DefaultTableModel) tblKhuyenMai.getModel();
        model.setRowCount(0);
        for (KhuyenMai km : listKM) {
            Object[] data = new Object[]{
                km.getMa(),
                km.getTen(),
                km.getPhanTramGiam(),
                km.getGiaGiam(),
                km.getSoLuong(),
                km.getNgayBatDau(),
                km.getNgayKetThuc(),
                km.getHinhThucGiam() == 1 ? "Giảm %" : "Giảm tiền",
                km.getTrangThai() == 1 ? "Đang hoạt động" : "Ngừng hoạt động",
                km.getMoTa()
            };
            model.addRow(data);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        pnlCapNhat = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btn_chonmua = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblKhuyenMai = new javax.swing.JTable();
        txtTimKiemKM = new javax.swing.JTextField();
        btnTimKiemKM = new javax.swing.JButton();

        pnlCapNhat.setBackground(new java.awt.Color(204, 255, 255));
        pnlCapNhat.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 102, 255));
        jLabel1.setText("Quản Lý Voucher");

        btn_chonmua.setText("Add Voucher");
        btn_chonmua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_chonmuaActionPerformed(evt);
            }
        });

        tblKhuyenMai.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã KM", "Tên KM", "Mức giảm (%)", "Giảm Tiền ", "Số lượng", "Ngày bắt đầu", "Ngày kết thúc", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblKhuyenMai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKhuyenMaiMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblKhuyenMai);

        txtTimKiemKM.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemKMKeyReleased(evt);
            }
        });

        btnTimKiemKM.setText("Tìm kiếm");
        btnTimKiemKM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemKMActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlCapNhatLayout = new javax.swing.GroupLayout(pnlCapNhat);
        pnlCapNhat.setLayout(pnlCapNhatLayout);
        pnlCapNhatLayout.setHorizontalGroup(
            pnlCapNhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCapNhatLayout.createSequentialGroup()
                .addGroup(pnlCapNhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlCapNhatLayout.createSequentialGroup()
                        .addGap(380, 380, 380)
                        .addComponent(jLabel1))
                    .addGroup(pnlCapNhatLayout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(btn_chonmua, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(161, 161, 161)
                        .addComponent(txtTimKiemKM, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnTimKiemKM, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1001, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlCapNhatLayout.setVerticalGroup(
            pnlCapNhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCapNhatLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel1)
                .addGap(21, 21, 21)
                .addGroup(pnlCapNhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btn_chonmua)
                    .addGroup(pnlCapNhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtTimKiemKM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnTimKiemKM)))
                .addGap(56, 56, 56)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlCapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pnlCapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btn_chonmuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_chonmuaActionPerformed
        int row = tblKhuyenMai.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn 1 voucher !");

        } else {
            String maKM = tblKhuyenMai.getValueAt(row, 0).toString();
            String idKM = hoaDonChoRepository.getIdKMbyMa(maKM);
            int rowHD = tb_hoadon2.getSelectedRow();
            if (rowHD < 0) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn 1 hóa đơn để thêm voucher !");

            } else {
                String idHD = hoaDonChoRepository.getIdHoaDonByMa(tb_hoadon2.getValueAt(rowHD, 0).toString());
                System.out.println("id hd" + idHD);
                int cf = JOptionPane.showConfirmDialog(this, "Bạn có muốn thêm voucher '" + tblKhuyenMai.getValueAt(row, 0) + "' vào hóa đơn không ???", "Message", JOptionPane.YES_NO_CANCEL_OPTION);
                if (cf == JOptionPane.NO_OPTION) {
                    return;
                }
                hoaDonChoRepository.updateKhuyenMaibyIdHoaDon(idHD, idKM);
                System.out.println("id km:" + idKM);
                JOptionPane.showMessageDialog(this, "Cập nhật thông tin hóa đơn thành công !");
                banHangfr.fillTableHoaDonCho();

                // fill thông tin khuyen mai lên table 
                KhuyenMai km = hoaDonChoRepository.getKhuyenMaibyMa(maKM);
                if (km != null) {
                    banHangfr.loadChonKhuyenMai(rowHD, km.getMa(), km.getPhanTramGiam(), km.getGiaGiam());
                    banHangfr.fillTableHoaDonCho();
                    System.out.println("gia giam:" + km.getGiaGiam());

                }
                banHangfr.selectedRowInBanHang = rowHD;
                tb_hoadon2.setRowSelectionInterval(banHangfr.selectedRowInBanHang, banHangfr.selectedRowInBanHang);

                this.dispose();
            }
        }
    }//GEN-LAST:event_btn_chonmuaActionPerformed

    private void tblKhuyenMaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKhuyenMaiMouseClicked

    }//GEN-LAST:event_tblKhuyenMaiMouseClicked

    private void txtTimKiemKMKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKMKeyReleased

    }//GEN-LAST:event_txtTimKiemKMKeyReleased

    private void btnTimKiemKMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemKMActionPerformed

    }//GEN-LAST:event_btnTimKiemKMActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnTimKiemKM;
    private javax.swing.JButton btn_chonmua;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pnlCapNhat;
    private javax.swing.JTable tblKhuyenMai;
    private javax.swing.JTextField txtTimKiemKM;
    // End of variables declaration//GEN-END:variables

}
