package servicios;

import java.util.ArrayList;
import agregado.Celular;
import agregado.DecoratorAgregado;
import agregado.TV_Cable;
import agregado.Telefono;
import interfaces.I_Contratable;
import interfaces.I_Pago;
import personas.Persona;

/**
 * @author Grupo8
 * <br>
 *Clase que representa una Factura
 */
public class Factura implements Cloneable, I_Factura {

    private double totalSinP; //porcentaje ya que, dependiendo del tipo de pago se hace un incremento o descuento
    private double totalConP;
    private boolean pagado;
    private String detalles;
    
	/**
	 * Constructor Factura
	 * <br>
	 */
	public Factura() {

		this.totalConP=0;
		this.totalSinP=0;
		this.pagado= false;
	}

	public void setDetalles(String detalles) {
		this.detalles= detalles;
		
	}
	public String getDetalles() {
		return this.detalles;
	}
	
	public double getTotalSinP() {
		return totalSinP;
	}
	public double getTotalConP() {
		return totalConP;
	}
	
	
	public void setTotalSinP(double totalSinP) {
		this.totalSinP = totalSinP;
	}


	public void setTotalConP(double totalConP) {
		this.totalConP = totalConP;
	}


	public boolean isPagado() {
		return pagado;
	}
	
	
	public void setPagado(boolean valor) {
		this.pagado = valor;
	}
	

	public void pagar() {
		this.pagado= true;
	}	
	
	/**
	 * Calcula el precio total a abonar aplicando el medio de pago
	 * <b>Pre: </b> I_Contratable debe ser distinto de null <br>
	 * <b>Post: </b> Se agrega una contratacion mas a la lista<br>
	 * @param persona: Parametro de tipo Persona que representa al abonado de la factura
	 * @param tipo: Parametro de tipo I_Pago que representa el medio de pago
	 */
	public void precioFinal (Persona persona,I_Pago tipo) {
		double total=0;
		this.totalSinP=total;
		this.totalConP=persona.aplicarPorcentaje(tipo, total);
	}
	
	/**
	 * Metodo de clonacion de factura
	 *@return Devuelve un clon de factura
	 *@throws CloneNotSupportedException : se lanza cuando no es posible clonar la factura
	 */
	@Override
    public Object clone() 
    {
        Factura facturaClonada=null;
        try {
			facturaClonada=(Factura) super.clone();
		} catch (CloneNotSupportedException e) {
			// Facturas ahora son siempre clonables
		}
        return facturaClonada;
                
    }

	
}
