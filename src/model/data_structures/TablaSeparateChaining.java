/**
 * 
 */
package model.data_structures;

import java.util.ArrayList;

import model.data_structures.SequentialSearch.NodeSS;
import model.logic.Pelicula;

/**
 * @author 
 *
 */
public class TablaSeparateChaining <K extends Comparable<K>, V extends Comparable<V>>  implements TablaSimbolos <K, V> {

	private int N, M,T;
	
	private SequentialSearch<K, V>[] st;

	/**
	 * 
	 */
	public TablaSeparateChaining() {
		this(997);
	}

	public TablaSeparateChaining (int M)
	{
		this.M = M;
		st = (SequentialSearch<K, V>[]) new SequentialSearch[M];
		for (int i = 0; i < M; i++)
			st[i] = new SequentialSearch(); 
	}

	public int hash(K key)
	{ return (key.hashCode() & 0x7fffffff) % M; }


	// resize the hash table to have the given number of chains,
	// rehashing all of the keys
	private void resize(int chains) {
		System.out.println("entra al resize, M inicial: " + M+ " ,N in: "+ N + " T en: "+T);
		System.out.println("resize a: "+chains);
		TablaSeparateChaining<K,V> temp = new TablaSeparateChaining<K, V>(chains);
		for (int i = 0; i < M; i++) {
			for (K key : st[i].keys()) {
				temp.put(key, st[i].get(key));
			}
		}
		this.M  = temp.M;
		this.N  = temp.N;
		this.st = temp.st;
	}


	@Override
	public void put(K key, V val) {
		if (key == null) throw new IllegalArgumentException("first argument to put() is null");
		if (val == null) {
			remove(key);
			System.out.println("Entra a val = null de put");
			return;
		}

		if (N/M >= 5.0) resize(2*M); 

		int i = hash(key);
		if (!st[i].contains(key)) N++;
		st[i].put(key, val);
		T++;
		
//		System.out.println("en "+ i +" se introduce llave " + key);
//		System.out.println(size());

	}
	
	@Override
	public V get(K key) {
		if (key == null) throw new IllegalArgumentException("argument to get() is null");
		int i = hash(key);
		return st[i].get(key);
	}
	
	
	public ArrayList<V> getAll(K key) {
		if (key == null) throw new IllegalArgumentException("argument to get() is null");
		int i = hash(key);
		return st[i].getAll(key);
	}
	
	public Pelicula darPrimeroPosicion(int i)
	{
		return (Pelicula) st[i].first.val;
	}
	@Override
	public V remove(K key) {
		V rta = null;

		if (key == null) throw new IllegalArgumentException("argument to delete() is null");

		int i = hash(key);
		if (st[i].contains(key)) N--;
		rta = st[i].get(key);
		st[i].delete(key);

		if (N/M <= 2) resize(M/2);

		return rta;
	}

	@Override
	public boolean contains(K key) {
		if (key == null) throw new IllegalArgumentException("argument to contains() is null");
		return get(key) != null;
	}

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	@Override
	public int size() {
		return N;
	}
	
	/**
	 * @return the m
	 */
	public int getM() {
		return M;
	}
	
	
	public int totalElem()
	{
		return T;
	}

	/**
	 * 
	 * @return Queue(cola) con todas las llaves de la tabla
	 */
	public Iterable<K> keySet() {
		Queue<K> queue = new Queue<K>();
		for (int i = 0; i < M; i++) {
			for (K key : st[i].keys())
				queue.enqueue(key);
		}
		return queue;
	} 
	
	/**
	 * 
	 * @return Queue(cola) con todos los valores de la tabla
	 */
	public Iterable<V> valueSet() {
		Queue<V> queue = new Queue<V>();
		for (int i = 0; i < M; i++) {
			for (V val: st[i].values())
				queue.enqueue(val);
		}
		return queue;
	} 
}
