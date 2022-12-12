package fes.aragon.modelo;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * @author ana-rosales
 */
public class Biseccion extends Funcion {
	private final DoubleProperty inf = new SimpleDoubleProperty(0.0);
	private final DoubleProperty sup = new SimpleDoubleProperty(0.0);
	private final DoubleProperty prod = new SimpleDoubleProperty(0.0);
	private final DoubleProperty errRaiz = new SimpleDoubleProperty(0.0);

	public Biseccion() {
		super();
	}
	
	public Biseccion(String funcion, Double i, Double s) {
		super(funcion);
		inf.set(i);
		sup.set(s);
	}

	public final DoubleProperty inf() {
		return inf;
	}

	public final DoubleProperty sup() {
		return sup;
	}
	
	public final DoubleProperty prod() {
		return prod;
	}

	public final DoubleProperty errRaiz() {
		return errRaiz;
	}

	/**
	 * Método que calcula una aproximación de la raíz de la función por el método
	 * de bisección.
	 */
	public void setRaiz() {
		Double raiz = (inf.get()+sup.get())/2;
		raiz().set(raiz);
	}
	
	/**
	 * Calcula el producto del valor de f en el límite inferior por el valor de f
	 * en la última aproximación a la raíz encontrada.
	 */
	public void setProducto() {
		Double prod = f(inf.get())*f(raiz.get());
		prod().set(prod);
	}
	
	/**
	 * Método que calcula el margen de error de la aproximación a la raíz que se acaba
	 * de calcular.
	 */
	public void setErrRaiz() {
		acomodarLimites();
		Double errRaiz = sup.get()-inf.get();
		errRaiz().set(errRaiz);
	}
	
	/**
	 * Método que acomoda límites, si el superior es menor que el inferior, intercambian
	 * lugares, sino se mantienen como están.
	 */
	public void acomodarLimites() {
		if(inf.get() > sup.get()) {
			Double temp = sup.get();
			sup.set(inf.get());
			inf.set(temp);;
		}
	}
	
	@Override
	public String toString() {
		return super.toString() + "Biseccion [inf=" + inf + ", sup=" + sup + ", prod=" + prod
				+ ", errRaiz=" + errRaiz + "]";
	}

}
