package fes.aragon.modelo;

public class Matriz {
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
