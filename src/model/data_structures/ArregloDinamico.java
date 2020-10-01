package model.data_structures;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 2019-01-23
 * Estructura de Datos Arreglo Dinamico de Strings.
 * El arreglo al llenarse (llegar a su maxima capacidad) debe aumentar su capacidad.
 * @author Fernando De la Rosa
 *
 *
 */

public class ArregloDinamico <T extends Comparable <T>> implements IArregloDinamico <T>
{
	/**
	 * Capacidad maxima del arreglo
	 */
	private int tamanoMax;
	/**
	 * Numero de elementos presentes en el arreglo (de forma compacta desde la posicion 0)
	 */
	private int tamanoAct;
	/**
	 * Arreglo de elementos de tamaNo maximo
	 */
	private Object elementos[ ];
	
	/**
	 * Construir un arreglo con la capacidad maxima inicial.
	 * @param max Capacidad maxima inicial
	 */
	public ArregloDinamico( int max )
	{
		elementos =  new Object[max];
		tamanoMax = max;
		tamanoAct = 0;
	}
	
 
	public void agregar( T dato ) // se tuvo que cambiar de tipo T a tipo objeto para que se pudieran agregar los arrays de String
	{
		if ( tamanoAct == tamanoMax )
		{  // caso de arreglo lleno (aumentar tamaNo)
			tamanoMax = 2 * tamanoMax;
			Object [ ] copia = elementos;
			elementos = new Object[tamanoMax];
			for ( int i = 0; i < tamanoAct; i++)
			{
				elementos[i] = copia[i];
			} 
			
			System.out.println("Arreglo lleno: " + tamanoAct + " - Arreglo duplicado: " + tamanoMax);
		}	
		elementos[tamanoAct]= dato;
//		System.out.println(dato);
		tamanoAct++;
//		System.out.println(tamanoAct);
	}

	public int darCapacidad() {
		return tamanoMax;
	}

	public int darTamano() {
		return tamanoAct;
	}

	public T darElemento(int i) {

		if(i < tamanoAct)
			return (T) elementos[i];

		else
			return null;
	}

	public T buscar(T dato) 
	{
		T rta = null;
		boolean terminar = false;

		for (int j = 0; j < elementos.length && !terminar; j++) 
		{

			if(((Comparable<T>) elementos[j]).compareTo(dato) == 0)
			{
				rta = (T) elementos[j];
				terminar = true;
			}

		}
		return rta;
	}

	public T eliminar(T dato) {

		ArrayList<T> lista = new ArrayList<>();
		
		T rta = null;
		
		for ( int i = 0; i < tamanoAct; i++)
		{
			if (((Comparable<T>) elementos[i]).compareTo(dato) != 0)
			{
				lista.add((T) elementos[i]);
			}
			
			else
				rta = (T) elementos[i];
		}
	
		Object[] copia = new Object[elementos.length];
			
		for (int i = 0; i < lista.size(); i++) {

			copia[i] = lista.get(i);
		}

		return rta;
	}

	public void addFirst(T element) 
	{
		T temp = (T)elementos[0];
		elementos[0] = element;
		agregar(temp);
	}

	public void addLast(T element) 
	{
		agregar(element);
	}
	
	public T removeFirst() {
		T temp = (T)elementos[0];
		eliminar(temp);
		tamanoAct--;
		return temp;
	}
	
	public T removeLast() {
		T temp = (T)elementos[tamanoAct-1];
		elementos[tamanoAct-1] = null;
		return temp;
	}

	public T firstElement() {
		return (T)elementos[0];
	}

	public T lastElement() {
		return (T)elementos[tamanoAct-1];
	}

	public boolean isEmpty() {
		boolean ret = false;
		if (tamanoAct == 0) 
			ret = true;
		return ret;
	}

	public int isPresent(T element) {
		int ret = -1;
		for (int i = 0; i < elementos.length; i++) 
		{
			T temp = (T)elementos[i];
			if (temp.compareTo(element) == 0)
				ret = i;
		}
		return ret;
	}

	public void exchange(int pos1, int pos2) {
		T temp = (T)elementos[pos2];
		elementos[pos2] = elementos[pos1];
		elementos[pos1] = temp;
	}

	public void changeInfo(int pos, T elem) {
		elementos[pos] = elem;
	}
}
