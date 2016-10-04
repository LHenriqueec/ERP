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
		session = ConnectionFactory.getSession();
	}
	
	public void salvar(T obj) throws DAOException {
		try {
			
			session.beginTransaction();
			session.save(obj);
			session.getTransaction().commit();
			
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			throw new DAOException(e);
		}
	}
	
	public T load(Serializable id) throws DAOException {
		
		try (Session sess = session) {
			
			return sess.load(clazz, id);
			
		} catch (HibernateException e) {
			throw new DAOException(e);
		}
	}
	
	public void delete(T obj) throws DAOException {
		
		try {
			
			session.beginTransaction();
			session.delete(obj);
			session.getTransaction().commit();
			
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			throw new DAOException(e);
		}
		
	}
	
	public void update(T obj) throws DAOException {
		try {
			
			session.beginTransaction();
			session.update(obj);
			session.getTransaction().commit();
			
			
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			throw new DAOException(e);
		}
	}
	
	
	@SuppressWarnings("rawtypes")
	protected List<?> list(String hql) throws DAOException {
		
		try {
			
			Query query = session.createQuery(hql);
			List<?> list =  query.getResultList();
			
			return list;
			
		} catch (HibernateException e) {
			throw new DAOException(e);
		
		}
	}
}













