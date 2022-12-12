package fes.aragon.modelo;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class NRaphson2 extends Funcion{
	private final DoubleProperty xi = new SimpleDoubleProperty();
	private final DoubleProperty xs = new SimpleDoubleProperty();
	private final DoubleProperty delta = new SimpleDoubleProperty();
	
	public NRaphson2() {
		super();
	}
	
	public NRaphson2(String funcion, Double x, Double delta) {
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
		Double fxi = f(xi);
		Double dxi = df(xi);	//derivada
		Double d2xi = df(dxi);	//2a derivada
		Double xs = xi-((fxi)/(dxi-(fxi*d2xi)/(2*dxi))); 
		this.xs.set(xs);
	}
	
	public void setDelta() {
		double delta = Math.abs(xi().get()-xs().get());
		this.delta.set(delta);
	}

}
