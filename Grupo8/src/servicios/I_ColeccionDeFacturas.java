
package servicios;

import java.util.Iterator;

public interface I_ColeccionDeFacturas {

	void agregarFactura(I_Factura factura, int mes);
	
	//void pagarFactura(I_Factura factura);
	
	I_Factura buscarFactura(int mes);
	
	Iterator<I_Factura> getFacturaIterator();
	
	int facturasSinPagar();
	
	Object clone() throws CloneNotSupportedException;
	
}
