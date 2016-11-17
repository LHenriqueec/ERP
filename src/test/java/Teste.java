import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;

import dao.ConnectionFactory;
import dao.DAOException;
import dao.DaoFactory;
import entity.Produto;

public class Teste  {
	
	@BeforeClass
	public static void init() {
		ConnectionFactory.initSetup();
	}
	
	@Test
	public void teste() throws DAOException {
		Produto p = DaoFactory.getInstance().getProdutoDAO().load("123");
		
		p.getUnMedida().forEach(System.out::println);
		
		DaoFactory.getInstance().getProdutoDAO().salvar(p);
		
		System.out.println(DaoFactory.getInstance().getProdutoDAO().load("123"));
		
	}
}


















