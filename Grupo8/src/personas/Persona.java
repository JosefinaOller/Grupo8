package personas;



import java.util.ArrayList;

import agregado.Celular;
import agregado.DecoratorAgregado;
import agregado.TV_Cable;
import agregado.Telefono;
import interfaces.I_Contratable;
import interfaces.I_Pago;
import servicios.Domicilio;
import servicios.Factura;
import servicios.I_ColeccionDeFacturas;
import servicios.I_Factura;

import servicios.Internet100;
import servicios.Internet500;
import servicios.ListaFacturas;
import servicios.Servicio;

/**
 * @author Grupo8
 * <br>
 * Clase abstracta que representa una Persona
 */
public abstract class Persona implements Cloneable{
	
	private String nombre;
	protected ArrayList<I_Contratable> coleccionContrataciones;
	protected I_ColeccionDeFacturas coleccionDeFacturas;
	protected double totalSinP;
	protected double totalConP;

    
	/**
	 * Constructor con un parametro para setear el nombre de la persona
	 * <br>
	 * @param nombre: parametro de tipo String que representa el nombre de la persona 
	 */
	public Persona(String nombre) {
		this.nombre = nombre;
		this.coleccionDeFacturas= new ListaFacturas();
		this.coleccionContrataciones= new ArrayList<I_Contratable>();
		this.totalSinP=0;
		this.totalConP=0;

		
	}

	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre Setea el nombre de la persona, debe ser distinto de null y no repetido
	 */
	public void setNombre(String nombre) { 
		this.nombre = nombre;
	}
	
	
	
	public ArrayList<I_Contratable> getListaContrataciones() {
		return listaContrataciones;
	}

	public void setListaContrataciones(ArrayList<I_Contratable> listaContrataciones) {
		this.listaContrataciones = listaContrataciones;
	}

	public I_ColeccionDeFacturas getColeccionDeFacturas() {
		return coleccionDeFacturas;
	}

	public void setColeccionDeFacturas(I_ColeccionDeFacturas coleccionDeFacturas) {
		this.coleccionDeFacturas = coleccionDeFacturas;
	}

	/**
	 *Metodo de clonacion condicional
	 *<br>
	 *La clase Persona conserva la propagacion de la excepcion, ya que no sabemos si las clases hijas seran o no cloneables
	 */
	
	@Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
	/**
	 *Realiza el calculo del total a abonar aplicando el tipo de pago correspondiente<br>
	 *<b>Pre: </b> tipo debe ser distinto de null y el total debe ser positivo <br>
	 * <b>Post: </b> sera calculado el total aplicando el tipo de pago
	 * @param tipo: medio de pago para aplicar el porcentaje correspondiente
	 * @param total: total de la factura a pagar para calcular su precio final aplicando el porcentaje
	 * @return devuelve el valor del total calculado con el porcentaje aplicado
	 */
	public abstract double aplicarPorcentaje(I_Pago tipo,double total); 
    
	/**
	 * Agrega una nueva linea de contratacion a la factura
	 * <b>Pre: </b> I_Contratable debe ser distinto de null <br>
	 * <b>Post: </b> Se agrega una contratacion mas a la lista<br>
	 * @param iContratable : Parametro que sera agregado a nuestra factura
	 */
	public void nuevaContratacion(I_Contratable iContratable) {
    	this.coleccionContrataciones.add(iContratable);

    }
	
	/**
	 * Busca una linea contratacion de acuerdo al domicilio ingresado
	 * <b>Pre: </b> El parametro domicilio tiene que exisitr y distinto de null o cadena vacia <br>
	 * <b>Post: </b> Encuentra la posicion de la linea de contratacion<br>
	 * @param domicilioPersona: Parametro que se usa para buscar la linea de contratacion
	 * @return Devuelve el valor de tipo int la posicion de la linea de contratacion buscada
	 */
	public int buscaContratacion(String domicilioPersona) { 
		int res=0;
		if (!this.coleccionContrataciones.isEmpty()) {
			int i=0;
			while(i<this.coleccionContrataciones.size() && this.coleccionContrataciones.get(i).getDomicilio().getDireccion().equals(domicilioPersona) ) 
				i++;
			if (i<this.coleccionContrataciones.size())

				res=i;
		}
		return res;
	}
	
	
	/**
	 * Cambia o quitar algun servicio o agregado
	 * <b>Pre: </b> I_Contratable debe ser distinto de null <br>
	 * <b>Post: </b> Se agrega una contratacion mas a la lista<br>
	 * @param pos: Parametro de tipo int que representa el ID de la linea de contratacion
	 * @param accion: Parametro de tipo String que representa la accion tales como modificar o quitar
	 * @param servicio: Parametro de tipo String que representa el servicio a modificar o quitar algun agregado
	 */
	public void modificaContratacion(int pos,String accion,String servicio) { 
		int cantCelulares=0,cantFijo=0,cantTV=0;									
		accion=accion.toUpperCase();
		servicio=servicio.toUpperCase();
		I_Contratable contratable= this.coleccionContrataciones.get(pos);

		while(contratable.isCelular() || contratable.isTelefono() || contratable.isTV_Cable()) { 
			DecoratorAgregado decorador= (DecoratorAgregado) contratable;
			if (contratable.isCelular()) {
				cantCelulares= decorador.getCantLineas();
			}
			else if (contratable.isTelefono()) {
				cantFijo=decorador.getCantLineas(); 
			}
			else if (contratable.isTV_Cable()) {
				cantTV= decorador.getCantLineas();
			}
			contratable= decorador.getContratable();
		}	
		I_Contratable reemplazo= contratable;
		Servicio s= (Servicio) contratable;
		Domicilio domicilio= s.getDomicilio();
		
		if (accion.equals("CAMBIAR")) {
			if (servicio.equals("INTERNET100")) 
				reemplazo= new Internet100(domicilio,s.getID());
			 else if(servicio.equals("INTERNET500"))
				reemplazo= new Internet500(domicilio,s.getID());
		}
		else if (accion.equals("QUITAR")) {
			if (servicio.equals("TVCABLE"))
				 cantTV--;
			if (servicio.equals("CELULAR"))
				 cantCelulares--;
			if (servicio.equals("TELEFONO FIJO"))
				 cantFijo--;
		}
		else if (accion.equals("AGREGAR")) {
			if (servicio.equals("TVCABLE"))
				 cantTV++;
			if (servicio.equals("CELULAR"))
				 cantCelulares++;
			if (servicio.equals("TELEFONO FIJO"))
				 cantFijo++;
		}
		if (cantTV>0) { //ACTUALIZAR
			I_Contratable auxreemplazo= new TV_Cable(cantTV,reemplazo);
			reemplazo=auxreemplazo;
		}
		if (cantCelulares>0) {
			I_Contratable auxreemplazo= new Celular(cantCelulares,reemplazo);
			reemplazo=auxreemplazo;
		}
		if (cantFijo>0) {
			I_Contratable auxreemplazo= new Telefono(cantTV,reemplazo);
			reemplazo=auxreemplazo;
		}
		this.coleccionContrataciones.set(pos, reemplazo);

	}
	
	/**
	 * Elimina o da de baja un servicio 
	 * <b>Pre: </b> El parametro posicion debe ser numero positivo y debe existir<br>
	 * <b>Post: </b> Se elimina una linea de contratacion de la factura<br>
	 * @param posicion: Parametro de tipo int que representa la posicion de dicha linea de contratacion dentro de la lista de contrataciones.
	 */
	public void eliminaContratacion(int posicion) {
		this.coleccionContrataciones.remove(posicion);

	}
	
	/**
	 * @return Devuelve toda la informacion detallada de la lista de contrataciones
	 */
	public String listarContrataciones() {
		StringBuilder sb= new StringBuilder();
		int i=0;
		while(i<this.coleccionContrataciones.size()) {
			sb.append("ID: " + this.coleccionContrataciones.get(i).getID()+" "+ this.coleccionContrataciones.get(i).toString()+"\n");

			i++;
		}
		return sb.toString();	
	}
	
	/**
	 * Calcula el precio total a abonar aplicando el medio de pago
	 * <b>Pre: </b> I_Contratable debe ser distinto de null <br>
	 * <b>Post: </b> Se agrega una contratacion mas a la lista<br>
	 * @param persona: Parametro de tipo Persona que representa al abonado de la factura
	 * @param tipo: Parametro de tipo I_Pago que representa el medio de pago
	 */
	public void precioFinal(I_Pago tipo) {
		double total=0;
		for(int i=0;i<this.coleccionContrataciones.size();i++) {
			total+=this.coleccionContrataciones.get(i).getPrecio();
		}
		this.totalSinP=total;
		this.totalConP=this.aplicarPorcentaje(tipo, total);
		
	}

	public ArrayList<I_Contratable> getColeccionContrataciones() {
		return coleccionContrataciones;
	}

	public void setColeccionContrataciones(ArrayList<I_Contratable> coleccionContrataciones) {
		this.coleccionContrataciones = coleccionContrataciones;
	}

	public I_ColeccionDeFacturas getColeccionDeFacturas() {
		return coleccionDeFacturas;
	}

	public void setColeccionDeFacturas(I_ColeccionDeFacturas coleccionDeFacturas) {
		this.coleccionDeFacturas = coleccionDeFacturas;
	}

	public double getTotalSinP() {
		return totalSinP;
	}

	public void setTotalSinP(double totalSinP) {
		this.totalSinP = totalSinP;
	}

	public double getTotalConP() {
		return totalConP;
	}

	public void setTotalConP(double totalConP) {
		this.totalConP = totalConP;
	}
	
	

}
