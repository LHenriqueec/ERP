package dao;

import java.util.Set;

import entity.UnidadeMedida;

public class UnidadeMedidaDAO extends DAO<UnidadeMedida> {

	private int count;

	public UnidadeMedidaDAO() {
		super(UnidadeMedida.class);
	}

	public void salvarLista(Set<UnidadeMedida> unidadeMedidas) {

		int batch = 5;
		session.beginTransaction();
		unidadeMedidas.forEach(um -> {
			countIncrement();
			session.persist(um);
			if (count > 0 && count % batch == 0) {
				session.flush();
				session.clear();
			}
		});
		session.getTransaction().commit();
	}

	private void countIncrement() {
		count++;
	}
}
