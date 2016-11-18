package service;

import java.util.List;

import dao.DAOException;
import dao.DaoFactory;
import dao.ProdutoDAO;
import entity.Grupo;
import entity.Marca;
import entity.Produto;

public class ProdutoService {
	
	private ProdutoDAO dao;
	private static Produto produto;

	public ProdutoService() {
		dao = DaoFactory.getInstance().getProdutoDAO();
		
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
			return dao.getProdutos();
		} catch (DAOException e) {
			throw new ServiceException();
		}
	}
	
	public List<Marca> getMarcas() throws ServiceException {
		try {
			
			return DaoFactory.getInstance().getMarcaDAO().getMarcas();
			
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	
	public List<Grupo> getGrupos() throws ServiceException {
		try {
			
			return DaoFactory.getInstance().getGrupoDAO().getGrupos();
		
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	
	public void salvar(Produto produto) throws ServiceException {
		try {
			DaoFactory.getInstance().getProdutoDAO().salvar(produto);
			
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	
	public void salvarLista(List<Produto> produtos) throws ServiceException {
		try {
		
			dao.salvarLista(produtos);
		
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
}
