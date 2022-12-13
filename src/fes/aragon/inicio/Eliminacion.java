package fes.aragon.inicio;

import java.util.Scanner;

public class Eliminacion {
	public static void main(String[] args) {
		Scanner entrada = new Scanner(System.in);
		int n, m;
		
		System.out.println("Ingrese el total de ecuaciones: ");
		n = entrada.nextInt();
		System.out.println("Cuántas incógnitas tienen? ");
		m = entrada.nextInt()+1;		
		
		double[][] a = new double[n][m];
		double[] prod = new double[m];
		double[] x = new double[m-1];
		
		for (int i = 0; i<n;i++) {
			for(int j = 0; j <m-1;j++) {
				System.out.println("Ingrese el elemento " + j + " de la ecuación " + i + ": ");
				a[i][j] = entrada.nextDouble();
			}
			System.out.println("Ingrese el resultado de la ecuación " + i + ": ");
			a[i][m-1] = entrada.nextDouble();
		}
		
		imprimir(n,m,a);
		
		for(int i = 0; i<n-1;i++) {
			int p = i;
			if(p<=n && a[p][i]!=0) {
				for(int j = i+1; j<n;j++) {
					double co = a[j][i]/a[i][i];
					//combinación lineal
					for (int k = 0; k<m; k++) {
						prod[k] = a[i][k] * co;
					}
					//reducción
					for (int k = 0; k<m;k++) {
						a[j][k] = a[j][k] - prod[k];
					}
				}
			}
			else {
				System.out.println("No hay una solución única.");
			}
		}
		
		imprimir(n,m,a);

		double cm = a[m-2][m-1];
		double amm = a[m-2][m-2];
		x[m-2] = cm/amm;
		
		for(int i = m-3; i>=0;i--) {
			double suma = 0;
			for (int j =m-2;j>i;j--) {
				suma = suma -a[i][j]*x[j];
			}
			x[i] = (a[i][m-1]+suma)/a[i][i];
		}
		
		System.out.println();
		for(int i = 0; i<x.length;i++) {
			System.out.println("X"+(i+1)+"= " +x[i]);
		}
		
	}
	
	public static void imprimir(int n, int m, double[][] a) {
		System.out.println();
		for (int i = 0; i<n;i++) {
			for(int j = 0; j<m;j++) {
				System.out.print("a[" + i + "]["+ j +"]=" + a[i][j] + " ");
			}
			System.out.println();
		}
	}
}
