package estados;

import interfaces.I_Contratable;
import interfaces.I_Pago;

import interfaces.I_State;
import personas.Fisica;

public class ConContratacion implements I_State {

	private Fisica abonado;
	
	public ConContratacion(Fisica abonado) {
		
		this.abonado = abonado;
	}

	public void pagarFactura(I_Pago tipo,int mes) {
		abonado.getColeccionDeFacturas().get(mes).precioFinal(abonado, tipo);
		abonado.getColeccionDeFacturas().get(mes).setPagado(true);
		if(abonado.getContadorFvencidas()>=2) // hay una forma mejor para buscar 
			abonado.setEstado(new Moroso(abonado));
	}

	public void contratarNuevoServicio(I_Contratable contratacion) {
		abonado.nuevaContratacion(contratacion);
	}

	public void darDeBajaServicio(String domicilio) {
		abonado.eliminaContratacion(abonado.buscaContratacion(domicilio));
		if(abonado.getListaContrataciones().size()==0) //no hay mas contrataciones, paso al estado Sin contratacion
			abonado.setEstado(new SinContratacion(abonado));
	}


}
