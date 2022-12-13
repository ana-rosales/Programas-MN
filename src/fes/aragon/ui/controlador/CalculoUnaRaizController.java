package fes.aragon.ui.controlador;

import static javafx.scene.control.Alert.AlertType.WARNING;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

import fes.aragon.modelo.Biseccion;
import fes.aragon.ui.GeneralControlador;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class CalculoUnaRaizController extends GeneralControlador{

    @FXML
    private TextField txtError;

    @FXML
    private Text txtHora;
    
    @FXML
    private Text txtFecha;

    @FXML
    private TextField txtFuncion;

    @FXML
    private TextField txtIteraciones;

    @FXML
    private TextField txtResultado;

    @FXML
    private TextArea txtTabla;

    @FXML
    private TextField txtXf;

    @FXML
    private TextField txtXi;

    public void initialize(URL url, ResourceBundle rb) {
		System.out.println("Algo pasa");
    	alerta = new Alert(WARNING);
		alerta.setTitle("ADVERTENCIA");
		alerta.setHeaderText("Campo inválido, no se puede almacenar.");
		alerta.setContentText("Algún campo debe estar vacío. Por favor, llénelo.");
		
		txtFecha.setText(day + "-" + (month) + "-" + year);
		hora(txtHora);
    }
    
    @FXML
    void biseccion(ActionEvent event) {
		int errno = 0;
		Biseccion current;
		Double sup, inf, err;
		Scanner entrada;
		String expresion, txtSup, txtInf, txtErr;
		
		expresion = 
    }

    @FXML
    void limpiar(ActionEvent event) {

    }

    @FXML
    void newton(ActionEvent event) {

    }

    @FXML
    void newton2(ActionEvent event) {

    }

    @FXML
    void regresar(ActionEvent event) {

    }

    @FXML
    void sistema(ActionEvent event) {

    }

    @FXML
    void vonmises(ActionEvent event) {

    }

}
