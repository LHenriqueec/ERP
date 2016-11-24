
import org.hibernate.Session;
import org.junit.Before;
import org.junit.Test;

import dao.ConnectionFactory;
import dao.DAOException;
import entity.Produto;


public class Teste {
	
	private Session session;

	@Before
	public void init() {
		ConnectionFactory.initSetup();
	}
	
	@Test
	public void teste() {
		
	}
	
	@Test
	public void TesteMuitasSessions() throws DAOException {
		session = ConnectionFactory.getSession();
		Produto p = session.load(Produto.class, "123");
		
		p.setNome("TESTE PRODUTO");
		
		if (session == null) {
			session = ConnectionFactory.getSession();
		}
		session.beginTransaction();
		session.saveOrUpdate(p);
		session.getTransaction().commit();
		
		System.out.println(p);
	}
}
