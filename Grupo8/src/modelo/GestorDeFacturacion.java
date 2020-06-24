
package modelo;

import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

import personas.Persona;
import servicios.Factura;
import servicios.I_Factura;

public class GestorDeFacturacion implements Observer {

	private EmuladorPasoTiempo ept;
	
	public GestorDeFacturacion() {
		
	}
	
	public void agregarObservable(EmuladorPasoTiempo ept) {
		ept.addObserver(this);
		this.ept= ept;
	}

	public static void realizarFacturacion(Iterator<Persona> personas) {
		Persona actual= null;
		while (personas.hasNext()) {
			actual= personas.next();
		}
	}

	public void update(Observable o, Object arg1) {
		EmuladorPasoTiempo ept= (EmuladorPasoTiempo) o;
		if (this.ept == ept) {
			Iterator<Persona> personas= (Iterator<Persona>) arg1;
			Persona actual= null;
			while (personas.hasNext()) {
				actual= personas.next();
				I_Factura factura= new Factura();
				factura.setDetalles(actual.listarContrataciones());
				//Codigo para asignar el precio a la factura
			}
		}
		else {
			throw new IllegalArgumentException();
		}
	}
}	
