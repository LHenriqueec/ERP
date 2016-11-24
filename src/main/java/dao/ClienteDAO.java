package dao;

import java.util.List;

import org.hibernate.query.Query;

import entity.Cliente;

public class ClienteDAO extends DAO<Cliente> {

	public ClienteDAO() {
		super(Cliente.class);
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Cliente> getClientes() throws DAOException {
		return (List<Cliente>) list("FROM Cliente");
	}


	@SuppressWarnings("unchecked")
	public Cliente getClienteByName(String name) {
		verifySession();
		Query<Cliente> query = session.createQuery("FROM Cliente WHERE razao = '" + name + "'");
		return query.getSingleResult();
	}

}
