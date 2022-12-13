package fes.aragon.ui.controlador;

import static javafx.scene.control.Alert.AlertType.WARNING;

import fes.aragon.ui.GeneralControlador;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class SistemaEcuacionesController extends GeneralControlador{

    @FXML
    private Text txtFecha;

    @FXML
    private Text txtHora;

    @FXML
    private TextField txtM;

    @FXML
    private TextField txtN;

    @FXML
    private ScrollPane txtResultados;

    @FXML
    private TextArea txtTabla;

    void initialize() {
		alerta = new Alert(WARNING);
		alerta.setTitle("ADVERTENCIA");
		alerta.setHeaderText("Campo inválido.");
		alerta.setContentText("Algún campo debe estar vacío. Por favor, llénelo.");

		txtFecha.setText(day + "-" + (month) + "-" + year);
		hora(txtHora);
	}

	void comenzar() {
		initialize();
	}
	
    @FXML
    void eliminacion(ActionEvent event) {
    	comenzar();
    }

    @FXML
    void gaussjordan(ActionEvent event) {
    	comenzar();

    }

    @FXML
    void generar(ActionEvent event) {

    }

    @FXML
    void inversa(ActionEvent event) {
    	comenzar();

    }

    @FXML
    void limpiar(ActionEvent event) {

    }

    @FXML
    void regresar(ActionEvent event) {

    }

    @FXML
    void seidal(ActionEvent event) {
    	comenzar();

    }

    @FXML
    void unaraiz(ActionEvent event) {

    }
    
    void imprimir(int n, int m, double[][] a) {
		System.out.println();
		for (int i = 0; i<n;i++) {
			for(int j = 0; j<m;j++) {
				System.out.print("a[" + i + "]["+ j +"]=" + a[i][j] + " ");
			}
			System.out.println();
		}
	}
}
