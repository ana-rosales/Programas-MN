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

public class CalculoUnaRaizController extends GeneralControlador {

	protected Double xi, xf, err;
	protected String funcion;
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

	public void initialize() {
		alerta = new Alert(WARNING);
		alerta.setTitle("ADVERTENCIA");
		alerta.setHeaderText("Campo inválido.");
		alerta.setContentText("Algún campo debe estar vacío. Por favor, llénelo.");

		txtFecha.setText(day + "-" + (month) + "-" + year);
		hora(txtHora);
	}

	public void comenzar() {
		initialize();
	}

	@FXML
	void biseccion(ActionEvent event) {
		// initialize();
		int errno = 0;
		Biseccion current;

		try {
			funcion = txtFuncion.getText();
			if (funcion.equals(""))
				throw new Exception("Algún campo está vacío.");
			xi = Double.parseDouble(txtXi.getText());
			xf = Double.parseDouble(txtXf.getText());
			err = Double.parseDouble(txtError.getText());
		} catch (Exception e) {
			alerta.setContentText(e.getMessage());
			alerta.showAndWait();
			errno++;
		}

		current = new Biseccion(funcion, xi, xf);
		current.acomodarLimites();

		try {
			if (current.f(xf) * current.f(xi) < 0) {
				do {
					current.setRaiz();
					current.setProducto();
					if (current.prod().get() < 0) {
						current.sup().set(current.raiz().get());
					} else if (current.prod().get() > 0) {
						current.inf().set(current.raiz().get());
					}
					current.setErrRaiz();
					txtTabla.setText(txtTabla.getText() 
							+ "\ni = " + current.iteraciones().get()
							+ "\t|\tRaíz= " + current.raiz().get()
							+ "\t|\tXi= " + current.sup().get()
							+ "\t|\tXf= " + current.inf().get()
							+ "\t|\tError= " + current.error().get());
					current.iterar();
				} while (current.errRaiz().get() >= err);
			} else {
				alerta.setContentText("Entre los limites indicados, no existe raiz.");
				errno++;
			}
		} catch (Exception e) {
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
		xi = 0.0;
		xf = 0.0;
		err = 0.0;
		funcion = "";
		txtError.setText(" ");
		txtHora.setText(" ");
		txtFecha.setText(" ");
		txtFuncion.setText(" ");
		txtIteraciones.setText(" ");
		txtResultado.setText(" ");
		txtTabla.setText(" ");
		txtXf.setText(" ");
		txtXi.setText(" ");
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
