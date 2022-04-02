package com.vektorel;

import com.vektorel.repository.MusteriRepository;
import com.vektorel.repository.entity.Musteri;
import com.vektorel.utility.HibernateUtility;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class Runner {
    public static void main(String[] args) {
        /*
        Kayıt V.1
        Musteri mst = new Musteri();
        mst.setAd("Ahmet");
        mst.setSoyad("KONUK");
        mst.setAdres("İzmir");
        MusteriRepository dbmusteri = new MusteriRepository();
        dbmusteri.save(mst);
         */

       // new MusteriRepository().save(new Musteri("Deniz","TAŞ","samsun"));
       // new MusteriRepository().save(new Musteri("Bahar","KONUK","istanbul"));
       // new MusteriRepository().save(new Musteri("Ayşe","TEKİN","trabzon"));
        /**
         * foreach -> iterable bir liste içindeki değerlere tek tek ulaşır
         * her seferinde liste/array içindeki bir değere ulaşır. döngü listenin
         * sonu gelinceye kadar devam eder.
         * Array -> String[] sinifListesi = new String[15];
         * Liste-> List<String> sinifListesi = new ArrayList();
         *
         * Öncelikle yeni bir repository nesnesi oluşturuyorum.
         * findAll ile tüm listeyi çekmek istiyorum.
         * DİKKAT!!! findall içinde new Musteri() şeklinde bir kullanım var
         * neden??? ilgili method sorgulama yapabilmek için gerekli olan
         * sınıf öğreniği alsın diye boş bir nesne veriyorum.
         */
        ///MusteriRepository dbmusteri = new MusteriRepository();
        ///dbmusteri.findAll()

        Musteri musteri = new Musteri();
        musteri.setAd("Ah");
        musteri.setAdres("k");




        Musteri mstById  = new MusteriRepository().findById(3L,new Musteri());
        System.out.println("id ile aranan müşteri");
        System.out.println("Müşteri id..........: "+ mstById.getId());
        System.out.println("Müşteri ad..........: "+ mstById.getAd());
        System.out.println("Müşteri soyad.......: "+ mstById.getSoyad());
        System.out.println("Müşteri adresi......: "+ mstById.getAdres());
        System.out.println("------------------------------------------");

       List<Musteri> mstByName  = new MusteriRepository()
                .findByColumn("ad","ayşe",false,new Musteri());
        System.out.println("adres te a harfi geçen müşteriler");
       for (Musteri mst: mstByName ) {
            System.out.println("Müşteri id..........: "+ mst.getId());
            System.out.println("Müşteri ad..........: "+ mst.getAd());
            System.out.println("Müşteri soyad.......: "+ mst.getSoyad());
            System.out.println("Müşteri adresi......: "+ mst.getAdres());
            System.out.println("------------------------------------------");
        }


        for(Musteri mst : new MusteriRepository().findAll(new Musteri())){
            System.out.println("Müşteri id..........: "+ mst.getId());
            System.out.println("Müşteri ad..........: "+ mst.getAd());
            System.out.println("Müşteri soyad.......: "+ mst.getSoyad());
            System.out.println("Müşteri adresi......: "+ mst.getAdres());
            System.out.println("------------------------------------------");
        }
    }

    private static void SaveHibernate(){

        /**
         * Hibernate ile Veri Eklemek
         * 1- Bağlanıyı Aç (Oturum Olşuturmak, Başlatmak)
         * hibernate yönetici dosyası olan utility, bağlantıyı açar ve yeni bir
         * oturum olşuturur.
         */
        Session session = HibernateUtility.getSessionFactory().openSession();
        /**
         * 2- Transaction oluştur. Ve sistemi izlemeye başla.
         */
        Transaction transaction = session.beginTransaction();
        Musteri mst = new Musteri("Muhammet","Hoca","Ankara");
        session.save(mst);
        transaction.commit();
        session.close();
    }
}
