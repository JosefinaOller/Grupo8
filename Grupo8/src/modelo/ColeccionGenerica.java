
package modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import servicios.I_Factura;

public class ColeccionGenerica<T extends Cloneable> implements Cloneable {

	private HashMap<Integer,T> coleccion;
	
	public ColeccionGenerica() {
		this.coleccion= new HashMap<Integer,T>();
	}
	
	public void agregaElemento(Integer valor,T elemento) {
		this.coleccion.put(valor,elemento);
	}
	
	public void removerElemento(Integer valor, T elemento) {
		this.coleccion.remove(valor, elemento);
	}
	
	public T getElemento(Integer valor) {
		return this.coleccion.get(valor);
	}
	
	public Iterator<T> getValuesIterator(){
		return this.coleccion.values().iterator();
	}
	
	public Object clone() throws CloneNotSupportedException {
		ColeccionGenerica clonado= null;
		clonado= (ColeccionGenerica) super.clone();
		Iterator<T> keys= clonado.coleccion.keySet().iterator();
		Iterator<T> values= clonado.coleccion.values().iterator();
		clonado.coleccion.clear();
		while (keys.hasNext() && values.hasNext()) {
			clonado.coleccion.put(keys.next(), values.next());
		}
		return clonado;
	}
}
