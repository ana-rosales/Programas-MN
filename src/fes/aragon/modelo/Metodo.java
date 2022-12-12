/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fes.aragon.modelo;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * @author ana-rosales
 */
public class Metodo {
	private final IntegerProperty id = new SimpleIntegerProperty();
	private final StringProperty nombre = new SimpleStringProperty();

	public Metodo(Integer id, String nombre) {
		this.id.set(id);
		this.nombre.set(nombre);
	}

	/*
	 * Getters son utilizados por PropertyValueFactory sin ellos no despliega
	 * información en una TableView c:
	 */

	public final IntegerProperty idMetodo() {
		return id;
	}

	public final Integer getId() {
		return idMetodo().get();
	}

	public final StringProperty nombreMetodo() {
		return nombre;
	}

	public final String getNombre() {
		return nombreMetodo().get();
	}

}
