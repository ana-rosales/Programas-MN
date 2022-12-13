package fes.aragon.modelo;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class VonMisses extends Funcion{
	private final DoubleProperty x0 = new SimpleDoubleProperty();
	private final DoubleProperty xi = new SimpleDoubleProperty();
	private final DoubleProperty xs = new SimpleDoubleProperty();
	private final DoubleProperty dx0 = new SimpleDoubleProperty();	
	private final DoubleProperty delta = new SimpleDoubleProperty();
	
	public VonMisses() {
		// TODO Auto-generated constructor stub
	}
	
	public VonMisses(String funcion, Double x0, Double xi, Double delta) {
		super(funcion);
		this.x0.set(x0);
		this.xi.set(xi);
		this.dx0.set(df(x0));
		this.delta.set(delta);
	}
	
	public final DoubleProperty x0() {
		return x0;
	}
		
	public final DoubleProperty xi() {
		return xi;
	}
	
	public final DoubleProperty xs() {
		return xs;
	}
	
	public final DoubleProperty dx0() {
		return dx0;
	}
	
	public final DoubleProperty delta() {
		return delta;
	}
	
	public void siguiente() {
		Double xi = this.xi.get();
		Double dx0 = this.dx0.get();
		Double xs = xi-((f(xi))/dx0); 
		this.xs.set(xs);
	}
	
	public void setDelta() {
		double delta = Math.abs(xi().get()-xs().get());
		this.delta.set(delta);
	}

	@Override
	public String toString() {
		return "VonMisses [x0=" + x0 + ", xi=" + xi + ", xs=" + xs + ", delta=" + delta + "]";
	}
	
	
}
