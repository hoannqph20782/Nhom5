/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.math.BigDecimal;

public class Size2 {
    private Integer Id;
    private String ma;
    private BigDecimal kichCo;
    private int trangThai;

    public Size2() {
    }

    public Size2(Integer Id, String ma, BigDecimal kichCo, int trangThai) {
        this.Id = Id;
        this.ma = ma;
        this.kichCo = kichCo;
        this.trangThai = trangThai;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer Id) {
        this.Id = Id;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public BigDecimal getKichCo() {
        return kichCo;
    }

    public void setKichCo(BigDecimal kichCo) {
        this.kichCo = kichCo;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }
    
}
