package interfaces;

import excepciones.NoPuedeContratarException;
import excepciones.NoPuedeDarDeBajaException;
import excepciones.NoPuedePagarException;

public interface I_State {
	
	public void pagarFactura(I_Pago tipo,int mes) throws NoPuedePagarException;
    
	public void contratarNuevoServicio(I_Contratable contratacion) throws NoPuedeContratarException;
	
	public void darDeBajaServicio(String domicilio) throws NoPuedeDarDeBajaException;


}
