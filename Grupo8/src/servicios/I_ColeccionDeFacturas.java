
package servicios;

import java.util.Iterator;

public interface I_ColeccionDeFacturas {

	void agregarFactura(I_Factura factura, int mes);
	
	void eliminarFactura(I_Factura factura);
	
	void pagarFactura(I_Factura factura);
	
	//Iterator<I_Factura> getFacturaIterator();
	
	int facturasSinPagar();
	
	Object clone() throws CloneNotSupportedException;
	
}
