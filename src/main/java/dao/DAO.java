package dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;


public class DAO<T> {

	protected Session session;
	private Class<T> clazz;
	
	public DAO(Class<T> clazz) {
		this.clazz = clazz;
	}
	
	public void salvar(T obj) throws DAOException {
		try {
			verifySession();
			session.beginTransaction();
			session.saveOrUpdate(obj);
			session.getTransaction().commit();
			
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			throw new DAOException(e);
		} finally {
			session.close();
		}
	}
	
	public T load(Serializable id) throws DAOException {
		
		try {
			verifySession();
			return session.load(clazz, id);
			
		} catch (HibernateException e) {
			throw new DAOException(e);
		}
	}
	
	public void delete(T obj) throws DAOException {
		
		try {
			verifySession();
			session.beginTransaction();
			session.delete(obj);
			session.getTransaction().commit();
			
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			throw new DAOException(e);
		
		} finally {
			session.close();
		}
		
	}
	
	public void update(T obj) throws DAOException {
		try {
			verifySession();
			session.beginTransaction();
			session.update(obj);
			session.getTransaction().commit();
			
			
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			throw new DAOException(e);
		
		} finally {
			session.close();
		}
	}
	
	
	@SuppressWarnings("rawtypes")
	protected List<?> list(String hql) throws DAOException {
		
		try {
			verifySession();
			Query query = session.createQuery(hql);
			List<?> list =  query.getResultList();
			return list;
			
		} catch (HibernateException e) {
			throw new DAOException(e);
		
		}
	}
	
	private void verifySession() {
		if (session == null) {
			session = ConnectionFactory.getSession();
		}
	}
}













