package fes.aragon.inicio;

import java.util.Scanner;

import fes.aragon.modelo.NRaphson;
import fes.aragon.modelo.NRaphson2;

public class NRaphson2Inicio {
	public static void main(String[] args) {
		int errno = 0;
		NRaphson2 current;
		Double xi, delta;
		Scanner entrada = new Scanner(System.in);
		String expresion, txtXi, txtDelta;
		
		do {
			System.out.println("\nIntroduzca funcion:");
			expresion = entrada.next();
			System.out.println("Introduzca el punto inicial:");
			txtXi = entrada.next();
			do {
				System.out.println("Introduzca el margen de error (no puede ser negativo):");
				txtDelta = entrada.next();
			} while (Double.parseDouble(txtDelta) <= 0);
		} while (expresion.equals("") || txtXi.equals("") || txtDelta.equals(""));
		
		xi = Double.parseDouble(txtXi);
		delta = Double.parseDouble(txtDelta);
		current = new NRaphson2(expresion, xi, delta);

		if (current.f(xi) == 0) {
			current.raiz().set(xi);
		} else {
			if (current.df(xi) != 0) {
				int i = 0;
				do {
					current.siguiente();
					current.iterar();
					current.setDelta();
					current.xi().set(current.xs().get());
					i++;
				} while (current.delta().get() > delta);
				current.raiz().set(current.xi().get());
			} else {
				System.out.println("La derivada con respecto al punto indicado es nula.");
				errno++;
			}
		}

		if (errno == 0) {
			System.out.println("resultado= " + current.raiz().get());
			System.out.println("iteraciones= " + current.iteraciones().get());
			System.out.println("derivada= " + current.derivada().get());
		} else {
			errno = 0;
		}
	}
	
}
