package fes.aragon.modelo;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

/**
 * @author ana-rosales
 */
public class NRaphson extends Funcion{
	private final DoubleProperty xi = new SimpleDoubleProperty();
	private final DoubleProperty xs = new SimpleDoubleProperty();
	private final DoubleProperty delta = new SimpleDoubleProperty();
	
	public NRaphson() {
		super();
	}
	
	public NRaphson(String funcion, Double x, Double delta) {
		super(funcion);
		this.xi.set(x);
		this.delta.set(delta);
	}
	
	public final DoubleProperty xi() {
		return xi;
	}
	
	public final DoubleProperty xs() {
		return xs;
	}
	
	public final DoubleProperty delta() {
		return delta;
	}
	
	public void siguiente() {
		Double xi = this.xi.get();
		Double xs = xi-((f(xi))/(df(xi))); 
		this.xs.set(xs);
	}
	
	public void setDelta() {
		double delta = Math.abs(xi().get()-xs().get());
		this.delta.set(delta);
	}

	@Override
	public String toString() {
		return "NRaphson [xi=" + xi + ", delta=" + delta + ", iteraciones=" + iteraciones
				+ ", raiz=" + raiz + "]";
	}
}
