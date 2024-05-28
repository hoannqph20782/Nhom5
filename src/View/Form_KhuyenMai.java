/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View;

import Model.KM;
import Service.KMService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ACER
 */
public class Form_KhuyenMai extends javax.swing.JPanel {

    KMService kms = new KMService();
    ArrayList<KM> listKM = new ArrayList<>();

    public Form_KhuyenMai() {
        initComponents();
        listKM = kms.getAll();
        loadTable();
    }

    public void loadTable() {
        DefaultTableModel model = (DefaultTableModel) tblKhuyenMai.getModel();
        model.setRowCount(0);
        for (KM km : listKM) {
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

    public void clearFrom() {
        txtMaKM.setText("");
        txtTenKM.setText("");
        txtMucGiam.setText("");
        txtSoLuongKM.setText("");
        txtNgayBatDauKM.setText("");
        txtNgayKetThucKM.setText("");
        rdoNgungHoatDong.setSelected(true);
        txtMoTaKM.setText("");
        txtTimKiemKM.setText("");
        txtGiaGiam.setText("");
        rdoGiamTien.setSelected(true);
        
    }

    public KM getFromData() {
        KM km = new KM();
        km.setMa(txtMaKM.getText().trim());
        km.setTen(txtTenKM.getText().trim());
        km.setPhanTramGiam(Float.parseFloat(txtMucGiam.getText().trim()));
        km.setGiaGiam(Double.parseDouble(txtGiaGiam.getText().trim()));
        km.setSoLuong(Integer.parseInt(txtSoLuongKM.getText().trim()));
        String ngayBatDau = txtNgayBatDauKM.getText().trim();
        Date nbd = parseDate(ngayBatDau);
        if (ngayBatDau != null) {
            km.setNgayBatDau(nbd);
        } else {
            JOptionPane.showMessageDialog(this, "Ngày bắt đầu không hợp lệ !");
        }

        String ngayKetThuc = txtNgayKetThucKM.getText().trim();
        Date nkt = parseDate(ngayKetThuc);
        if (ngayKetThuc != null) {
            km.setNgayKetThuc(nkt);
        } else {
            JOptionPane.showMessageDialog(this, "Ngày kết thúc không hợp lệ !");
        }

        if (rdoDangHoatDong.isSelected()) {
            km.setTrangThai(1);
        } else {
            km.setTrangThai(0);
        }

        if (rdoGiamTien.isSelected()) {
            km.setHinhThucGiam(0);
        } else {
            km.setHinhThucGiam(1);
        }

        km.setMoTa(txtMoTaKM.getText().trim());
        return km;
    }

    private Date parseDate(String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    //check trống:
    public boolean checkTrong() {
        if (txtMaKM.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng không để trống mã khuyến mại!");
            txtMaKM.requestFocus();
            return false;
        } else if (txtTenKM.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng không để trống tên khuyến mại!");
            txtTenKM.requestFocus();
            return false;
        } else if (txtMucGiam.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng không để trống mức giảm khuyến mại!");
            txtMucGiam.requestFocus();
            return false;
        } else if (txtNgayBatDauKM.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng không để trống ngày bắt đầu!");
            txtNgayBatDauKM.requestFocus();
            return false;
        } else if (txtNgayKetThucKM.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng không để trống ngày kết thúc!");
            txtNgayKetThucKM.requestFocus();
            return false;
        } else if (txtSoLuongKM.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng không để trống số lượng!");
            txtSoLuongKM.requestFocus();
            return false;
        } else if (txtMoTaKM.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng không để trống mô tả khuyến mại!");
            txtMoTaKM.requestFocus();
            return false;
        } else if (txtGiaGiam.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng không để trống giá giảm!");
            txtGiaGiam.requestFocus();
            return false;
        }

        return true;
    }

    // Check ngày bắt đầu và ngày kết thúc:
    public boolean validateDate() {
        // Kiểm tra ngày bắt đầu
        String ngayBatDauStr = txtNgayBatDauKM.getText().trim();
        if (!isValidDate(ngayBatDauStr)) {
            JOptionPane.showMessageDialog(this, "Ngày bắt đầu không hợp lệ (định dạng chuẩn: năm-tháng-ngày)", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Kiểm tra ngày kết thúc
        String ngayKetThucStr = txtNgayKetThucKM.getText().trim();
        if (!isValidDate(ngayKetThucStr)) {
            JOptionPane.showMessageDialog(this, "Ngày kết thúc không hợp lệ (định dạng chuẩn: năm-tháng-ngày)", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        // Kiểm tra nếu ngày kết thúc sau ngày bắt đầu
        Date ngayBatDau = parseDate(ngayBatDauStr);
        Date ngayKetThuc = parseDate(ngayKetThucStr);

        if (ngayBatDau != null && ngayKetThuc != null && ngayKetThuc.before(ngayBatDau)) {
            JOptionPane.showMessageDialog(this, "Ngày kết thúc phải sau ngày bắt đầu", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    private boolean isValidDate(String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false); // Đảm bảo không chấp nhận ngày không hợp lệ (ví dụ: 31/02/2022)
        try {
            dateFormat.parse(dateString);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    // check mức giảm:
    // số âm và trong khoảng cho phép:
    public boolean validateMucGiam() {
        float mucGiam = Float.parseFloat(txtMucGiam.getText().trim());
        if (mucGiam < 0) {
            JOptionPane.showMessageDialog(this, "Mức giảm không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        } else if (mucGiam > 100) {
            JOptionPane.showMessageDialog(this, "Mức giảm chỉ từ 0 % --> 100 %", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    // check mức giảm là số not chữ:
    public boolean isNumeric(String str) {
        try {
            Float.parseFloat(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean validateDinhDangMucGiam() {
        String mucGiam = txtMucGiam.getText().trim();
        if (!isNumeric(mucGiam)) {
            JOptionPane.showMessageDialog(this, "Mức giảm phải là số !", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtTenKM = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtMucGiam = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtNgayBatDauKM = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtNgayKetThucKM = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        rdoDangHoatDong = new javax.swing.JRadioButton();
        rdoNgungHoatDong = new javax.swing.JRadioButton();
        btnLuu = new javax.swing.JButton();
        btnCapNhat = new javax.swing.JButton();
        btnLamMoi = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txtMaKM = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtSoLuongKM = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtMoTaKM = new javax.swing.JTextArea();
        jLabel14 = new javax.swing.JLabel();
        txtGiaGiam = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        rdoGiamPhanTram = new javax.swing.JRadioButton();
        rdoGiamTien = new javax.swing.JRadioButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblKhuyenMai = new javax.swing.JTable();
        btnTimKiemKM = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        cboTrangThaiKM = new javax.swing.JComboBox<>();
        txtTimKiemKM = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        cboLocHT = new javax.swing.JComboBox<>();
        btnReload = new javax.swing.JButton();
        txtNgayBT = new com.toedter.calendar.JDateChooser();
        txtNgayKT = new com.toedter.calendar.JDateChooser();

        setBackground(new java.awt.Color(204, 255, 255));

        jPanel2.setBackground(new java.awt.Color(204, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Hình thức khuyến mại", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        jLabel1.setText("Tên khuyến mại :");

        jLabel2.setText("Mức giảm giá (%) :");

        jLabel3.setText("Ngày bắt đầu : ");

        jLabel4.setText("Ngày kết thúc : ");

        jLabel5.setText("Trạng thái : ");

        buttonGroup1.add(rdoDangHoatDong);
        rdoDangHoatDong.setText("Đang hoạt động");

        buttonGroup1.add(rdoNgungHoatDong);
        rdoNgungHoatDong.setSelected(true);
        rdoNgungHoatDong.setText("Ngừng hoạt động");

        btnLuu.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnLuu.setText("Lưu");
        btnLuu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuActionPerformed(evt);
            }
        });

        btnCapNhat.setText("Cập nhật");
        btnCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapNhatActionPerformed(evt);
            }
        });

        btnLamMoi.setText("Làm mới");
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });

        jLabel6.setText("Mã khuyến mại :");

        jLabel7.setText("Số lượng : ");

        jLabel8.setText("Mô tả : ");

        txtMoTaKM.setColumns(20);
        txtMoTaKM.setRows(5);
        jScrollPane2.setViewportView(txtMoTaKM);

        jLabel14.setText("Giá giảm:");

        jLabel15.setText("Hình thức giảm :");

        buttonGroup2.add(rdoGiamPhanTram);
        rdoGiamPhanTram.setText("Giảm %");

        buttonGroup2.add(rdoGiamTien);
        rdoGiamTien.setSelected(true);
        rdoGiamTien.setText("Giảm tiền");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnCapNhat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTenKM)
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMaKM)
                        .addGap(6, 6, 6))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtSoLuongKM)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMucGiam, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(btnLuu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNgayBatDauKM)
                            .addComponent(txtNgayKetThucKM))
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtGiaGiam)))
                        .addGap(6, 6, 6))
                    .addComponent(jScrollPane2)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rdoDangHoatDong, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(rdoGiamPhanTram, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
                                .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(rdoNgungHoatDong, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(rdoGiamTien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtMaKM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtTenKM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtMucGiam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txtGiaGiam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtNgayBatDauKM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtNgayKetThucKM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtSoLuongKM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdoNgungHoatDong)
                    .addComponent(rdoDangHoatDong))
                .addGap(9, 9, 9)
                .addComponent(jLabel15)
                .addGap(9, 9, 9)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdoGiamPhanTram)
                    .addComponent(rdoGiamTien))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(7, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(204, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách khuyến mại", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        tblKhuyenMai.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã KM", "Tên KM", "% giảm", "Giá giảm", "Số lượng", "Ngày bắt đầu", "Ngày kết thúc", "Hình thức", "Trạng thái", "Mô tả"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, true, false
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

        btnTimKiemKM.setText("Tìm kiếm");
        btnTimKiemKM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemKMActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setText("Ngày bắt đầu: ");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setText("Ngày kết thúc : ");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel11.setText("Lọc trạng thái :");

        cboTrangThaiKM.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Đang hoạt động", "Ngừng hoạt động", " ", " " }));
        cboTrangThaiKM.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboTrangThaiKMItemStateChanged(evt);
            }
        });

        txtTimKiemKM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemKMActionPerformed(evt);
            }
        });
        txtTimKiemKM.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTimKiemKMKeyReleased(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel12.setText("Tìm theo mã / tên ");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel13.setText("Lọc hình thức : ");

        cboLocHT.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Giảm %", "Giảm tiền" }));
        cboLocHT.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboLocHTItemStateChanged(evt);
            }
        });

        btnReload.setText("Reload");
        btnReload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReloadActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(cboTrangThaiKM, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtTimKiemKM, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cboLocHT, 0, 175, Short.MAX_VALUE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNgayBT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(txtNgayKT, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnReload, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnTimKiemKM, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(80, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btnTimKiemKM, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnReload, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel12)
                                .addComponent(jLabel9))
                            .addComponent(jLabel10))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTimKiemKM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNgayBT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNgayKT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(jLabel13))
                        .addGap(14, 14, 14)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cboTrangThaiKM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboLocHT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 394, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuActionPerformed
        if (JOptionPane.showConfirmDialog(this, "Bạn chắc chắn muốn thêm voucher ?") == JOptionPane.NO_OPTION) {
            return;
        }
        try {
            if (checkTrong() == true && validateDate() == true && validateMucGiam() && validateDinhDangMucGiam()) {
                kms.add(getFromData());
                listKM = kms.getAll();
                loadTable();
                JOptionPane.showMessageDialog(this, "Thêm voucher thành công!");
                clearFrom();
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Thêm thất bại!");
        }
    }//GEN-LAST:event_btnLuuActionPerformed

    private void btnCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatActionPerformed
        int row = tblKhuyenMai.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Mời chọn dòng cần cập nhật!");
            return;
        }
        String ma = tblKhuyenMai.getValueAt(row, 0).toString();
        if (JOptionPane.showConfirmDialog(this, "Bạn chắc chắn muốn thêm voucher ?") == JOptionPane.NO_OPTION) {
            return;
        }
        try {
            if (checkTrong() == true && validateDate() == true && validateMucGiam() && validateDinhDangMucGiam()) {
                kms.update(ma, getFromData());
                listKM = kms.getAll();
                loadTable();
                JOptionPane.showMessageDialog(this, "Cập nhật thành công");
                clearFrom();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Cập nhật voucher thất bại!");
        }
    }//GEN-LAST:event_btnCapNhatActionPerformed

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        clearFrom();
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void tblKhuyenMaiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKhuyenMaiMouseClicked
        try {
            int row = tblKhuyenMai.getSelectedRow();
            if (row < 0) {
                return;
            }

            txtMaKM.setText(tblKhuyenMai.getValueAt(row, 0).toString());
            txtTenKM.setText(tblKhuyenMai.getValueAt(row, 1).toString());
            txtMucGiam.setText(tblKhuyenMai.getValueAt(row, 2).toString());
            txtGiaGiam.setText(tblKhuyenMai.getValueAt(row, 3).toString());
            txtSoLuongKM.setText(tblKhuyenMai.getValueAt(row, 4).toString());
            txtNgayBatDauKM.setText(tblKhuyenMai.getValueAt(row, 5).toString());
            txtNgayKetThucKM.setText(tblKhuyenMai.getValueAt(row, 6).toString());
            if (tblKhuyenMai.getValueAt(row, 7).toString() == "Giảm tiền") {
                rdoGiamTien.setSelected(true);
            } else {
                rdoGiamPhanTram.setSelected(true);
            }

            if (tblKhuyenMai.getValueAt(row, 8).toString() == "Đang hoạt động") {
                rdoDangHoatDong.setSelected(true);
            } else {
                rdoNgungHoatDong.setSelected(true);
            }
            txtMoTaKM.setText(tblKhuyenMai.getValueAt(row, 9).toString());
        } catch (Exception e) {
        }
    }//GEN-LAST:event_tblKhuyenMaiMouseClicked

    private void btnTimKiemKMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemKMActionPerformed
        try {
            if (txtNgayBT.getDate() == null || txtNgayKT.getDate() == null) {
                listKM = kms.getAll();
                loadTable();
                JOptionPane.showMessageDialog(this, "Bắt buộc phải chọn đủ 2 trường ngày bắt đầu và ngày kết thúc!");
            } else {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String dateStart = sdf.format(txtNgayBT.getDate());
                String dateEnd = sdf.format(txtNgayKT.getDate());

                ArrayList<KM> listKMDate = kms.searchDate(dateStart, dateEnd);
                DefaultTableModel model = (DefaultTableModel) tblKhuyenMai.getModel();
                model.setRowCount(0);
                for (KM km : listKMDate) {
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
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btnTimKiemKMActionPerformed

    private void cboTrangThaiKMItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboTrangThaiKMItemStateChanged
        try {
            if ("Đang hoạt động".equalsIgnoreCase(cboTrangThaiKM.getSelectedItem().toString())) {
                ArrayList<KM> listKMTT = kms.searchTinhTrang(1);
                DefaultTableModel model = (DefaultTableModel) tblKhuyenMai.getModel();
                model.setRowCount(0);
                for (KM km : listKMTT) {
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
            } else if ("Ngừng hoạt động".equalsIgnoreCase(cboTrangThaiKM.getSelectedItem().toString())) {
                ArrayList<KM> listKMTT = kms.searchTinhTrang(0);
                DefaultTableModel model = (DefaultTableModel) tblKhuyenMai.getModel();
                model.setRowCount(0);
                for (KM km : listKMTT) {
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

        } catch (Exception e) {
        }
    }//GEN-LAST:event_cboTrangThaiKMItemStateChanged

    private void txtTimKiemKMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemKMActionPerformed
        String input = txtTimKiemKM.getText();
        if (input == null) {
            loadTable();
        }
        ArrayList<KM> listKMTK = new ArrayList<>();
        for (var x : kms.search(input)) {
            if (x.getMa().toLowerCase().contains(input.toLowerCase())
                || x.getTen().toLowerCase().contains(input.toLowerCase())) {
                listKMTK.add(x);
            }
        }

        DefaultTableModel model = (DefaultTableModel) tblKhuyenMai.getModel();
        model.setRowCount(0);
        for (KM km : listKMTK) {
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
    }//GEN-LAST:event_txtTimKiemKMActionPerformed

    private void txtTimKiemKMKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKMKeyReleased

    }//GEN-LAST:event_txtTimKiemKMKeyReleased

    private void cboLocHTItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboLocHTItemStateChanged
        try {
            if ("Giảm %".equalsIgnoreCase(cboLocHT.getSelectedItem().toString())) {
                ArrayList<KM> listKMHT = kms.searchHinhThuc(1);
                DefaultTableModel model = (DefaultTableModel) tblKhuyenMai.getModel();
                model.setRowCount(0);
                for (KM km : listKMHT) {
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
            } else if ("Giảm tiền".equalsIgnoreCase(cboLocHT.getSelectedItem().toString())) {
                ArrayList<KM> listKMHT = kms.searchHinhThuc(0);
                DefaultTableModel model = (DefaultTableModel) tblKhuyenMai.getModel();
                model.setRowCount(0);
                for (KM km : listKMHT) {
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

        } catch (Exception e) {
        }
    }//GEN-LAST:event_cboLocHTItemStateChanged

    private void btnReloadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReloadActionPerformed
        listKM = kms.getAll();
        loadTable();
    }//GEN-LAST:event_btnReloadActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCapNhat;
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnLuu;
    private javax.swing.JButton btnReload;
    private javax.swing.JButton btnTimKiemKM;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox<String> cboLocHT;
    private javax.swing.JComboBox<String> cboTrangThaiKM;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JRadioButton rdoDangHoatDong;
    private javax.swing.JRadioButton rdoGiamPhanTram;
    private javax.swing.JRadioButton rdoGiamTien;
    private javax.swing.JRadioButton rdoNgungHoatDong;
    private javax.swing.JTable tblKhuyenMai;
    private javax.swing.JTextField txtGiaGiam;
    private javax.swing.JTextField txtMaKM;
    private javax.swing.JTextArea txtMoTaKM;
    private javax.swing.JTextField txtMucGiam;
    private com.toedter.calendar.JDateChooser txtNgayBT;
    private javax.swing.JTextField txtNgayBatDauKM;
    private com.toedter.calendar.JDateChooser txtNgayKT;
    private javax.swing.JTextField txtNgayKetThucKM;
    private javax.swing.JTextField txtSoLuongKM;
    private javax.swing.JTextField txtTenKM;
    private javax.swing.JTextField txtTimKiemKM;
    // End of variables declaration//GEN-END:variables
}
