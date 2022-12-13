package fes.aragon.inicio;

public class InversaInicio {

	public static double detLaplace(double[][] matriz) {

		double det = 0;

		if (matriz.length == 1) {

			det = matriz[0][0];
		} else {

			for (int i = 0; i < matriz[0].length; i++) {

				det += matriz[0][i] * Math.pow(-1, (0 + i)) * detLaplace(adjunta(0, i, matriz));
			}
		}

		return det;
	}

	public static double[][] inversa(double matriz[][]) throws Exception {

		double det = detLaplace(matriz);

		if (det == 0) {
			throw new Exception("Determinante igual a 0");
		}

		double[][] AUX = new double[matriz.length][matriz[0].length];

		for (int i = 0; i < matriz.length; i++) {

			for (int j = 0; j < matriz[i].length; j++) {

				double cof = Math.pow(-1, i + j) * detLaplace(adjunta(i, j, matriz));
				AUX[j][i] = cof / det;
			}
		}

		return AUX;
	}

	// Retorna la matriz quitando la fila y columna indicada
	public static double[][] adjunta(int fila, int columna, double[][] matriz) {

		double AUX[][] = new double[matriz.length - 1][matriz[0].length - 1];

		int I = 0, J = 0;

		for (int i = 0; i < matriz.length; i++) {

			if (fila != i) {

				for (int j = 0; j < matriz[i].length; j++) {

					if (columna != j) {

						AUX[I][J] = matriz[i][j];
						J++;
					}
				}

				I++;
				J = 0;
			}
		}

		return AUX;
	}
}
