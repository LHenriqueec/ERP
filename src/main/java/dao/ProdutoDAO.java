package dao;

import java.util.List;

import org.hibernate.HibernateException;

import entity.Produto;

public class ProdutoDAO extends DAO<Produto> {

	
	public ProdutoDAO() {
		super(Produto.class);
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Produto> getProdutos() throws DAOException {
		return (List<Produto>) list("FROM Produto");
	}
	
	public void salvarLista(List<Produto> produtos) throws DAOException {
		try {
			
			int batch = 50;
			
			session.beginTransaction();
			for (int i = 0; i < produtos.size(); i++) {
				Produto produto = produtos.get(i);
				session.persist(produto);
				
				if (i > 0 && i % batch == 0) {
					session.flush();
					session.clear();
				}
			}
			session.getTransaction().commit();
		
		} catch(HibernateException e) {
			throw new DAOException(e);
		}
	}
}






