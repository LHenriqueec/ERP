package service;

import java.util.List;

import dao.DAOException;
import dao.DaoFactory;
import dao.GrupoDAO;
import dao.MarcaDAO;
import dao.ProdutoDAO;
import entity.Grupo;
import entity.Marca;
import entity.Produto;

public class ProdutoService {
	
	private ProdutoDAO produtoDAO;
	private MarcaDAO marcaDAO;
	private GrupoDAO grupoDAO;
	private static Produto produtoBinding;
	private Produto other;
	private static ProdutoService instance;

	public ProdutoService() {
		produtoDAO = DaoFactory.getInstance().getProdutoDAO();
		marcaDAO = DaoFactory.getInstance().getMarcaDAO();
		grupoDAO = DaoFactory.getInstance().getGrupoDAO();
		
		if (produtoBinding == null) {
			produtoBinding = new Produto();
		}
	}
	
	public Produto getProduto() {
		return produtoBinding;
	}
	
	public void setProduto(Produto produto) {
		other = produto;
		changeProduto(produtoBinding, other);
	}
	
	public Produto loadByCodigo(String codigo) throws ServiceException {
		try {
			return produtoDAO.load(codigo);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	
	public List<Produto> getProdutos() throws ServiceException {
		try {
			return produtoDAO.getProdutos();
		} catch (DAOException e) {
			throw new ServiceException();
		}
	}
	
	public List<Marca> getMarcas() throws ServiceException {
		try {
			
			return marcaDAO.getMarcas();
			
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	
	public List<Grupo> getGrupos() throws ServiceException {
		try {
			
			return grupoDAO.getGrupos();
		
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	
	public void salvar(Produto produto) throws ServiceException {
		try {
			produtoDAO.salvar(produto);
			
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	
	public void salvarLista(List<Produto> produtos) throws ServiceException {
		try {
		
			produtoDAO.salvarLista(produtos);
		
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	
	public static ProdutoService getService() {
		if (instance == null) {
			instance = new ProdutoService();
		}
		
		return instance;
	}
	
	private void changeProduto(Produto oldProduto, Produto newProduto) {
		oldProduto.setCodigo(newProduto.getCodigo());
		oldProduto.setCusto(newProduto.getCusto());
		oldProduto.setEan(newProduto.getEan());
		oldProduto.setGrupo(newProduto.getGrupo());
		oldProduto.setMarca(newProduto.getMarca());
		oldProduto.setNcm(newProduto.getNcm());
		oldProduto.setNome(newProduto.getNome());
		oldProduto.setPeso(newProduto.getPeso());
		oldProduto.setUnMedida(newProduto.getUnMedida());
	}
	
	public void update(Produto produtoUpdate) {
		changeProduto(other, produtoUpdate);
	}
}
