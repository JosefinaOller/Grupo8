package excepciones;

import personas.Persona;

@SuppressWarnings("serial")
public class NoPuedePagarException extends Exception {

	private Persona persona;
	
	public NoPuedePagarException(Persona persona, String mensaje) {
		super(mensaje);
		this.persona= persona;
	}

	public Persona getPersona() {
		return persona;
	}

	
}
