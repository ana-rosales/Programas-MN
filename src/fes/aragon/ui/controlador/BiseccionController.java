package fes.aragon.ui.controlador;

import static javafx.scene.control.Alert.AlertType.WARNING;
import static javafx.scene.control.ButtonType.OK;

import java.util.Optional;
import java.util.Scanner;

import fes.aragon.modelo.Biseccion;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

public class BiseccionController {
	int errno = 0;
	Biseccion current;
	Double sup, inf, err;
	String expresion, txtSup, txtInf, txtErr;
	Alert alerta;
	
    @FXML
    private TextField txtError;

    @FXML
    private TextField txtFuncion;

    @FXML
    private TextField txtInferior;

    @FXML
    private TextField txtIteraciones;

    @FXML
    private TextField txtResultado;

    @FXML
    private TextField txtSuperior;

    @FXML
    void calcular(ActionEvent event) {
    	expresion = txtFuncion.getText();
    	txtSup = txtSuperior.getText();
    	txtInf = txtInferior.getText();
    	txtErr = txtError.getText();
    	
		if (expresion.equals("") || txtSup.equals("") || txtInf.equals("") || txtErr.equals("")) {
			System.out.println("Campos vacíos.");
			errno++;
			return;
		}
		
		sup = Double.parseDouble(txtSup);
		inf = Double.parseDouble(txtInf);
		err = Double.parseDouble(txtErr);

		current = new Biseccion(expresion, inf, sup);
		current.acomodarLimites();
		
		try {
			if (current.f(inf) * current.f(sup) < 0) {
				do {
					current.setRaiz();
					current.setProducto();
					if (current.prod().get() < 0) {
						current.sup().set(current.raiz().get());
					} else if (current.prod().get() > 0) {
						current.inf().set(current.raiz().get());
					}
					current.setErrRaiz();
					current.iterar();
				} while (current.errRaiz().get() >= err);
			} else {
				alerta = new Alert(WARNING);
				alerta.setTitle("ADVERTENCIA");
				alerta.setHeaderText("Raíz inexistente.");
				alerta.setContentText("Entre los límites indicados, no existe raíz.");
				errno++;
			}
		} catch (Exception e) {
			alerta = new Alert(WARNING);
			alerta.setTitle("ADVERTENCIA");
			alerta.setHeaderText("Error en la expresión ingresada.");
			alerta.setContentText(current.error().get());
			errno++;
		}
		
		if (errno == 0) {
			txtResultado.setText(current.raiz().get() + "");
			txtIteraciones.setText(current.iteraciones().get() + "");
		} else {
			errno = 0;
		}
    }

    @FXML
    void limpiar(ActionEvent event) {
    	txtFuncion.setText(" ");
    	txtSuperior.setText(" ");
    	txtInferior.setText(" ");
    	txtError.setText(" ");
    	txtResultado.setText(" ");
    	txtIteraciones.setText(" ");
    }

    @FXML
    void salir(ActionEvent event) {
		Optional<ButtonType> resultado = alerta.showAndWait();
		if (resultado.get().equals(OK)) {
			Platform.exit();
		}
    }

}
