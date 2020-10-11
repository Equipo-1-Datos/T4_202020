/**
 * 
 */
package model.data_structures;

import java.util.ArrayList;

/**
 * @author cajiv
 *
 */
public interface TablaSimbolosOrdenada <K extends Comparable<K>,V>  {
	
	public int size();
	
	public boolean isEmpty();
	
	public ArrayList<V> get(K key);
	
	public int getHeight(K key);
	
	public boolean contains(K key);
	
	public void put(K key, V val);
	
	public int height();
	
	public K min();
	
	public K max();
	
	public Iterable<K> keySet();
	
	public Iterable<K> keysInRange(K init, K end);
	
	public Iterable<V> valuesInRange(K init, K end);
	
	
	
}