package fes.aragon.ui.controlador;

import static javafx.scene.control.Alert.AlertType.WARNING;
import static javafx.scene.control.ButtonType.OK;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Scanner;

import fes.aragon.modelo.Biseccion;
import fes.aragon.modelo.Funcion;
import fes.aragon.modelo.NRaphson;
import fes.aragon.modelo.NRaphson2;
import fes.aragon.modelo.VonMisses;
import fes.aragon.ui.GeneralControlador;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class CalculoUnaRaizController extends GeneralControlador {

	protected Double x0, xi, xf, err;
	protected String funcion;
	protected int errno = 0;
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
	void biseccion(ActionEvent event) {
		clean();
		comenzar();
		Biseccion current;

		entradas(true);

		try {
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
						txtTabla.setText(txtTabla.getText() + "\ni = " + current.iteraciones().get() + "\t|\tRaíz= "
								+ current.raiz().get() + "\t|\tXi= " + current.sup().get() + "\t|\tXf= "
								+ current.inf().get() + "\t|\tError= " + current.errRaiz().get());
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

			salida(current);
		} catch (Exception e) {
			errno = 0;
		}
	}

	@FXML
	void limpiar(ActionEvent event) {
		clean();
	}
	
	void clean() {
		xi = 0.0;
		xf = 0.0;
		err = 0.0;
		funcion = "";
		txtError.setText(" ");
		txtFuncion.setText(" ");
		txtIteraciones.setText(" ");
		txtResultado.setText(" ");
		txtTabla.setText(" ");
		txtXf.setText(" ");
		txtXi.setText(" ");
	}

	@FXML
	void newton(ActionEvent event) {
		clean();
		comenzar();
		NRaphson current;
		entradas(false);
		try {
			current = new NRaphson(funcion, xi, err);

			if (current.f(xi) == 0) {
				current.raiz().set(xi);
				txtTabla.setText("El punto inicial indicado, es la raíz :)");
			} else {
				if (current.df(xi) != 0) {
					int i = 0;
					do {
						current.siguiente();
						current.iterar();
						current.setDelta();
						current.xi().set(current.xs().get());
						txtTabla.setText(txtTabla.getText() + "\n i = " + current.iteraciones().get() + "\t|\tXi= "
								+ current.xi().get() + "\t|\tXs= " + current.xs().get() + "\t|\tError= "
								+ current.delta().get());
						i++;
					} while (current.delta().get() > err);
					current.raiz().set(current.xi().get());
				} else {
					alerta.setContentText("La derivada con respecto al punto indicado es nula.");
					errno++;
				}
			}

			salida(current);
		} catch (Exception e) {
			errno = 0;
		}
	}

	@FXML
	void newton2(ActionEvent event) {
		clean();
		comenzar();
		NRaphson2 current;
		entradas(false);

		try {
			current = new NRaphson2(funcion, xi, err);

			if (current.f(xi) == 0) {
				current.raiz().set(xi);
				txtTabla.setText("El punto inicial indicado, es la raíz :)");
			} else {
				if (current.df(xi) != 0) {
					int i = 0;
					do {
						current.siguiente();
						current.iterar();
						current.setDelta();
						current.xi().set(current.xs().get());
						txtTabla.setText(txtTabla.getText() + "\n i = " + current.iteraciones().get() + "\t|\tXi= "
								+ current.xi().get() + "\t|\tXs= " + current.xs().get() + "\t|\tError= "
								+ current.delta().get());
						i++;
					} while (current.delta().get() > err);
					current.raiz().set(current.xi().get());
				} else {					
					alerta.setContentText("La derivada con respecto al punto indicado es nula.");
					errno++;
				}
			}

			salida(current);
		} catch (Exception e) {
			errno = 0;
		}

	}

	@FXML
	void regresar(ActionEvent event) {

	}

	@FXML
	void sistema(ActionEvent event) {

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
	void vonmises(ActionEvent event) {
		clean();
		comenzar();
		VonMisses current;
		entradas(false);

		try {
			x0 = xi;
			current = new VonMisses(funcion, x0, x0, err);
			
			if (current.f(xi) == 0) {
				current.raiz().set(xi);
				txtTabla.setText("El punto inicial indicado, es la raíz :)");
			} else {
				if (current.df(xi) != 0) {
					int i = 0;
					do {
						current.siguiente();
						current.iterar();
						current.setDelta();
						current.xi().set(current.xs().get());
						txtTabla.setText(txtTabla.getText()
								+ "\n i = " + current.iteraciones().get()
								+ "\t|\tXi= " + current.xi().get()
								+ "\t|\tXs= " + current.xs().get()
								+ "\t|\tdX0= " + current.dx0().get()
								+ "\t|\tError= " + current.delta().get());
						i++;
					} while (current.delta().get() > err);
					current.raiz().set(current.xi().get());
				} else {					
					alerta.setContentText("La derivada con respecto al punto indicado es nula.");
					errno++;
				}
			}
			salida(current);
		} catch (Exception e) {
			errno = 0;
		}
	}

	void entradas(boolean biseccion) {
		try {
			funcion = txtFuncion.getText();
			if (funcion.equals(""))
				throw new Exception("Algún campo está vacío.");
			xi = Double.parseDouble(txtXi.getText());
			if (biseccion)
				xf = Double.parseDouble(txtXf.getText());
			err = Double.parseDouble(txtError.getText());
			if (err <= 0)
				throw new Exception("El margen de error debe ser mayor a cero.");
		} catch (Exception e) {
			alerta.setContentText(e.getMessage());
			alerta.showAndWait();
			errno++;
		}
	}

	void salida(Funcion current) {
		if (errno == 0) {
			txtResultado.setText(current.raiz().get() + "");
			txtIteraciones.setText(current.iteraciones().get() + "");
		} else {
			errno = 0;
		}
	}
}
