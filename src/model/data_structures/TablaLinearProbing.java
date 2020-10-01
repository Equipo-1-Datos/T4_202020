/**
 * 
 */
package model.data_structures;

import java.util.ArrayList;

import model.data_structures.SequentialSearchLP.NodeSSLP;
import model.logic.Pelicula;

/**
 * @author cajiv
 *
 */
public class TablaLinearProbing <K extends Comparable<K>, V extends Comparable<V>> implements TablaSimbolos <K, V> {

	private int n,m;
	private K[] keys;
	//private ArregloDinamico<V>[] values;
	private SequentialSearchLP<V>[] cosa;

	public TablaLinearProbing(int m)
	{
		cosa = (SequentialSearchLP<V>[]) new SequentialSearchLP[m];
		this.m = m;
		n = 0;
		keys = (K[]) new Comparable[m];
	}

	private int hash(K key) 
	{
		return (key.hashCode() & 0x7fffffff) % m;
	}

	public void resize(int m)
	{
		TablaLinearProbing<K, V> temp = new TablaLinearProbing<K, V>(m);
		for (int i = 0; i < this.m; i++)
		{
			if (keys[i] != null)
			{
				for (NodeSSLP x = cosa[i].first; x != null; x = x.next) 
				{
					temp.put(keys[i], (V) x.val);
				}
			}
			/**if (keys[i] != null) 
			{
				ArregloDinamico<V> arreglo = values[i];
				for (int j = 0; j < arreglo.darTamano(); j++)
				{
					temp.put(keys[i], arreglo.darElemento(j));
				}
			}*/
		}
		keys = temp.keys;
		//values = temp.values;
		this.m = temp.m;
		//this.n = temp.n;
	}

	public void put(K key, V value) 
	{
		if (key == null)
			throw new IllegalArgumentException("La llave es nula.");
		if (value == null) 
		{
			remove(key);
			return;
		}
		if (n/m > 0.75)
			resize(2*m);

		int i;
		for (i = hash(key); keys[i] != null; i = (i + 1) % m)
		{
			if (keys[i].equals(key))
			{
				//Cambiar por put
				cosa[i].put(value);
				System.out.println(key);
				return;
			}
		}

		keys[i] = key;
		//Cambiar por put 
		cosa[i].put(value);
		System.out.println(key);
		n++;
	}

	public ArrayList<V> getValues(K key)
	{
		if (key == null)
			throw new IllegalArgumentException("La llave es nula.");

		for (int i = hash(key); keys[i] != null; i = (i + 1) % m)
			if (keys[i].equals(key))
				return cosa[i].getAll();

		return null;
	}

//	public ArregloDinamico<V> removeValues(K key) 
//	{
//		ArregloDinamico<V> aRetornar = null;
//		if (key == null) 
//			throw new IllegalArgumentException("argument to delete() is null");
//		if (!contains(key))
//			return null;
//
//		// Encuentra la posicion de la key.
//		int i = hash(key);
//		while (!key.equals(keys[i])) 
//		{
//			i = (i + 1) % m;
//		}
//
//		// Elimina la key y el valor.
//		keys[i] = null;
//		aRetornar = values[i];
//		values[i] = null;
//
//		// Hace rehash a todas las siguientes keys.
//		i = (i + 1) % m;
//		while (keys[i] != null)
//		{
//			// Elimina y reinserta
//			K keyToRehash = keys[i];
//			ArregloDinamico<V> valueToRehash = values[i];
//			keys[i] = null;
//			values[i] = null;
//			n--;
//			for (int j = 0; j < valueToRehash.darTamano(); j++) 
//				put(keyToRehash, valueToRehash.darElemento(j));
//			i = (i + 1) % m;
//		}
//
//		n--;
//
//		// Reduce el tamano a la mitad si esta lleno el 12.5% o menos.
//		if (n > 0 && n <= m/8) 
//			resize(m/2);
//		return aRetornar;
//	}

	public boolean contains(K key) 
	{
		if (key == null) 
			throw new IllegalArgumentException("La llave es nula.");
		return getValues(key) != null;
	}

	public boolean isEmpty() 
	{
		return size() == 0;
	}

	public int size()
	{
		return n;
	}

	public Iterable<K> keySet() 
	{
		Queue<K> queue = new Queue<K>();
		for (int i = 0; i < m; i++)
			if (keys[i] != null) 
				queue.enqueue(keys[i]);
		return queue;
	}

//	public Queue<V> valueSet() 
//	{
//		Queue<V> queue = new Queue<V>();
//		for (int i = 0; i < m; i++)
//			if (keys[i] != null)
//			{
//				ArregloDinamico<V> x = values[i];
//				for (int j = 0; j < x.darTamano(); j++)
//				{
//					queue.enqueue(x.darElemento(j));
//				}
//			}
//		return queue;
//	}

	@Override
	public V get(K key) {
		return null;
	}

	@Override
	public V remove(K key) {
		return null;
	}

//	public V darPrimeraPelicula()
//	{
//		return values[0].darElemento(0);
//	}
//
//	public V darUltimaPelicula()
//	{
//		return values[m].darElemento(values[m].darTamano());
//	}

	public int darM()
	{
		return m;
	}

}
