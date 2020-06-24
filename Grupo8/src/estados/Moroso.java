package estados;

import javax.swing.JOptionPane;

import interfaces.I_Contratable;
import interfaces.I_Pago;
import interfaces.I_State;
import personas.Fisica;
import servicios.Factura;

public class Moroso implements I_State {

	private Fisica abonado;
	
	public Moroso(Fisica abonado) {
		this.abonado = abonado;
	}

	public void pagarFactura(I_Pago tipo,int mes) { //SE SUPONE QUE ES FALSE PAGADO
		Factura factura=abonado.getColeccionDeFacturas().get(mes);
		factura.precioFinal(abonado, tipo);
		factura.setTotalConP(factura.getTotalConP()*1.3);
		factura.setTotalSinP(factura.getTotalSinP()*1.3);
		factura.setPagado(true);
		abonado.setContadorFvencidas(abonado.getContadorFvencidas()-1);
		if(abonado.getContadorFvencidas()<2)
			abonado.setEstado(new ConContratacion(abonado));
	}

	public void contratarNuevoServicio(I_Contratable contratacion) {

		JOptionPane.showMessageDialog(null, "Tiene deudas,no puede contratar algun servicio!", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
		
	}

	public void darDeBajaServicio(String domicilio) {

		JOptionPane.showMessageDialog(null, "Tiene deudas,no puede dar de baja algun servicio!", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
		
	}

}
