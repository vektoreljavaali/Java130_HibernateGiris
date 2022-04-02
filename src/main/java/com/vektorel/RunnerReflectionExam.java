package com.vektorel;

import com.vektorel.repository.MusteriRepository;
import com.vektorel.repository.SatisRepository;
import com.vektorel.repository.entity.Musteri;
import com.vektorel.repository.entity.Satis;

public class RunnerReflectionExam {
    public static void main(String[] args) {
        Musteri mst = new Musteri();
        Satis satis = new Satis();
        mst.setAd("Ahmet");
        mst.setAdres("Ahmet");


        new MusteriRepository().findAnyItem(mst);
      //  new SatisRepository().findAnyItem(satis);
    }
}
