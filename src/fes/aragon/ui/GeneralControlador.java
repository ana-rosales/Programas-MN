package fes.aragon.ui;

import java.util.Calendar;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.text.Text;

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
}
