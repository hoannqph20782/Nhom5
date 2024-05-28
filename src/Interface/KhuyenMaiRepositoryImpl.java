package Interface;

import Model.KhuyenMai;
import java.util.ArrayList;

public interface KhuyenMaiRepositoryImpl {

    public ArrayList<KhuyenMai> getAllKM();

    public boolean add(KhuyenMai km);
    
    public boolean update(String ma, KhuyenMai km);
    
    public ArrayList<KhuyenMai> timKiem(String ma, String ten, float mucGiam);
    public ArrayList<KhuyenMai> searchDate(String ngayBatDau, String ngayKetThuc);
}
