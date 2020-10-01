/**
 * 
 */
package model.data_structures;

/**
 * @author cajiv
 *
 */
public interface TablaSimbolos <K extends Comparable<K>,V>{

	
	void put(K key, V value);
	
	public V get(K key);
	
	public V remove(K key);
	
	public boolean contains (K key);
	
	public boolean isEmpty();
	
	/** 
	 * 
	 * @return Numero de tuplas en la tabla
	 */
	public int size();
	
	/**
	 * 
	 * @return Todas las llaves en la tabla
	 */
	public Iterable<K> keySet();
	
	/**
	 * 
	 * @return Todos los valores almacenados en la tabla
	 */
	public Iterable<V> valueSet();
	
}
