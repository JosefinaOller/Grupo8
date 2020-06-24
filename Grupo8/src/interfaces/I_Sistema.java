package interfaces;

import interfaces.I_Pago;
import personas.Persona;
import servicios.Domicilio;


public interface I_Sistema {
	
	public void agregarFacturas(Persona persona);
	public void agregarServicio(String persona,String internet, int cantCel, int cantTel, int cantTV, Domicilio domicilio);
	public void modificarAgregado(String persona,String direccion,String accion,String servicio);
	public void abonar(String nombrePersona,I_Pago tipo);
	public void duplicarFactura(String persona);
	public void eliminarContratacion(String nombrePersona,String domicilio);
	public String listarFactura(String persona);
	public String listarFacturas();



}