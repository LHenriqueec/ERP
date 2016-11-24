package service;

import dao.DAOException;
import dao.DaoFactory;
import dao.MarcaDAO;
import entity.Marca;

public class MarcaService {

	private MarcaDAO marcaDAO;
	
	public MarcaService() {
		marcaDAO = DaoFactory.getInstance().getMarcaDAO();
	}

	public void salvar(Marca marca) throws ServiceException {
		try {
			
			marcaDAO.salvar(marca);
			
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
}
