package com.vektorel;

import com.vektorel.repository.entity.Musteri;
import com.vektorel.utility.HibernateUtility;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class Runner {
    public static void main(String[] args) {

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
