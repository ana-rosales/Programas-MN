/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fes.aragon.ui;

import java.net.URL;

import javafx.fxml.FXMLLoader;

/**
 *
 * @author mash
 */
public enum PaginasFXML {
	INICIO("/fes/aragon/ui/fxml/Inicio.fxml"),
	UNARAIZ("/fes/aragon/ui/fxml/CalculoUnaRaiz.fxml"),
	SISTEMA("/fes/aragon/ui/fxml/SistemaEcuaciones.fxml");

	private final String ubicacion;

	PaginasFXML(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public URL getPagina() {
		return getClass().getResource(ubicacion);
	}

}
