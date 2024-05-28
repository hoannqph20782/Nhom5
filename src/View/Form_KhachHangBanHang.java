/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View;

import Interface.KhachHangServiceIplm;
import Model.KhachHang;
import Repo.HoaDonChoRepo;
import Repo.KhachHangRepository;
import Service.KhachHangService;
import Utilities.DBConnext;
import View_Model.KhachHangModel;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author asus
 */
public class Form_KhachHangBanHang extends javax.swing.JFrame {

    private KhachHangService khs = new KhachHangService();
    private KhachHangRepository khr = new KhachHangRepository();
    public static HoaDonChoRepo hoaDonChoRepository = new HoaDonChoRepo();
    public static KhachHang khach;

    JTable tb_giohang;
    JTable tb_hoadon2;
    KhachHangModel khm;
    Form_BanHang banHangfr;

    long count, soTrang, trang = 1;

    private KhachHangServiceIplm service = new KhachHangService();
    ArrayList<KhachHang> listKh = new ArrayList<>();

    @SuppressWarnings("unchecked")
    public Form_KhachHangBanHang(Form_BanHang banHangfr, JTable tb_hoadon2) {
        initComponents();

        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setSize(1005, 530);
        setLocationRelativeTo(null);
        titleTable();
        countDB();
        if (count % 5 == 0) {
            soTrang = count / 5;
        } else {
            soTrang = count / 5 + 1;
        }
        loadData(1);
        lbsoTrang.setText("1/" + soTrang);
        lbtrang.setText("1");
        this.tb_hoadon2 = tb_hoadon2;
        this.banHangfr = banHangfr;
    }

    public void titleTable() {
        khm = new KhachHangModel();
        tblKhachHang.setModel(khm);
        tblKhachHang.setShowHorizontalLines(true);
        tblKhachHang.setShowVerticalLines(true);
    }

    public void countDB() {
        try {
            String query = "Select COUNT(*) from KHACHHANG";
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
            Logger.getLogger(Form_KhachHangBanHang.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void loadData(long trang) {
        titleTable();
        khm.getDataVector().removeAllElements();
        try {
            String query = "Select top 5 * from KHACHHANG where Ten not in ("
                    + "Select top " + (trang * 5 - 5) + " Ten from KHACHHANG)";
            Connection cn = DBConnext.getConnection();
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Vector v = new Vector();

                String Ma = rs.getString(2);
                String Ten = rs.getString(3);
                Date ngaySinh = rs.getDate(4);
                Integer gioiTinh = rs.getInt(5);

                String gt;
                if (gioiTinh == 1) {
                    gt = "Nam";
                } else {
                    gt = "Nữ";
                }
                String sdt = rs.getString(6);
                String email = rs.getString(7);
                String diaChi = rs.getString(8);
                Integer trangThai = rs.getInt(10);
                String tt;

                if (trangThai == 1) {
                    tt = "HD";
                } else {
                    tt = "Nhd";
                }
                v.add(Ma);
                v.add(Ten);
                v.add(ngaySinh);
                v.add(gt);
                v.add(sdt);
                v.add(email);
                v.add(diaChi);
                v.add(tt);
                khm.addRow(v);
            }
            rs.close();
            st.close();
            cn.close();
        } catch (Exception ex) {
            Logger.getLogger(Form_KhachHangBanHang.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean checkSDT() {
        if (txtSDT.getText().matches("[0,+84][\\d]{9}")) {
            return true;

        }
        JOptionPane.showMessageDialog(this, "Sai định dạng số điện thoại!");
        return false;
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        pnlCapNhat = new javax.swing.JPanel();
        lblTenKH = new javax.swing.JLabel();
        lblSDT = new javax.swing.JLabel();
        txtSDT = new javax.swing.JTextField();
        txtTenKH = new javax.swing.JTextField();
        btnThem = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblKhachHang = new javax.swing.JTable();
        btnNhoMax = new javax.swing.JButton();
        btnLonMax = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        txtTimKiem = new javax.swing.JTextField();
        btnTimKiem = new javax.swing.JButton();
        lbtrang = new javax.swing.JLabel();
        btnNho = new javax.swing.JButton();
        btnLon = new javax.swing.JButton();
        lbsoTrang = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        btn_chonmua = new javax.swing.JButton();

        pnlCapNhat.setBackground(new java.awt.Color(204, 255, 255));
        pnlCapNhat.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblTenKH.setText("Tên khách hàng :");

        lblSDT.setText("Số điện thoại :");

        txtTenKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenKHActionPerformed(evt);
            }
        });

        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        tblKhachHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tblKhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKhachHangMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblKhachHang);

        btnNhoMax.setText("<<");
        btnNhoMax.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNhoMaxActionPerformed(evt);
            }
        });

        btnLonMax.setText(">>");
        btnLonMax.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLonMaxActionPerformed(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(255, 255, 204));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tìm kiếm ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        txtTimKiem.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTimKiemCaretUpdate(evt);
            }
        });
        txtTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemActionPerformed(evt);
            }
        });

        btnTimKiem.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnTimKiem.setText("OK");
        btnTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnTimKiem))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimKiem))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lbtrang.setText("jLabel3");

        btnNho.setText("<");
        btnNho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNhoActionPerformed(evt);
            }
        });

        btnLon.setText(">");
        btnLon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLonActionPerformed(evt);
            }
        });

        lbsoTrang.setText("jLabel1");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 102, 255));
        jLabel1.setText("QUẢN LÝ KHÁCH HÀNG");

        btn_chonmua.setText("Chọn Mua Hàng");
        btn_chonmua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_chonmuaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlCapNhatLayout = new javax.swing.GroupLayout(pnlCapNhat);
        pnlCapNhat.setLayout(pnlCapNhatLayout);
        pnlCapNhatLayout.setHorizontalGroup(
            pnlCapNhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCapNhatLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(pnlCapNhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlCapNhatLayout.createSequentialGroup()
                        .addComponent(lblTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(101, 101, 101))
                    .addGroup(pnlCapNhatLayout.createSequentialGroup()
                        .addGap(171, 171, 171)
                        .addComponent(btn_chonmua, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(pnlCapNhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlCapNhatLayout.createSequentialGroup()
                        .addComponent(lblSDT)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(148, Short.MAX_VALUE))
            .addGroup(pnlCapNhatLayout.createSequentialGroup()
                .addGap(87, 87, 87)
                .addComponent(btnNhoMax, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnNho, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbtrang)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLon, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnLonMax, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbsoTrang)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(pnlCapNhatLayout.createSequentialGroup()
                .addGroup(pnlCapNhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlCapNhatLayout.createSequentialGroup()
                        .addGap(380, 380, 380)
                        .addComponent(jLabel1))
                    .addGroup(pnlCapNhatLayout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addGroup(pnlCapNhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnThem)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 939, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        pnlCapNhatLayout.setVerticalGroup(
            pnlCapNhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCapNhatLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel1)
                .addGroup(pnlCapNhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlCapNhatLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
                        .addGroup(pnlCapNhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTenKH)
                            .addComponent(lblSDT)
                            .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlCapNhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnNhoMax)
                            .addComponent(btnNho)
                            .addComponent(lbtrang)
                            .addComponent(btnLon)
                            .addComponent(btnLonMax)
                            .addComponent(lbsoTrang))
                        .addContainerGap(39, Short.MAX_VALUE))
                    .addGroup(pnlCapNhatLayout.createSequentialGroup()
                        .addGap(138, 138, 138)
                        .addGroup(pnlCapNhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_chonmua)
                            .addComponent(btnThem))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jPanel4.getAccessibleContext().setAccessibleName("Tìm kiếm \n");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(pnlCapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(pnlCapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtTenKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenKHActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenKHActionPerformed

    private void tblKhachHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKhachHangMouseClicked
        int index = tblKhachHang.getSelectedRow();
        if (index < 0) {
            return;
        }
        txtTenKH.setText(tblKhachHang.getValueAt(index, 1).toString());
        txtSDT.setText(tblKhachHang.getValueAt(index, 4).toString());

    }//GEN-LAST:event_tblKhachHangMouseClicked

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed

        if (txtTenKH.getText().trim().equals("")
                || txtSDT.getText().trim().equals("")) {

            JOptionPane.showMessageDialog(this, "Vui long khong de trong");
            return;
        }

        String ten = txtTenKH.getText();
        String sdt = txtSDT.getText();

        KhachHang khachHang = new KhachHang(ten, sdt);

        hoaDonChoRepository.addKhachHang(khachHang);
        JOptionPane.showMessageDialog(this, "Thêm Thành  Công");
        txtTenKH.setText("");
        txtSDT.setText("");
        loadData(1);
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnNhoMaxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNhoMaxActionPerformed
        trang = 1;
        loadData(trang);
        lbtrang.setText("1");
        lbsoTrang.setText("1/" + soTrang);
    }//GEN-LAST:event_btnNhoMaxActionPerformed

    private void btnNhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNhoActionPerformed
        if (trang > 1) {
            trang--;
            loadData(trang);
            lbtrang.setText("" + trang);
            lbsoTrang.setText(trang + "/" + soTrang);
        }
    }//GEN-LAST:event_btnNhoActionPerformed

    private void btnLonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLonActionPerformed
        if (trang < soTrang) {
            trang++;
            loadData(trang);
            lbtrang.setText("" + trang);
            lbsoTrang.setText(trang + "/" + soTrang);
        }
    }//GEN-LAST:event_btnLonActionPerformed

    private void btnLonMaxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLonMaxActionPerformed
        trang = soTrang;
        loadData(trang);
        lbtrang.setText("" + soTrang);
        lbsoTrang.setText(soTrang + "/" + soTrang);
    }//GEN-LAST:event_btnLonMaxActionPerformed

    private void txtTimKiemCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTimKiemCaretUpdate
        List<KhachHang> listk = new ArrayList<>();
        for (KhachHang k : listKh) {
            if (k.getTen().toLowerCase().contains(txtTimKiem.getText().toLowerCase())
                    || k.getSdt().toLowerCase().contains(txtTimKiem.getText().toLowerCase())) {
                listk.add(k);

            }
        }
        loadData(1);
    }//GEN-LAST:event_txtTimKiemCaretUpdate

    private void btn_chonmuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_chonmuaActionPerformed

        // TODO add your handling code here:
        int row = tblKhachHang.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn 1 khách hàng !");

        } else {
            String makH = tblKhachHang.getValueAt(row, 0).toString();
            String idKH = hoaDonChoRepository.getIDKHbyMa(makH);
            int rowHD = tb_hoadon2.getSelectedRow();
            if (rowHD < 0) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn 1 hóa đơn để thêm khách hàng !");

            } else {
                String idHD = hoaDonChoRepository.getIdHoaDonByMa(tb_hoadon2.getValueAt(rowHD, 0).toString());
                System.out.println("id hd" + idHD);
                int cf = JOptionPane.showConfirmDialog(this, "Bạn có muốn thêm khách hàng '" + tblKhachHang.getValueAt(row, 1) + "' vào hóa đơn không ???", "Message", JOptionPane.YES_NO_CANCEL_OPTION);
                if (cf == JOptionPane.NO_OPTION) {
                    return;
                }
                hoaDonChoRepository.updateKHinHoaDon(idHD, idKH);
                JOptionPane.showMessageDialog(this, "Cập nhật thông tin hóa đơn thành công !");
                banHangfr.fillTableHoaDonCho();

                // fill thông tin khách hàng lên table 
                KhachHang khach = hoaDonChoRepository.getKhachHangById(idKH);
                if (khach != null) {
                    banHangfr.loadChonKH(khach.getTen(), khach.getSdt());
                    banHangfr.fillTableHoaDonCho();
                }
                // giu chon khach hang tre hoa don 
                banHangfr.selectedRowInBanHang = rowHD;
                tb_hoadon2.setRowSelectionInterval(banHangfr.selectedRowInBanHang, banHangfr.selectedRowInBanHang);

                this.dispose();
            }
        }
    }//GEN-LAST:event_btn_chonmuaActionPerformed

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed
        String timKiemText = txtTimKiem.getText().toLowerCase();
        DefaultTableModel model = (DefaultTableModel) tblKhachHang.getModel();

        model.setRowCount(0);

        try {
            // Cập nhật dữ liệu từ nguồn
            khs.refreshData();

            // Tìm kiếm khách hàng với text tìm kiếm
            ArrayList<KhachHang> kh;

            // Kiểm tra nếu độ dài của chuỗi tìm kiếm là 2 và bắt đầu bằng "10"
            if (timKiemText.length() == 2 && timKiemText.startsWith("10")) {
                kh = khs.TimKiem("", timKiemText);
            } else {
                kh = khs.TimKiem(timKiemText, timKiemText);
            }

            if (kh != null) {
                for (KhachHang k : kh) {
                    Object[] row = new Object[]{
                        k.getMa(),
                        k.getTen(),
                        k.getNgaySinh(),
                        k.getGioiTinh() == 1 ? "Nam" : "Nữ",
                        k.getSdt(),
                        k.getEmail(),
                        k.getDiaChi(),};
                    model.addRow(row);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Xử lý lỗi một cách phù hợp, ví dụ: hiển thị thông báo lỗi cho người dùng
        }

    }//GEN-LAST:event_btnTimKiemActionPerformed

    private void txtTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemActionPerformed
        String timKiemText = txtTimKiem.getText().toLowerCase();
        DefaultTableModel model = (DefaultTableModel) tblKhachHang.getModel();

        model.setRowCount(0);

        try {
            // Cập nhật dữ liệu từ nguồn
            khs.refreshData();

            // Kiểm tra nếu độ dài của chuỗi tìm kiếm là 2 và bắt đầu bằng "10"
            // hoặc nếu độ dài là 10 (đủ số điện thoại)
            if (timKiemText.length() == 2 && timKiemText.startsWith("10")) {
                // Tìm kiếm theo mã
                ArrayList<KhachHang> kh = khs.TimKiem("", timKiemText);
                addToTableModel(model, kh);
            } else if (timKiemText.length() == 10 && timKiemText.matches("\\d+")) {
                // Tìm kiếm theo số điện thoại
                ArrayList<KhachHang> kh = khs.TimKiemTheoSDT(timKiemText);
                addToTableModel(model, kh);
            } else {
                // Tìm kiếm theo thông tin khác
                ArrayList<KhachHang> kh = khs.TimKiem(timKiemText, timKiemText);
                addToTableModel(model, kh);
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Xử lý lỗi một cách phù hợp, ví dụ: hiển thị thông báo lỗi cho người dùng
        }
    }//GEN-LAST:event_txtTimKiemActionPerformed
private void addToTableModel(DefaultTableModel model, ArrayList<KhachHang> kh) {
    if (kh != null) {
        for (KhachHang k : kh) {
            Object[] row = new Object[]{
                k.getMa(),
                k.getTen(),
                k.getNgaySinh(),
                k.getGioiTinh() == 1 ? "Nam" : "Nữ",
                k.getSdt(),
                k.getEmail(),
                k.getDiaChi(),
            };
            model.addRow(row);
        }
    }
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLon;
    private javax.swing.JButton btnLonMax;
    private javax.swing.JButton btnNho;
    private javax.swing.JButton btnNhoMax;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JButton btn_chonmua;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblSDT;
    private javax.swing.JLabel lblTenKH;
    private javax.swing.JLabel lbsoTrang;
    private javax.swing.JLabel lbtrang;
    private javax.swing.JPanel pnlCapNhat;
    private javax.swing.JTable tblKhachHang;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTenKH;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
