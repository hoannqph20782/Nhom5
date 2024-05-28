/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Model.KhachHang;

import Interface.KhachHangServiceIplm;
import Repo.KhachHangRepository;
import View_Model.KhachHangModel;
import java.util.ArrayList;

/**
 *
 * @author UyTin
 */
public class KhachHangService implements KhachHangServiceIplm {

    KhachHangRepository khachHangRepository = new KhachHangRepository();
    private ArrayList<KhachHang> danhSachKhachHang = new ArrayList<>();

    @Override
    public ArrayList<KhachHang> getList() {
        return khachHangRepository.getList();
    }

    @Override
    public String them(KhachHang kh) {
        if (khachHangRepository.addNew(kh)) {
            return "Thêm thành công";
        } else {
            return "Thêm thất bại";
        }
    }

    @Override
    public String update(String Ten, KhachHang sdt) {
        if (khachHangRepository.upDateKH(Ten, sdt)) {
            refreshData(); // Cập nhật lại dữ liệu sau khi sửa đổi
            return "Sửa thành công";
        } else {
            return "Sửa thất bại";
        }
    }

    // Phương thức để làm mới dữ liệu từ nguồn
    public void refreshData() {
        danhSachKhachHang.clear();
        danhSachKhachHang.addAll(khachHangRepository.getList());
    }

    private ArrayList<KhachHang> layDuLieuTuDatabase() {
        // Thực hiện truy vấn cơ sở dữ liệu và trả về danh sách khách hàng
        // Đây chỉ là ví dụ, cần thay thế bằng mã thực tế
        return new ArrayList<>();
    }

    public void updateData() {
        // Xóa danh sách cũ
        danhSachKhachHang.clear();

        // Thêm dữ liệu mới từ nguồn dữ liệu (ví dụ: truy vấn từ cơ sở dữ liệu)
        ArrayList<KhachHang> khachHangMoi = layDuLieuTuDatabase();
        danhSachKhachHang.addAll(khachHangMoi);
    }

    @Override
    public ArrayList<KhachHang> TimKiem(String Ten, String sdt) {
        ArrayList<KhachHang> ketQua = new ArrayList<>();
        for (KhachHang kh : danhSachKhachHang) {
            if (kh.getTen().toLowerCase().contains(Ten) || kh.getSdt().contains(sdt)) {
                ketQua.add(kh);
            }
        }
        return ketQua;
    }
    public ArrayList<KhachHang> TimKiemTheoSDT(String sdt) {
    ArrayList<KhachHang> ketQua = new ArrayList<>();
    for (KhachHang kh : danhSachKhachHang) {
        if (kh.getSdt().equals(sdt)) {
            ketQua.add(kh);
        }
    }
    return ketQua;
}

    public static void main(String[] args) {
        KhachHangService khachHangService = new KhachHangService();
        System.out.println(khachHangService.TimKiem("Nguyễn Thị A", "0123456789").toString());
    }
}
