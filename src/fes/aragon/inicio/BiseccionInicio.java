package fes.aragon.inicio;

import java.util.Scanner;

import fes.aragon.modelo.Biseccion;

public class BiseccionInicio {
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		int errno = 0;
		Biseccion current;
		Double sup, inf, err;
		Scanner usuFun, usuSup, usuInf, usuErr;
		String expresion, txtSup, txtInf, txtErr;
		
		System.out.println("\nIntroduzca funcion:");
		usuFun = new Scanner(System.in);
		expresion = usuFun.next();
		System.out.println("Introduzca límite superior:");
		usuSup = new Scanner(System.in);
		txtSup = usuSup.next();
		System.out.println("Introduzca límite inferior:");
		usuInf = new Scanner(System.in);
		txtInf = usuInf.next();
		System.out.println("Introduzca margen de error:");
		usuErr = new Scanner(System.in);
		txtErr = usuErr.next();
		
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
				System.out.println("Entre los limites indicados, no existe raiz.");
				errno++;
			}
		} catch (Exception e) {
			System.out.println(current.error().get());
			errno++;
		}

		if (errno == 0) {
			System.out.println("resultado= "+current.raiz().get());
			System.out.println("iteraciones= "+current.iteraciones().get());
			System.out.println("derivada= "+current.derivada().get());
		} else {
			errno = 0;
		}
	}
}
