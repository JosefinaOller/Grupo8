package estados;

import javax.swing.JOptionPane;

import excepciones.NoTieneContratacionesException;

import interfaces.I_Contratable;
import interfaces.I_Pago;
import interfaces.I_State;
import personas.Fisica;

public class SinContratacion implements I_State {

	private Fisica abonado;
	
	public SinContratacion(Fisica abonado) {
		this.abonado = abonado;
	}
	public void pagarFactura(I_Pago tipo,int mes) throws NoTieneContratacionesException { //no puede pagar
		throw new NoTieneContratacionesException(abonado, "El abonado no tiene contrataciones que pagar");
	}

	public void contratarNuevoServicio(I_Contratable contratacion) {
		abonado.nuevaContratacion(contratacion);
		abonado.setEstado(new ConContratacion(abonado));
	}

	public void darDeBajaServicio(String domicilio) {
		JOptionPane.showMessageDialog(null, "No puede dar de baja algun servicio aun", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);}
}
