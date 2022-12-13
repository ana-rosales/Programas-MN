package fes.aragon.ui;

import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GeneralControlador {
	protected Alert alerta;
	Calendar cale = Calendar.getInstance();
	protected int year = cale.get(Calendar.YEAR);
	protected int month = cale.get(Calendar.MONTH);
	protected int day = cale.get(Calendar.DAY_OF_MONTH);

	public static void hora(Text txtHora) {
		Thread clock = new Thread(() -> {
			while (true) {
				Calendar cal = Calendar.getInstance();
				int second = cal.get(Calendar.SECOND);
				int minute = cal.get(Calendar.MINUTE);
				int hour = cal.get(Calendar.HOUR);
				int digitosS = (int) (Math.log10(second) + 1);
				int digitosM = (int) (Math.log10(minute) + 1);
				int digitosH = (int) (Math.log10(hour) + 1);
				Platform.runLater(() -> {
					txtHora.setText(
							((digitosH == 1) ? "0" + hour : hour) + ":" + ((digitosM == 1) ? "0" + minute : minute)
									+ ":" + ((digitosS == 1) ? "0" + second : second));
				});

				try {
					Thread.sleep(1000);
				} catch (InterruptedException ex) {

				}
			}
		});
		clock.start();
	}
	
	// paginación
	protected void navegar(Event event, URL fxmlDocName) throws IOException {
		// Loading new fxml UI document
		Parent pageParent = FXMLLoader.load(fxmlDocName);
		// Creating new scene
		Scene scene = new Scene(pageParent);
		// get current stage
		Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		// Hide old stage
		appStage.hide(); // Optional
		// Set stage with new Scene
		appStage.setScene(scene);
		// Show up the stage
		appStage.show();
	}
}
