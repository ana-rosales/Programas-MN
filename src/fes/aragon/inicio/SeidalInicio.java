package fes.aragon.inicio;

import java.util.Scanner;

import fes.aragon.modelo.Matriz;

public class SeidalInicio extends Matriz {
	public static void main(String[] args) {
		Scanner entrada = new Scanner(System.in);
		int n, m, i = 0;
		boolean iguales = true;

		do {
			System.out.println("Ingrese el total de ecuaciones (positivo): ");
			n = entrada.nextInt();
		} while (n < 0);
		do {
			System.out.println("Cuántas incógnitas tienen? (positivo):");
			m = entrada.nextInt() + 1;
		} while (m < 0);

		double[][] a = new double[n][m];
		double[] prev = new double[m - 1];
		double[] x = new double[m - 1];
		double coef = 0;

		for (i = 0; i < n; i++) {
			for (int j = 0; j < m - 1; j++) {
				System.out.println("Ingrese el elemento " + j + " de la ecuación " + i + ": ");
				a[i][j] = entrada.nextDouble();
			}
			System.out.println("Ingrese el resultado de la ecuación " + i + ": ");
			a[i][m - 1] = entrada.nextDouble();
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
		System.out.println();
		do {
			for (i = 0; i < n; i++) {
				prev[i] = x[i];
				double resta = 0;
				System.out.println();
				System.out.println("X" + i + "= c" + i);
				for (int j = 0; j < m - 1; j++) {
					if (j != i) {
						System.out.print("-a" + i + j + "*x" + j);
						resta = resta - a[i][j] * x[j];
					}
					x[i] = a[i][m - 1] + resta;
				}
				iguales = (x[i] == prev[i]) ? true : false;
			}
		} while (!iguales);

		System.out.println();
		for (i = 0; i < x.length; i++) {
			System.out.println("X" + (i + 1) + "= " + x[i]);
		}

		System.out.println();
		for (i = 0; i < x.length; i++) {
			System.out.println("PREV" + (i + 1) + "= " + prev[i]);
		}
	}
}
