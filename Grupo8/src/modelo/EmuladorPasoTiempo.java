package modelo;

import java.util.Observable;

import ModeloMVC.SistemaContrataciones;

public class EmuladorPasoTiempo extends Observable {
	
	private int mesActual;

	public EmuladorPasoTiempo() {
		this.mesActual= 1;
	}

	public void avanzarMes() {
		this.setChanged();
		this.notifyObservers(SistemaContrataciones.getInstancia().getPersonas());
		this.mesActual++;
	}
	
}
