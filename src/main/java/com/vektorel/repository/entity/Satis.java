package com.vektorel.repository.entity;

import javax.persistence.*;

/**
 * DİKAAAAAAAAAT !!!!!
 * Eğer yeni bir entity eklemiş iseniz bunu
 * veritabanına yansıması için hibernate.cfg
 * dosyasına eklemeniz gereklidir.
 */
@Entity
@Table(name = "tblsatis")
public class Satis {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    long musteriid;
    long satistarihi;
    double fiyat;
    int kdv;
    double toplamfiyat;

    public Satis() {
    }

    public Satis(long id, long musteriid, long satistarihi, double fiyat, int kdv, double toplamfiyat) {
        this.id = id;
        this.musteriid = musteriid;
        this.satistarihi = satistarihi;
        this.fiyat = fiyat;
        this.kdv = kdv;
        this.toplamfiyat = toplamfiyat;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getMusteriid() {
        return musteriid;
    }

    public void setMusteriid(long musteriid) {
        this.musteriid = musteriid;
    }

    public long getSatistarihi() {
        return satistarihi;
    }

    public void setSatistarihi(long satistarihi) {
        this.satistarihi = satistarihi;
    }

    public double getFiyat() {
        return fiyat;
    }

    public void setFiyat(double fiyat) {
        this.fiyat = fiyat;
    }

    public int getKdv() {
        return kdv;
    }

    public void setKdv(int kdv) {
        this.kdv = kdv;
    }

    public double getToplamfiyat() {
        return toplamfiyat;
    }

    public void setToplamfiyat(double toplamfiyat) {
        this.toplamfiyat = toplamfiyat;
    }
}
