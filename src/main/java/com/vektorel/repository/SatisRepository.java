package com.vektorel.repository;

import com.vektorel.repository.entity.Musteri;
import com.vektorel.repository.entity.Satis;
import com.vektorel.utility.HibernateUtility;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class SatisRepository {

    private Session session;
    private Transaction transaction;

    private void Open(){
        session = HibernateUtility.getSessionFactory().openSession();
        transaction = session.beginTransaction();
    }
    private void CloseCommit(){
        transaction.commit();
        session.close();
    }
    private void CloseRollBack(){
        transaction.rollback();
        session.close();
    }

    public void save(Satis satis){
        try{
            Open();
            session.save(satis); // Eğer kayıt sırasında sorun olursa rollback yap
            CloseCommit();
        }catch (Exception exception){
            CloseRollBack();
        }
    }
    public void update(Satis satis){
        try{
            Open();
            session.update(satis); // Eğer kayıt sırasında sorun olursa rollback yap
            CloseCommit();
        }catch (Exception exception){
            CloseRollBack();
        }
    }
    public void delete(Satis satis){
        try{
            Open();
            session.delete(satis); // Eğer kayıt sırasında sorun olursa rollback yap
            CloseCommit();
        }catch (Exception exception){
            CloseRollBack();
        }
    }
    public List<Satis> findAll(){
        List<Satis> result=null;
        Open();
        /**
         * Verilen sorguyu çalıştıracak
         */
        Query query = session.createQuery("FROM tblsatis");
        /**
         * sorgu neticesinde dönen değeri bir listeye atayacak.
         */
        result = query.getResultList();
        return result;
    }
}
