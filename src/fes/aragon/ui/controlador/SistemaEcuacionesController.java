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
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class SistemaEcuacionesController extends GeneralController {

	protected int n, m;
	protected double[][] a;
	protected double[] prod, x;
	@FXML
	private Text txtFecha;

	@FXML
	private Text txtHora;

	@FXML
	private TextField txtM;

	@FXML
	private TextField txtN;

	@FXML
	private TextArea txtResultados;

	@FXML
	private TextArea txtTabla;

	@FXML
	private VBox vbxMatriz;

	public void initialize(URL url, ResourceBundle rb) {
		alerta = new Alert(WARNING);
		alerta.setTitle("ADVERTENCIA");
		alerta.setHeaderText("Campo inválido.");
		alerta.setContentText("Algún campo debe estar vacío. Por favor, llénelo.");

		txtFecha.setText(day + "-" + (month) + "-" + year);
		hora(txtHora);
	}

	@FXML
	void eliminacion(ActionEvent event) {

		ObservableList<Node> data = vbxMatriz.getChildren();

		for (int i = 0; i < data.size(); i++) {
			HBox actual = (HBox) data.get(i);
			ObservableList<Node> data2 = actual.getChildren();
			for (int j = 0; j < data2.size(); j++) {
				String texto;
				TextField cuadro = (TextField) data2.get(j);
				do {
					texto = cuadro.getText();
				} while (texto == " ");
				a[i][j] = Double.parseDouble(texto);
			}
		}

		imprimir(n, m, a);

		for (int i = 0; i < n - 1; i++) {
			int p = i;
			if (p <= n && a[p][i] != 0) {
				for (int j = i + 1; j < n; j++) {
					double co = a[j][i] / a[i][i];
					// combinación lineal
					for (int k = 0; k < m; k++) {
						prod[k] = a[i][k] * co;
					}
					// reducción
					for (int k = 0; k < m; k++) {
						a[j][k] = a[j][k] - prod[k];
					}
				}
			} else {
				alerta.setContentText("No hay una solución única.");
				alerta.showAndWait();
			}
		}

		imprimir(n, m, a);

		double cm = a[m - 2][m - 1];
		double amm = a[m - 2][m - 2];
		x[m - 2] = cm / amm;

		for (int i = m - 3; i >= 0; i--) {
			double suma = 0;
			for (int j = m - 2; j > i; j--) {
				suma = suma - a[i][j] * x[j];
			}
			x[i] = (a[i][m - 1] + suma) / a[i][i];
		}

		for (int i = 0; i < x.length; i++) {
			txtResultados.setText(txtResultados.getText() + "X" + (i + 1) + "= " + x[i] + "; ");
		}
	}

	@FXML
	void generar(ActionEvent event) {

		entradas();

		try {
			a = new double[n][m];
			prod = new double[m];
			x = new double[m - 1];

			for (int i = 0; i < n; i++) {
				HBox contenedor = new HBox();
				vbxMatriz.getChildren().add(contenedor);
				for (int j = 0; j < m; j++) {
					TextField cuadro = new TextField();
					contenedor.getChildren().add(cuadro);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
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
	void limpiar(ActionEvent event) {
		clean();
	}
	
	void clean() {
		n = 0;
		m = 0;
		a = new double[0][0];
		prod = new double[0];
		x = new double[0];
		txtM.setText("");
		txtN.setText("");
		txtTabla.setText("");
		txtResultados.setText("");
		vbxMatriz.getChildren().clear();
	}

	@FXML
	void regresar(ActionEvent event) throws IOException {
		navegar(event, INICIO.getPagina());
	}

	@FXML
	void seidal(ActionEvent event) {
		int i = 0;
		double coef = 0;
		boolean iguales = true;

		ObservableList<Node> data = vbxMatriz.getChildren();

		for (i = 0; i < data.size(); i++) {
			HBox actual = (HBox) data.get(i);
			ObservableList<Node> data2 = actual.getChildren();
			for (int j = 0; j < data2.size(); j++) {
				String texto;
				TextField cuadro = (TextField) data2.get(j);
				do {
					texto = cuadro.getText();
				} while (texto == " ");
				a[i][j] = Double.parseDouble(texto);
			}
		}

		imprimir(n, m, a);

		i = 0;
		do {
			iguales = (a[i][i] == a[i + 1][i + 1]) ? true : false;
			i++;
		} while (iguales == true && i < n - 1);

		if (iguales) {
			coef = a[0][0];
		} else {
			System.out.println("Coeficientes de diagonal, diferentes.");
		}

		for (i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				a[i][j] = a[i][j] / coef;
			}
		}

		imprimir(n, m, a);

		iguales = true;
		txtTabla.setText(txtTabla.getText() + "\n");
		do {
			for (i = 0; i < n; i++) {
				prod[i] = x[i];
				double resta = 0;
				txtTabla.setText(txtTabla.getText() + "\n");
				txtTabla.setText(txtTabla.getText() + "X" + i + "= c" + i);
				for (int j = 0; j < m - 1; j++) {
					if (j != i) {
						txtTabla.setText(txtTabla.getText() + "-a" + i + j + "*x" + j);
						resta = resta - a[i][j] * x[j];
					}
					x[i] = a[i][m - 1] + resta;
				}
				iguales = (x[i] == prod[i]) ? true : false;
			}
		} while (!iguales);

		txtTabla.setText(txtTabla.getText() + "\n");
		for (i = 0; i < x.length; i++) {
			txtTabla.setText(txtTabla.getText() + "X" + (i + 1) + "= " + x[i]);
		}

		txtTabla.setText(txtTabla.getText() + "\n");
		for (i = 0; i < x.length; i++) {
			txtTabla.setText(txtTabla.getText() + "PREV" + (i + 1) + "= " + prod[i]);
		}

	}

	@FXML
	void unaraiz(ActionEvent event) throws IOException {
		navegar(event, UNARAIZ.getPagina());
	}

	void imprimir(int n, int m, double[][] a) {
		txtTabla.setText(txtTabla.getText() + "\n");
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				txtTabla.setText(txtTabla.getText() + "a[" + i + "][" + j + "]=" + a[i][j] + " ");
			}
			txtTabla.setText(txtTabla.getText() + "\n");
		}
	}

	void entradas() {
		try {
			n = Integer.parseInt(txtN.getText());
			m = Integer.parseInt(txtM.getText()) + 1;
			if (n < 0 || m < 1)
				throw new Exception("Los campos están vacíos, o alguno es negativo.");
		} catch (Exception e) {
			alerta.setContentText(e.getMessage());
			alerta.showAndWait();
		}
	}
}
