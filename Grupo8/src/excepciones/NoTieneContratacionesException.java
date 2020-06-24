package excepciones;

import personas.Persona;

public class NoTieneContratacionesException extends Exception {

	private Persona persona;
	
	public NoTieneContratacionesException(Persona persona, String mensaje) {
		super(mensaje);
		this.persona= persona;
	}

	public Persona getPersona() {
		return persona;
	}

	
}
