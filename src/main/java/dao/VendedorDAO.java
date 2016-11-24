package dao;

import org.hibernate.query.Query;

import entity.Vendedor;

public class VendedorDAO extends DAO<Vendedor> {

	
	public VendedorDAO() {
		super(Vendedor.class);
	}
	
	@SuppressWarnings("unchecked")
	public Vendedor getVendedorByNome(String nome) {
		verifySession();
		
		Query<Vendedor> query = session.createQuery("FROM Vendedor WHERE Nome = '" + nome + "'");
		return query.getResultList().get(0);
	}

}
