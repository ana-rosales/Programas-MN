package fes.aragon.modelo;

import javafx.beans.property.StringProperty;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

//lib para procesar funciones de un string
import org.lsmp.djep.djep.DJep;
import org.nfunk.jep.JEP;
import org.nfunk.jep.Node;
import org.nfunk.jep.ParseException;


/**
 * @author ana-rosales
 */
public class Funcion {

	protected final IntegerProperty iteraciones = new SimpleIntegerProperty(0);
	private final StringProperty funcion = new SimpleStringProperty("");
	private final StringProperty derivada = new SimpleStringProperty("");
	protected final DoubleProperty raiz = new SimpleDoubleProperty(0.0);
	private final StringProperty error = new SimpleStringProperty("");
	private final DoubleProperty fX = new SimpleDoubleProperty(0.0);
	private final DoubleProperty x = new SimpleDoubleProperty(0.0);
	Node nodoFuncion;
	Node nodoDerivada;
	DJep djep;
	JEP jep;

	public Funcion() {
		
	}
	
	public Funcion(String fn) {
		funcion.set(fn);
		derivar();
	}

	public final StringProperty funcion() {
		derivar();
		return funcion;
	}

	public final StringProperty derivada() {
		return derivada;
	}
	
	public final DoubleProperty x() {
		return x;
	}

	public final DoubleProperty fX() {
		return fX;
	}

	public final DoubleProperty raiz() {
		return raiz;
	}

	public final StringProperty error() {
		return error;
	}

	public final IntegerProperty iteraciones() {
		return iteraciones;
	}

	/**
	 * Método que evalúa un valor de x en la funcion (con herramientas del objeto JEP).
	 */
	public void evaluar(String expresion) {
		jep = new JEP();
		this.jep.addStandardFunctions();// incluye sen(x) cos(x) etc
		this.jep.addStandardConstants();// incluye pi euler etc
		this.jep.addVariable("x", this.x().get());
		this.jep.parseExpression(expresion);
		this.fX.set(this.jep.getValue());
		this.error.set((this.jep.hasError()) ? "Hay error" : "No hay error");
	}

	/**
	 * Método que calcula la derivada de la función.
	 */
	public void derivar() {
		try {
			this.djep = new DJep();
			this.djep.addStandardFunctions();
			this.djep.addStandardConstants();
			this.djep.addComplex();
			this.djep.setAllowUndeclared(true);
			this.djep.setAllowAssignment(true);
			this.djep.setImplicitMul(true);
			this.djep.addStandardDiffRules();
			
			this.nodoFuncion = this.djep.parse(this.funcion.get());
			Node diff = this.djep.differentiate(nodoFuncion, "x");
			this.nodoDerivada = this.djep.simplify(diff);
			this.derivada.set(this.djep.toString(this.nodoDerivada));
		} catch (ParseException e) {
			System.out.println("Error" + e.getErrorInfo());
		}
	}
	/**
	 * Método que calcula el valor de una función en x, manda a llamar el método
	 * evaluar().
	 * @param x
	 * @return fX
	 */
	public double f(Double x) {
		this.x().set(x);
		this.evaluar(this.funcion.get());
		double fX = this.fX().get();
		return fX;
	}
	
	/**
	 * Método que calcula el valor de una función cuando esta está derivada, manda a 
	 * llamar el método evaluar().
	 * @param x
	 * @return Valor de la derivada en x.
	 */
	public double df(double x) {
		this.x().set(x);
		this.evaluar(this.derivada.get());
		double fX = this.fX().get();
		return fX;
	}
	
	/**
	 * Método que itera :)
	 */
	public void iterar() {
		Integer actual = iteraciones.get();
		actual++;
		iteraciones.set(actual);
	}
//	//para la gráfica
//	public double[] evaluar(double[] x)throws Exception{
//		int n=x.length;
//		double[] fX= new double[n];
//		for (int i=0; i<n;i++) {
//			fX[i] = this.f(x[i]);
//		}
//		return fX;
//	}
//	
//	//para la grafica
//	public double[] rango(double min, double max, double increm) {
//		int n = (int) (Math.abs(max-min)/increm);
//		double[] fX = new double[n];
//		for(int i=0; i<n;i++) {
//			fX[i] = min;
//			min+=increm;
//		}
//		return fX;
//	}
	


//	public void dibujar(Plano p) {
//		Thread grafica = new Thread(() -> {
//			double x = -(p.getMidW());
//			while (x < p.getMidW()) {
//				double y = -(f(x));
//				Platform.runLater(() -> {
//					if (y >= 0 && y <= p.getDouH()) {
//						p.dibujarPunto(x, y);
//					}
//				});
//			}
//			try {
//				Thread.sleep(0);
//			} catch (InterruptedException ex) {
//				System.out.println(ex);
//			}
//		});
//		grafica.start();
//	}

	@Override
	public String toString() {
		return "Funcion [iteraciones=" + iteraciones + ", funcion=" + funcion + "]";
	}

}
