package service;


import dao.DAOException;
import dao.DaoFactory;
import dao.GrupoDAO;
import entity.Grupo;

public class GrupoService {
	
	private GrupoDAO grupoDAO;
	
	public GrupoService() {
		grupoDAO = DaoFactory.getInstance().getGrupoDAO();
	}

    
    public void salvar(Grupo grupo) throws ServiceException {
    	try {
    		
    		grupoDAO.salvar(grupo);
    		
    	} catch (DAOException e) {
    		throw new ServiceException(e);
    	}
    }
}












