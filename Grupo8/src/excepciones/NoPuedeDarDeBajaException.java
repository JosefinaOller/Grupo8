package excepciones;

import personas.Persona;

@SuppressWarnings("serial")
public class NoPuedeDarDeBajaException extends Exception {

	private Persona persona;
	
	public NoPuedeDarDeBajaException(Persona persona, String mensaje) {
		super(mensaje);
		this.persona= persona;
	}

	public Persona getPersona() {
		return persona;
	}
}
