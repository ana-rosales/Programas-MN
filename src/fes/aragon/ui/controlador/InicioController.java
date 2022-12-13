package fes.aragon.ui.controlador;

import static fes.aragon.ui.PaginasFXML.*;
import static javafx.scene.control.Alert.AlertType.WARNING;
import static javafx.scene.control.ButtonType.OK;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import fes.aragon.ui.GeneralController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class InicioController extends GeneralController implements Initializable{

	@FXML
	private Text txtFecha;

	@FXML
	private Text txtHora;

	@FXML
	private VBox vbxRaiz;

	@FXML
	private VBox vbxSistema;

	@FXML
	private VBox vbxCreditos;

	@FXML
	private Button btnCreditos;

	public void initialize(URL url, ResourceBundle rb) {
		alerta = new Alert(WARNING);
		alerta.setTitle("ADVERTENCIA");
		alerta.setHeaderText("Campo inválido.");
		alerta.setContentText("Algún campo debe estar vacío. Por favor, llénelo.");

		txtFecha.setText(day + "-" + (month) + "-" + year);
		hora(txtHora);
	}

	@FXML
	void creditos(ActionEvent event) {
		if (btnCreditos.getText() == "Creditos") {
			btnCreditos.setText("Temas");
			vbxCreditos.setDisable(false);
			vbxRaiz.setDisable(true);
			vbxSistema.setDisable(true);
		} else {
			btnCreditos.setText("Creditos");
			vbxCreditos.setDisable(true);
			vbxRaiz.setDisable(false);
			vbxSistema.setDisable(false);
		}
	}

	@FXML
	void salir(ActionEvent event) {
		alerta.setHeaderText("Quiere salir?");
		alerta.setContentText("¿Está seguro de querer salir?");
		Optional<ButtonType> resultado = alerta.showAndWait();
		if (resultado.get().equals(OK)) {
			Platform.exit();
		}
	}

	@FXML
	void sistema(ActionEvent event) throws IOException {
		navegar(event, SISTEMA.getPagina());
	}

	@FXML
	void unaraiz(ActionEvent event) throws IOException {
		navegar(event, UNARAIZ.getPagina());
	}

}
