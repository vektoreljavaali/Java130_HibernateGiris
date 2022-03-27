package com.vektorel.utility;

import com.vektorel.repository.entity.Musteri;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

/**
 * JAva Generic Types
 */
public class VTIsletimcisi<T> {

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

    public void save(T t){
        try{
            Open();
            session.save(t); // Eğer kayıt sırasında sorun olursa rollback yap
            CloseCommit();
        }catch (Exception exception){
            CloseRollBack();
        }
    }
    public void update(T t){
        try{
            Open();
            session.update(t); // Eğer kayıt sırasında sorun olursa rollback yap
            CloseCommit();
        }catch (Exception exception){
            CloseRollBack();
        }
    }
    public void delete(T t){
        try{
            Open();
            session.delete(t); // Eğer kayıt sırasında sorun olursa rollback yap
            CloseCommit();
        }catch (Exception exception){
            CloseRollBack();
        }
    }
    public List<T> findAll(T t){
        List<T> result=null;
        Open();
        /**
         * Verilen sorguyu çalıştıracak
         */
       // Criteria cr = session.createCriteria(t.getClass());
       // result = cr.list();

        Query query = session.createQuery("FROM tblmusteri");
        /**
         * sorgu neticesinde dönen değeri bir listeye atayacak.
         */
        result = query.getResultList();
        return result;
    }

}
