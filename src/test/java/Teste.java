
import java.util.List;

import org.hibernate.Session;
import org.junit.Before;
import org.junit.Test;

import dao.ConnectionFactory;
import dao.DAOException;
import dao.DaoFactory;
import dao.ProdutoDAO;
import entity.Produto;


public class Teste {
	
	private Session session;
	private ProdutoDAO dao;
	
	
	@Before
	public void init() {
		ConnectionFactory.initSetup();
		dao = DaoFactory.getInstance().getProdutoDAO();
	}
	
	@Test
	public void salvar_duas_vezes_o_mesmo_objeto() throws DAOException {
		Produto p = dao.load("123");
		
		dao.salvar(p);
		dao.salvar(p);
	}
	
	@Test
	public void alterarItemDeListaDoDAO() throws DAOException {
		List<Produto> produtos = dao.getProdutos();
		
		Produto produto = produtos.get(2);
		
		produto.setNome("TESTE PRODUTO");
		
		dao.salvar(produto);
	}
	
	@Test
	public void testeMuitasSessions() throws DAOException {
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
