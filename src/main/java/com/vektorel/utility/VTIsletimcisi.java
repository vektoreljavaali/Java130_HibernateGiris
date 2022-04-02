package com.vektorel.utility;

import com.vektorel.repository.entity.Musteri;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

import java.lang.reflect.Field;
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
        Open();
        /**
         * Boş bir geri dönüş listesi oluşturduk
         */
        List<T> result = null;
        /**
         * İlgili sınıf üzerinden sorgulama yapabilmek için bir kriter
         * oluşturuyoruz. Bu kriter select * from ...... kısmını oluşturuyor.
         * t.getClass() boşluk kısmında ki alanı doldurur.
         */
        Criteria cr = session.createCriteria(t.getClass());
        /**
         * t nin türüne göre geri dönen değeri bir listenin içine atar.
         */
        result = cr.list();
        CloseCommit();
        return result;
    }

    public T findById(long id, T t){
        T result = null;
        Open();
        // select * from .....
        Criteria cr = session.createCriteria(t.getClass());
        // where id=?
        cr.add(Restrictions.eq("id",id));
        // 2 durum var id sini aradığım değer var dır ya da yoktur.
        // kriter ile sorgu yatığında gelen listenin boyutu 0 dan büyük ise
        // işlem yap.
        if(cr.list().size()>0){
            // list.get() -> bir liste içinde ki verilen index numarasından
            // olan kaydı getirir.
            // HATA NEDENİ??? tip uyuşmazlığı
            result = (T)cr.list().get(0);
        }
        return result;
    }

    public List<T> findByColumn(String columnName, String value,boolean isEquals, T t){
        /**
         * -> ad alanı için adı ahmet olanları getir Equals
         * -> ad alanı için adı ah ile başlayanları getir. Like 'ad*'
         */
        List<T> result = null;
        Open();
        Criteria cr = session.createCriteria(t.getClass());
        if(isEquals)
            cr.add(Restrictions.eq(columnName,value));
        else
        /**
         * Ahmet, ahmet, AhmEt,
         * -> select * from tblmusteri where ad like '%ah%'
         * -> select * from tblmusteri where ad ilike '%ah%'
         */
            cr.add(Restrictions.ilike(columnName,"%"+value+"%"));
        result = cr.list();
        return result;
    }

    public List<T> findAnyItem(T t){
        /**
         * Java reflection
         */
        List<T> result = null;
        /**
         * t ile gelen nesnenin sınıf olarak alınmasını sağlıyoruz.         *
         */
         Class cl = t.getClass();
        /**
         * Bu aslında sınıf içinde dğeişken olarak tanımlanmış
         * alanların tümünü bir liste içinde okumamızı sağlar.
         */
        Field[] fields = cl.getDeclaredFields();
        /**
         * For ile tüm değişkenleri tek tek dönüyoruz.
         */
        Open();
        Criteria cr = session.createCriteria(t.getClass());
        try{
            for(int i=0; i<fields.length;i++){
                fields[i].setAccessible(true);
                /**
                 * bir sınıf içinde tanımlanan değişkenlerin adlarını verir.
                 */
                if(fields[i].getType().equals("long")){
                    /**
                     * Gelen değer long tipinde
                     */
                    if((long)fields[i].get(t) > 0){
                        cr.add(Restrictions.eq(fields[i].getName(),(long)fields[i].get(t)));
                    }
                }else if(fields[i].getType().equals("class java.lang.String")){
                    if(fields[i].get(t) != null)
                        cr.add(Restrictions.ilike(fields[i].getName(), "%"+fields[i].get(t)+"%"));
                }

                System.out.println("Alanlar "+i+"...: "+ fields[i].getName());
                System.out.println("Değer...........: "+ fields[i].get(t));
                System.out.println("Type............: "+ fields[i].getType());

            }
        }catch (Exception exception){
            System.out.println("Hata..: "+ exception.toString());
        }
        result = cr.list();
        return result;
    }

    /*
    public List<T> findAll(T t){
        List<T> result=null;
        Open();

         // Verilen sorguyu çalıştıracak
        Query query = session.createQuery("FROM tblmusteri");
               // sorgu neticesinde dönen değeri bir listeye atayacak.

        result = query.getResultList();
        return result;
    }
    */
}
