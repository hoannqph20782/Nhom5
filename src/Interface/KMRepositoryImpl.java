package Interface;

import Model.KM;
import java.util.ArrayList;

public interface KMRepositoryImpl {

    public ArrayList<KM> getAllKM();

    public boolean add(KM km);

    public boolean update(String ma, KM km);

    public ArrayList<KM> search(String maKM);

    public ArrayList<KM> searchDate(String ngayBatDau, String ngayKetThuc);

    public ArrayList<KM> searchTinhTrang(int tinhTrang);

    public ArrayList<KM> searchHinhThuc(int hinhThuc);

}
