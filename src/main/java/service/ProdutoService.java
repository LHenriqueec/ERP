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
	private static Produto produto;

	public ProdutoService() {
		produtoDAO = DaoFactory.getInstance().getProdutoDAO();
		marcaDAO = DaoFactory.getInstance().getMarcaDAO();
		grupoDAO = DaoFactory.getInstance().getGrupoDAO();
		
		if (produto == null) {
			produto = new Produto();
		}
	}
	
	public Produto getProduto() {
		return produto;
	}
	
	public void setProduto(Produto produto) {
		ProdutoService.produto.setCodigo(produto.getCodigo());
		ProdutoService.produto.setNome(produto.getNome());
		ProdutoService.produto.setNcm(produto.getNcm());
		ProdutoService.produto.setEan(produto.getEan());
		ProdutoService.produto.setPeso(produto.getPeso());
		ProdutoService.produto.setCusto(produto.getCusto());
		ProdutoService.produto.setGrupo(produto.getGrupo());
		ProdutoService.produto.setMarca(produto.getMarca());
		ProdutoService.produto.setUnMedida(produto.getUnMedida());
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
}
