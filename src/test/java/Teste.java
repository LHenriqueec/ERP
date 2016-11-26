
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

	@Before
	public void init() {
		ConnectionFactory.initSetup();
	}
	
	@Test
	public void alterar_produto_da_lista_do_DAO_e_salvar_em_Session_deferente() throws DAOException {
		ProdutoDAO dao = DaoFactory.getInstance().getProdutoDAO(); 
		Produto produto = dao.getProdutos().get(2);
		Produto p = new Produto();
		
		p.setCodigo(produto.getCodigo());
		p.setNome(produto.getNome());
		dao.salvar(p);
	}
	
	@Test
	public void alterarItemDeListaDoDAO() throws DAOException {
		
		ProdutoDAO dao = DaoFactory.getInstance().getProdutoDAO();
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
