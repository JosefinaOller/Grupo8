package excepciones;

import personas.Persona;

@SuppressWarnings("serial")
public class NoPuedeContratarException extends Exception {
	
	private Persona persona;
	
	public NoPuedeContratarException(Persona persona, String mensaje) {
		super(mensaje);
		this.persona= persona;
	}

	public Persona getPersona() {
		return persona;
	}

}
