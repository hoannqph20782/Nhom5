/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View;

import Model.ChiTietGiay;
import Model.DanhMuc;
import Model.SanPham;
import Repo.DanhMucRepository;
import Repo.SanPhamChiTietRepository;
import Service.SanPhamChiTietService;
import Service.SanPhamService;
import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ACER
 */
public class Form_SanPham extends javax.swing.JPanel {

    SanPhamService sanPhamService = new SanPhamService();
    SanPhamChiTietService ctgService = new SanPhamChiTietService();
    SanPhamChiTietRepository spctRepo = new SanPhamChiTietRepository();
    ArrayList<ChiTietGiay> listgiay = ctgService.getAllChiTietGiay();
    ArrayList<SanPham> listsp = sanPhamService.getAllSanPhamService();
    DanhMucRepository dmRepo = new DanhMucRepository();

    private int currentPage = 0;
    private int pageSize = 5;
    String imageUrl;

    List<String> listDanhMuc;

    public Form_SanPham() {
        initComponents();
        fillTableSanPham(listgiay);
        fillCboSanPham();
    }

    private void getDSSP() {
        DefaultTableModel model = (DefaultTableModel) tbDanhSach.getModel();
        model.setRowCount(0);
        for (ChiTietGiay ctg : spctRepo.getAllChiTietGiay()) {
            model.addRow(new Object[]{
                ctg.getId(),
                ctg.getIdSanPham().getMa(),
                ctg.getIdSanPham().getTen(),
                ctg.getIdDanhMuc().getTen(),
                ctg.getGiaBan(),
                ctg.getMota(),
                ctg.getTrangThai() == 1 ? "Đang bán" : "Ngừng bán",
                ctg.getHinhAnh()
            });
        }
        ButtonState();
    }

    public void fillTableSanPham(List<ChiTietGiay> listgiay) {
        // Lấy mô hình bảng từ tbDanhSach và thiết lập số dòng là 0
        DefaultTableModel def = (DefaultTableModel) tbDanhSach.getModel();
        def.setRowCount(0);

        // Tính toán vị trí bắt đầu và kết thúc cho trang hiện tại
        int start = currentPage * pageSize;
        int end = Math.min(start + pageSize, listgiay.size());

        // Duyệt qua các phần tử từ vị trí bắt đầu đến kết thúc và thêm chúng vào mô hình bảng
        for (int i = start; i < end; i++) {
            ChiTietGiay ctg = listgiay.get(i); // Đảm bảo lấy phần tử từ danh sách listgiay
            Object[] rowData = {
                ctg.getId(),
                ctg.getIdSanPham().getMa(),
                ctg.getIdSanPham().getTen(),
                // Đảm bảo rằng danh mục không null trước khi lấy tên
                ctg.getIdDanhMuc() != null ? ctg.getIdDanhMuc().getTen() : "",
                ctg.getGiaBan(),
                ctg.getMota(),
                // Hiển thị trạng thái là "Đang bán" hoặc "Ngừng bán"
                ctg.getTrangThai() == 1 ? "Đang bán" : "Ngừng bán",
                ctg.getHinhAnh()
            };
            // Thêm hàng dữ liệu vào mô hình bảng
            def.addRow(rowData);
        }
        // Cập nhật trạng thái của các nút phân trang
        ButtonState();
    }

    private void ButtonState() {
        // Tính tổng số trang dựa trên kích thước của danh sách và số lượng phần tử mỗi trang
        int pageCount = (int) Math.ceil((double) listgiay.size() / pageSize);

        // Kích hoạt hoặc vô hiệu hóa các nút phân trang dựa trên trang hiện tại
        btNhoMax.setEnabled(currentPage > 0);
        btLonMax.setEnabled(currentPage < pageCount - 1);
        // Cập nhật nhãn trang để hiển thị trang hiện tại và tổng số trang
        lblTrang.setText("Trang: " + (currentPage + 1) + " / " + pageCount);
    }

    public void fillCboSanPham() {
        listDanhMuc = spctRepo.getDanhMuc();
        if (listDanhMuc.isEmpty()) {
            System.out.println("List Danh Muc Rong");
        } else {
            cboDanhMuc.removeAllItems();
            for (String string : listDanhMuc) {
                cboDanhMuc.addItem(string);
            }
            listDanhMuc.add("All");
            cboLocDanhMuc.removeAllItems();
            for (String string : listDanhMuc) {
                cboLocDanhMuc.addItem(string);
            }
        }
    }

    ChiTietGiay getList() {
        Integer TrangThai = rdoDangBan.isSelected() ? 1 : 0;
        ChiTietGiay ctg = new ChiTietGiay();
        SanPham sp = new SanPham();
        ctg.setId(Integer.parseInt(lblId.getText()));
        sp.setMa(txtMaSanPham.getText());
        sp.setTen(txtTenSanPham.getText());
        ctg.setIdDanhMuc((DanhMuc) cboDanhMuc.getSelectedItem());
        ctg.setGiaBan(new BigDecimal(txtGia.getText()));
        ctg.setMota(txtMoTa.getText());
        ctg.setTrangThai(TrangThai);
        return ctg;
    }

    public void showImage(String imageUrl) {
        if (imageUrl != null) {
            ImageIcon imageIcon = new ImageIcon(imageUrl);
            Image image = imageIcon.getImage();
            Image newImg = image.getScaledInstance(anhChiTiet.getWidth(), anhChiTiet.getHeight(), Image.SCALE_SMOOTH);
            ImageIcon imgAfter = new ImageIcon(newImg);
            System.out.println(imageUrl);
            anhChiTiet.setIcon(imgAfter);
        } else {
            System.out.println("Khong Co Anh");
            anhChiTiet.setIcon(null);
        }
    }

    public void clearFrom() {
        lblId.setText("");
        txtMaSanPham.setText("");
        txtTenSanPham.setText("");
        txtLocGia.setText("");
        txtGia.setText("");
        txtMoTa.setText("");
        cboDanhMuc.setSelectedIndex(0);
        rdoDangBan.setSelected(true);
        cboLocDanhMuc.setSelectedIndex(0);
        anhChiTiet.setIcon(null);
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
        jPanel7 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        cboLocDanhMuc = new javax.swing.JComboBox<>();
        btnLoc = new javax.swing.JButton();
        txtLocGia = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        anhChiTiet = new javax.swing.JLabel();
        btnUpLoad = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        rdoDangBan = new javax.swing.JRadioButton();
        rdoNgungBan = new javax.swing.JRadioButton();
        btnThem = new javax.swing.JButton();
        btnCapNhat = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        txtTenSanPham = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtGia = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtMoTa = new javax.swing.JTextArea();
        btnChiTiet = new javax.swing.JButton();
        cboDanhMuc = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblId = new javax.swing.JLabel();
        txtMaSanPham = new javax.swing.JLabel();
        btNhoMax = new javax.swing.JButton();
        btLonMax = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbDanhSach = new javax.swing.JTable();
        lblTrang = new javax.swing.JLabel();

        jPanel7.setBackground(new java.awt.Color(204, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)), "Lọc Sản Phẩm"));

        jLabel14.setText("Danh Mục");

        jLabel15.setText("Giá Tiền");

        cboLocDanhMuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboLocDanhMucActionPerformed(evt);
            }
        });

        btnLoc.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnLoc.setText("Lọc");
        btnLoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLocActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addComponent(cboLocDanhMuc, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 143, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addComponent(txtLocGia, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(98, 98, 98)
                .addComponent(btnLoc, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(74, 74, 74))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboLocDanhMuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLocGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(btnLoc, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(204, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)), "Thông Tin Sản Phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(51, 51, 51))); // NOI18N

        btnUpLoad.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnUpLoad.setText("UpLoad");
        btnUpLoad.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnUpLoadMouseClicked(evt);
            }
        });
        btnUpLoad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpLoadActionPerformed(evt);
            }
        });

        jLabel2.setText("Tên Sản Phẩm");

        jLabel5.setText("Danh Mục");

        jLabel12.setText("Mô Tả");

        jLabel13.setText("Trạng Thái");

        buttonGroup1.add(rdoDangBan);
        rdoDangBan.setText("Đang Bán");

        buttonGroup1.add(rdoNgungBan);
        rdoNgungBan.setText("Ngừng Bán");

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

        btnReset.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnReset.setText("Reset");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        jLabel3.setText("Giá");

        txtMoTa.setColumns(20);
        txtMoTa.setRows(5);
        jScrollPane2.setViewportView(txtMoTa);

        btnChiTiet.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnChiTiet.setText("Xem Chi Tiết");
        btnChiTiet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChiTietActionPerformed(evt);
            }
        });

        cboDanhMuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboDanhMucActionPerformed(evt);
            }
        });

        jLabel4.setText("Mã Sản Phẩm");

        jLabel6.setText("Id");

        lblId.setText("______________________");

        txtMaSanPham.setText("______________________");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(btnUpLoad))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(anhChiTiet, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(43, 43, 43)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(jLabel6)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblId)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtTenSanPham)
                                .addComponent(cboDanhMuc, 0, 272, Short.MAX_VALUE))
                            .addComponent(txtMaSanPham))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 92, Short.MAX_VALUE)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel3))
                                .addGap(32, 32, 32)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtGia)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE)))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addGap(18, 18, 18)
                                .addComponent(rdoDangBan)
                                .addGap(18, 18, 18)
                                .addComponent(rdoNgungBan)))
                        .addGap(20, 20, 20))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addComponent(btnCapNhat)
                        .addGap(62, 62, 62)
                        .addComponent(btnReset)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnChiTiet)
                        .addGap(153, 153, 153))))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(anhChiTiet, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUpLoad)
                    .addComponent(btnThem)
                    .addComponent(btnCapNhat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnReset, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnChiTiet, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(txtGia, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel3))
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel6)
                                .addComponent(lblId)))
                        .addGap(22, 22, 22)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtMaSanPham))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel2)
                                .addComponent(txtTenSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel12))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cboDanhMuc, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5))
                                .addGap(41, 41, 41))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel13)
                                    .addComponent(rdoDangBan)
                                    .addComponent(rdoNgungBan))
                                .addGap(42, 42, 42)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btNhoMax.setText("<<");
        btNhoMax.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btNhoMaxMouseClicked(evt);
            }
        });
        btNhoMax.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btNhoMaxActionPerformed(evt);
            }
        });

        btLonMax.setText(">>");
        btLonMax.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btLonMaxMouseClicked(evt);
            }
        });
        btLonMax.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btLonMaxActionPerformed(evt);
            }
        });

        tbDanhSach.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Id", "Mã Sản Phẩm", "Tên Sản Phẩm", "Danh Mục", "Giá", "Mô Tả", "Trạng Thái", "Image"
            }
        ));
        tbDanhSach.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbDanhSachMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbDanhSach);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(279, 279, 279)
                .addComponent(btNhoMax)
                .addGap(73, 73, 73)
                .addComponent(lblTrang, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addComponent(btLonMax)
                .addContainerGap(423, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1))
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(550, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btLonMax)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(btNhoMax)
                            .addGap(2, 2, 2))
                        .addComponent(lblTrang, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(24, 24, 24))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(72, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cboLocDanhMucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboLocDanhMucActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboLocDanhMucActionPerformed

    private void btnLocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLocActionPerformed
        String keyword = (String) cboLocDanhMuc.getSelectedItem();
        Float gia = txtLocGia.getText().trim().equals("") ? null : Float.parseFloat(txtLocGia.getText());
        if (keyword.equalsIgnoreCase("all")) {
            listgiay = spctRepo.getAllChiTietGiay();
        } else {
            listgiay = (ArrayList<ChiTietGiay>) spctRepo.locSanPham(keyword, gia);
        }

        System.out.println(keyword);
        if (listgiay.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy sản phẩm");
        }
        fillTableSanPham(listgiay);
    }//GEN-LAST:event_btnLocActionPerformed

    private void btnUpLoadMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUpLoadMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnUpLoadMouseClicked

    private void btnUpLoadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpLoadActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
            @Override
            public boolean accept(File f) {
                if (f.isDirectory()) {
                    return true;
                } else {
                    String filename = f.getName().toLowerCase();
                    return filename.endsWith(".jpg") || filename.endsWith(".jpeg") || filename.endsWith(".png") || filename.endsWith(".gif");
                }
            }

            @Override
            public String getDescription() {
                return "Các tệp ảnh (*.jpg, *.jpeg, *.png, *.gif)";
            }
        });

        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                BufferedImage image = ImageIO.read(selectedFile);
                ImageIcon imageIcon = new ImageIcon(image.getScaledInstance(anhChiTiet.getWidth(), anhChiTiet.getHeight(), Image.SCALE_SMOOTH));
                anhChiTiet.setIcon(imageIcon);
                imageUrl = selectedFile.getAbsolutePath(); // Lưu đường dẫn ảnh
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Lỗi tải ảnh: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnUpLoadActionPerformed

    public boolean checkRongTxtArea(JTextArea txtA) {
        if (txtA.getText().trim().equalsIgnoreCase("")) {
            return false;
        }
        return true;
    }

    public boolean checkRongTxt(JTextField txt) {
        if (txt.getText().trim().equalsIgnoreCase("")) {
            return false;
        }
        return true;
    }

    public boolean checkPhaiLaSo(JTextField txt) {
        try {
            Integer.parseInt(txt.getText().trim());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    private DanhMuc FillTenDanhMuc(String ten) {
        return dmRepo.getDanhMucTen(ten);
    }

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed

        int selected = -1;
        if (checkRongTxtArea(txtMoTa) == false || checkRongTxt(txtGia) == false
                || checkRongTxt(txtTenSanPham) == false) {
            JOptionPane.showMessageDialog(this, "Hãy nhập đầy đủ thông tin.");
            return;
        }

        if (!checkPhaiLaSo(txtGia)) {
            JOptionPane.showMessageDialog(this, "Giá phải là số.");
            return;
        }

        BigDecimal gia = new BigDecimal(txtGia.getText());
        if (gia.compareTo(BigDecimal.ZERO) < 0) {
            JOptionPane.showMessageDialog(this, "Giá không được âm.");
            return;
        }

        SanPham sanPham = new SanPham();
        sanPham.setMa(String.valueOf(txtMaSanPham.getText()));
        sanPham.setTen(String.valueOf(txtTenSanPham.getText()));
        if (rdoDangBan.isSelected()) {
            sanPham.setTrangThai(1);
        } else {
            sanPham.setTrangThai(0);
        }

        Integer sanPhamMa = sanPhamService.insert(sanPham);
        if (sanPhamMa <= 0) {
            JOptionPane.showMessageDialog(this, "Thêm thất bại");
            return;
        }

        ChiTietGiay ctg = new ChiTietGiay();
        ctg.setId(sanPhamMa);
        ctg.setIdSanPham(sanPhamService.getSanPhamMa(sanPham.getMa()));
        ctg.setIdDanhMuc(FillTenDanhMuc((String) cboDanhMuc.getSelectedItem()));
        ctg.setGiaBan(new BigDecimal(txtGia.getText()));
        ctg.setMota(String.valueOf(txtMoTa.getText()));
        if (rdoDangBan.isSelected()) {
            ctg.setTrangThai(1);
        } else {
            ctg.setTrangThai(0);
        }
        ctg.setHinhAnh(imageUrl); // Lưu đường dẫn ảnh
        JOptionPane.showMessageDialog(this, spctRepo.addChiTietSanPham(ctg));
        listgiay = spctRepo.getAllSanPham();
        fillTableSanPham(listgiay);
        clearFrom();
        showImage(imageUrl);


    }//GEN-LAST:event_btnThemActionPerformed

    public boolean checkPhaiDuong(JTextField txt) {
        try {
            int value = Integer.parseInt(txt.getText().trim());

            return value >= 0;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return false;
        }
    }
    private void btnCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapNhatActionPerformed
        // TODO add your handling code here:
        int selected = -1;
        if (checkRongTxtArea(txtMoTa) == false || checkRongTxt(txtGia) == false
                || checkRongTxt(txtTenSanPham) == false) {
            JOptionPane.showMessageDialog(this, "Hãy chọn sản phẩm muốn cập nhật.");
        } else if (checkPhaiLaSo(txtGia) == false) {
            JOptionPane.showMessageDialog(this, "Giá bán không phải là số.");
        } else if (checkPhaiDuong(txtGia) == false) {
            JOptionPane.showMessageDialog(this, "Giá bán không được âm.");
        }

        SanPham sanPham = new SanPham();
        sanPham.setMa(String.valueOf(txtMaSanPham.getText()));
        sanPham.setTen(String.valueOf(txtTenSanPham.getText()));
        if (rdoDangBan.isSelected()) {
            sanPham.setTrangThai(1);
        } else {
            sanPham.setTrangThai(0);
        }
        if (!sanPhamService.update(sanPham)) {
            JOptionPane.showMessageDialog(this, "Sửa thất bại");
            return;
        }

        ChiTietGiay ctg = new ChiTietGiay();

        ctg.setIdSanPham(sanPhamService.getSanPhamMa(sanPham.getMa()));
        ctg.setIdDanhMuc(FillTenDanhMuc((String) cboDanhMuc.getSelectedItem()));
        ctg.setGiaBan(new BigDecimal(txtGia.getText()));
        ctg.setMota(String.valueOf(txtMoTa.getText()));
        if (rdoDangBan.isSelected()) {
            ctg.setTrangThai(1);
        } else {
            ctg.setTrangThai(0);
        }
        ctg.setHinhAnh(imageUrl);
        ctg.setId(Integer.parseInt(lblId.getText()));
        JOptionPane.showMessageDialog(this, spctRepo.updateChiTietSanPham(ctg));
        listgiay = spctRepo.getAllSanPham();
        fillTableSanPham(listgiay);
        clearFrom();
        showImage(imageUrl);
    }//GEN-LAST:event_btnCapNhatActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        clearFrom();
        ButtonState();
        getDSSP();
    }//GEN-LAST:event_btnResetActionPerformed

    private void btnChiTietActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChiTietActionPerformed
        System.out.println("Button clicked!"); // Kiểm tra xem phương thức được gọi

        // Tạo một JDialog mới
        JDialog dialog = new JDialog();
        dialog.setTitle("Chi Tiết Sản Phẩm");

        // Thêm View_SanPhamChiTiet vào JDialog
        View_SanPhamChiTiet fctsp = new View_SanPhamChiTiet();
        dialog.add(fctsp);

        // Cài đặt kích thước cho JDialog
        dialog.setSize(1000, 800);
        dialog.setLocationRelativeTo(null); // Đặt vị trí cửa sổ ở trung tâm màn hình
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE); // Đóng cửa sổ khi bấm nút đóng

        // Hiển thị JDialog
        dialog.setVisible(true);
    }//GEN-LAST:event_btnChiTietActionPerformed

    private void cboDanhMucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboDanhMucActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboDanhMucActionPerformed

    private void btNhoMaxMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btNhoMaxMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btNhoMaxMouseClicked

    private void btLonMaxMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btLonMaxMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btLonMaxMouseClicked

    private void tbDanhSachMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbDanhSachMouseClicked
        int index = tbDanhSach.getSelectedRow();
        if (index < 0) {
            return;
        }
        lblId.setText(tbDanhSach.getValueAt(index, 0).toString());
        txtMaSanPham.setText(tbDanhSach.getValueAt(index, 1).toString());
        txtTenSanPham.setText(tbDanhSach.getValueAt(index, 2).toString());
        cboDanhMuc.setSelectedItem(tbDanhSach.getValueAt(index, 3).toString());
        txtGia.setText(tbDanhSach.getValueAt(index, 4).toString());
        txtMoTa.setText(tbDanhSach.getValueAt(index, 5).toString());
        if (tbDanhSach.getValueAt(index, 6).toString() == "Đang bán") {
            rdoDangBan.setSelected(true);
        } else {
            rdoNgungBan.setSelected(true);
        }
        String imageUrl;
        imageUrl = tbDanhSach.getValueAt(index, 7).toString();
        showImage(imageUrl);
    }//GEN-LAST:event_tbDanhSachMouseClicked

    private void btLonMaxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btLonMaxActionPerformed
        // TODO add your handling code here:
        currentPage++;
        fillTableSanPham(listgiay);
    }//GEN-LAST:event_btLonMaxActionPerformed

    private void btNhoMaxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNhoMaxActionPerformed
        // TODO add your handling code here:
        currentPage--;
        fillTableSanPham(listgiay);
    }//GEN-LAST:event_btNhoMaxActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel anhChiTiet;
    private javax.swing.JButton btLonMax;
    private javax.swing.JButton btNhoMax;
    private javax.swing.JButton btnCapNhat;
    private javax.swing.JButton btnChiTiet;
    private javax.swing.JButton btnLoc;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnUpLoad;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cboDanhMuc;
    private javax.swing.JComboBox<String> cboLocDanhMuc;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblId;
    private javax.swing.JLabel lblTrang;
    private javax.swing.JRadioButton rdoDangBan;
    private javax.swing.JRadioButton rdoNgungBan;
    private javax.swing.JTable tbDanhSach;
    private javax.swing.JTextField txtGia;
    private javax.swing.JTextField txtLocGia;
    private javax.swing.JLabel txtMaSanPham;
    private javax.swing.JTextArea txtMoTa;
    private javax.swing.JTextField txtTenSanPham;
    // End of variables declaration//GEN-END:variables
}
