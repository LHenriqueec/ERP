package controller;

import entity.Grupo;
import entity.Marca;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import service.ProdutoService;
import service.ServiceException;
import util.CreateViewUtil;
import util.StageGroup;

public class NovoProdutoController extends Controller {

	private ProdutoService service;
	
	@FXML
	private ComboBox<Grupo> cmbGrupo;
	@FXML
	private ComboBox<Marca> cmbMarca;

	@FXML
	private void initialize() throws ServiceException {
		service = new ProdutoService();
		
		cmbMarca.getItems().setAll(service.getMarcas());
		cmbGrupo.getItems().setAll(service.getGrupos());
	}

	@FXML
	private void onNewMarca() throws ServiceException {
		CreateViewUtil.createViewByNode(getScreen("Nova_Marca"), "Nova Marca", "Marca");
		cmbMarca.getItems().setAll(service.getMarcas());
	}

	@FXML
	private void onNewGrupo() throws ServiceException {
		CreateViewUtil.createViewByNode(getScreen("Novo_Grupo"), "Novo Grupo", "Grupo");
		cmbGrupo.getItems().setAll(service.getGrupos());
	}

	@FXML
	private void onCancelar() {
		StageGroup.finishStage("ProdutoNovo");
	}
}