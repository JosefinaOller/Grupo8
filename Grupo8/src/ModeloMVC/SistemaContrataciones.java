package ModeloMVC;

import java.util.HashMap;
import java.util.Iterator;

import agregado.ContratableFactory;
import excepciones.DomicilioInvalidoException;
import excepciones.PersonaExistenteException;
import excepciones.ServicioInternetInvalidoException;
import interfaces.I_Sistema;
import modelo.EmuladorPasoTiempo;
import interfaces.I_Pago;
import personas.Persona;
import servicios.Domicilio;
import personas.Persona;

public class SistemaContrataciones implements I_Sistema {
	
	private static SistemaContrataciones  instancia= null; //APLICO EL PATRON SINGLETON PUES SE INSTANCIA POR UNICA VEZ
	private HashMap <String,Persona> listaAbonados=new HashMap<String,Persona>();

    /**
     * Constructor privado de SistemaContrataciones  pues aplicamos el patron Singleton
     */
	private SistemaContrataciones () {
	}
	
	/**
	 * Metodo estatico para instanciar por unica vez SistemaContrataciones 
	 * @return devuelve la instancia de tipo SistemaContrataciones 
	 */
	public static SistemaContrataciones  getInstancia() { 
		if(instancia==null)
			instancia= new SistemaContrataciones();
		return instancia;
	}

	/**
	 * Agrega una Factura al sistema y a la vez analiza si no hay una persona repetida<br>
	 * <b>Pre: </b> El parametro Persona debe ser distinto de null<br>
	 * <b>Post: </b> Se agrega una factura mas a la lista<br>
	 * @param persona : Parametro que ser? agregado a nuestro sistema
	 * @throws PersonaExistenteException: Se lanza en el caso que se encuentra que la persona ingresada sea repetida
	 */
	public void agregarFacturas(Persona persona) { 
		if(!this.listaAbonados.containsKey(persona.getNombre())) 
			this.listaAbonados.put(persona.getNombre(), new Persona(persona));
		else
		{
			try {
				throw new PersonaExistenteException("Persona ya existente");
			} catch (PersonaExistenteException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	/**
	 * Agrega un servicio nuevo a la lista de contrataciones de factura<br>
	 * <b>Pre: </b>La persona debe ser distinto de cadena vacia y debe existir;Las cantidades como cantCel,cantTel,cantTv deben ser mayor o igual que cero; El domicilio no debe ser repetido<br>
	 * <b>Post: </b>Se agrega un servicio mas a la lista de contrataciones<br>
	 * @param persona: Parametro de tipo String que representa al titular de la factura
	 * @param internet: Parametro de tipo String que representa al servicio de internet que desea contratar
	 * @param cantCel: Parametro de tipo int que representa a la cantidad de celulares que desea agregar
	 * @param cantTel: Parametro de tipo int que representa a la cantidad de telefonos que desea agregar
	 * @param cantTV: Parametro de tipo int que representa a la cantidad de cables TV que desea agregar
	 * @param domicilio: Parametro de tipo Domicilio que representa al domicilio del serivicio asociado
	 * @throws ServicioInternetInvalidoException: Se lanza en el caso de que el servicio sea invalido
	 * @throws DomicilioInvalidoException: Se lanza en el caso de que el domicilio sea invalido
	 */
	public void agregarServicio(String persona,String internet, int cantCel, int cantTel, int cantTV, Domicilio domicilio) { 
		try {
			this.listaAbonados.get(persona).nuevaContratacion(ContratableFactory.nuevoServicio(internet, cantCel, cantTel, cantTV, domicilio));
		} catch (ServicioInternetInvalidoException e) { //VACIO O NO EXISTE
			System.out.println(e.getMessage());
		} catch (DomicilioInvalidoException e) { //VACIO
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * Realiza el cambio de agregado del servicio
	 * <b>Pre: </b> El parametro Direccion debe ser valido y que exista en la factura;El parametro Accion no debe ser una cadena vacia y debe existir; El parametro Servicio no debe ser una cadena vacia y debe existir<br>
	 * <b>Post: </b> Se realiza el cambio de agregado del servicio<br>
	 * @param direccion: Parametro de tipo String que representa a la direccion del servicio que desea cambiar
	 * @param accion: Parametro de tipo String que representa a la accion que quiera realizar
	 * @param servicio: Parametro de tipo String que representa al servicio que desea modificar
	 */
	public void modificarAgregado(String persona,String direccion,String accion,String servicio) { //PRECONDICON DIRECCION VALIDA Y QUE EXISTA EN LA FACTURA 
		int pos=this.listaAbonados.get(persona).buscaContratacion(direccion);
		this.listaAbonados.get(persona).modificaContratacion(pos, accion, servicio);
	}
			
	/**
	 * Metodo para abonar el total segun el medio de pago
	 * <b>Pre: </b> El parametro nombrePersona debe ser distinto de null o cadena vacia;El parametro tipo debe ser distinto de null y debe existir<br>
	 * <b>Post: </b> Devuelve el valor del total calculado con el porcentaje aplicado<br>
	 * @param nombrePersona: Parametro de tipo String que representa al abonado de la factura
	 * @param tipo: Parametro de tipo I_Pago que representa al medio de pago
	 */
	public void abonar(String nombrePersona,I_Pago tipo) {
		Persona persona=this.listaAbonados.get(nombrePersona).getPersona();
		this.listaAbonados.get(nombrePersona).precioFinal(persona, tipo); 
	}
	/**
	 * Duplica la factura de una persona
	 * <b>Pre: </b> El parametro persona debe ser distinto de cadena vacia<br>
	 * <b>Post: </b> Se muestra la factura duplicada<br>
	 * @param persona: Parametro de tipo String que representa al abonado de la factura a duplicar
	 * @throws CloneNotSupportedException: Se lanza en el caso de que no sea posible clonar
	 */
	public void duplicarFactura(String persona) {
		Persona facturaOriginal=this.listaAbonados.get(persona);
		Persona facturaDuplicada=null;
		try {
			facturaDuplicada=(Persona) facturaOriginal.clone();
			System.out.println("FACTURA DUPLICADA: \n"+this.listarFactura(facturaDuplicada.getPersona().getNombre()));
			
		} catch (CloneNotSupportedException e) {
			System.out.println("Error al clonar, la persona es juridica");
		}
	}

	/**
	 * Se elimina una contratacion de una lista de contrataciones de una factura que pertenece a un abonado.<br>
	 * <b>Pre: </b> Los parametros nombrePersona y domicilio debe ser distinto de null<br>
	 * <b>Post: </b> Se elimino una contratacion ; si la lista de contrataciones queda vacia, se elimina la factura  <br>
	 * @param nombrePersona : Parametro de tipo String que representa el nombre de una persona.
	 * @param nombrePersona : Parametro de tipo String que representa el domicilio de una persona.
	 */
	public void eliminarContratacion(String nombrePersona,String domicilio) {
		int posicion;
		if(this.listaAbonados.containsKey(nombrePersona)) { 
			posicion= this.listaAbonados.get(nombrePersona).buscaContratacion(domicilio);
			if (posicion>=0) { 
				this.listaAbonados.get(nombrePersona).eliminaContratacion(posicion);
				if (this.listaAbonados.get(nombrePersona).getListaContrataciones().isEmpty()) 
					this.listaAbonados.remove(nombrePersona);
			}
		}
	}
	/**
	 * @param persona: Parametro de tipo String que representa al abonado que desee listar su factura, distinto de cadena vacia
	 * @return Devuelve String que imprime la factura de un abonado
	 */
	public String listarFactura(String persona) {
		StringBuilder sb= new StringBuilder();
		this.listaAbonados.get(persona).actualizaPrecio();
		sb.append(this.listaAbonados.get(persona).getPersona().toString()+"\n");
		sb.append(this.listaAbonados.get(persona).listarContrataciones());
		if(this.listaAbonados.get(persona).getTotalConP()>=this.listaAbonados.get(persona).getTotalSinP())
			sb.append("\n-->PRECIO TOTAL: " + this.listaAbonados.get(persona).getTotalConP()+"\n\n");
		else
		{
			sb.append("\n-->PRECIO TOTAL SIN DESCUENTO: "+ this.listaAbonados.get(persona).getTotalSinP()+ "\n\n");
			sb.append("\n-->PRECIO TOTAL CON DESCUENTO: "+ this.listaAbonados.get(persona).getTotalConP() + "\n\n");
		}
		return sb.toString();
	}
	/**
	 * @return Devuelve String de toda la informacion detallada de las facturas
	 */
	public String listarFacturas() {
		StringBuilder sb= new StringBuilder();
		sb.append("FACTURAS:\n");
		for(HashMap.Entry<String,Persona> pair: listaAbonados.entrySet()) {
			pair.getValue().actualizaPrecio();
			sb.append(pair.getValue().getPersona().toString() + "\nLista de contrataciones: \n"+ pair.getValue().listarContrataciones()+ "\n");
			if(pair.getValue().getTotalConP()>=pair.getValue().getTotalSinP())
				sb.append("\n--> PRECIO TOTAL: " + pair.getValue().getTotalConP()+"\n\n");
			else {
				sb.append("\n--> PRECIO TOTAL SIN DESCUENTO: "+ pair.getValue().getTotalSinP()+ "\n\n");
				sb.append("\n--> PRECIO TOTAL CON DESCUENTO: "+ pair.getValue().getTotalConP() + "\n\n");
			}
		}
		return sb.toString();
	}

	public Iterator<Persona> getPersonas(){
		return this.listaAbonados.values().iterator();
	}

}
