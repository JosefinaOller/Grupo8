
package modelo;

import java.util.Iterator;

import personas.Persona;

public class GestorDeFacturacion {

	public GestorDeFacturacion() {
		
	}

	public static void realizarFacturacion(Iterator<Persona> personas) {
		Persona actual= null;
		while (personas.hasNext()) {
			actual= personas.next();
		}
	}
}	
