/**
 * 
 */
package model.data_structures;

import java.util.ArrayList;

/**
 * @author cajiv
 *
 */
public class SequentialSearch <K extends Comparable<K>, V extends Comparable<V>>
{// first node in the linked list
	public class NodeSS
	{ // linked-list node
		K key;
		V val;
		NodeSS next;
		NodeSS sim;
		int S;
		public NodeSS(K key, V val, NodeSS next)
		{
			this.key = key;
			this.val = val;
			this.next = next;
			sim = null;
			S = 0;
		}

		void setSim(NodeSS similar)
		{
			sim = similar;
		}
		/**
		 * @return the s
		 */
		public int getS() {
			return S;
		}

		/**
		 * @return the val
		 */
		public V getVal() {
			return val;
		}
	}

	public SequentialSearch() {
	}

	private int n;           // number of key-value pairs
	public NodeSS first;      // the linked list of key-value pairs

	public int size() {
		return n;
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	public boolean contains(K key) {
		return get(key) != null;
	}

	public V get(K key)
	{ // Search for key, return associated value.
		for (NodeSS x = first; x != null; x = x.next)
			if (key.equals(x.key))
			{
				return x.val;
			}
		// search hit
		return null; // search miss
	}

	public void delete(K key) {
		first = delete(first, key);
	}

	///// si quiero eliminar una Key, se eliminarian todos los datos relacionados a la Key?

	private NodeSS delete(NodeSS x, K key) {
		if (x == null) return null;
		if (key.equals(x.key)) {
			n--;
			return x.next;
		}
		x.next = delete(x.next, key);
		return x;
	}

	public void put(K key, V val)
	{ 
		if (val == null) {
			delete(key);
			return;
		}
		// Search for key. Update value if found; grow table if new.

		//Esto se debe cambiar para que en vez de cambiar el valor cree una lista con los iguales.
		for (NodeSS x = first; x != null; x = x.next)
			if (key.equals(x.key))
			{
				//				System.out.println("key: " + key + " igual a: "+x.key);
				if(x.sim == null)
				{
					x.setSim(new NodeSS(key, val, null));
					x.S++;
				}

				else
				{
					NodeSS viejoSim = x.sim;
					NodeSS nuevo = new NodeSS(key, val, null);
					nuevo.setSim(viejoSim);
					x.setSim(nuevo);
					x.S++;
				}
				//				System.out.println("en llave "+key+" hay "+x.S + " repetidos");
				return;
			}
		// si no encuentra una key igual
		first = new NodeSS(key, val, first);
		n++;// Search miss: add new node.
	}

	/////////////////////////////// si hay llaves repetidas igual solo la menciona una vez
	public Iterable<K> keys()  {
		Queue<K> queue = new Queue<K>();
		for (NodeSS x = first; x != null; x = x.next)
			queue.enqueue(x.key);
		return queue;
	}


	/////////////////////////////////// incluye los valores de las llaves repetidas
	public Iterable<V> values()  {
		Queue<V> queue = new Queue<V>();
		for (NodeSS x = first; x != null; x = x.next)
		{
			queue.enqueue(x.val);
			if(x.sim != null)
			{
				for (NodeSS y = x.sim; y != null ; y = y.sim) {

					queue.enqueue(y.val);
				}
			}

		}

		return queue;
	}

	/**
	 * @param key
	 * @return
	 */
	public ArrayList<V> getAll(K key) {
		ArrayList<V> lista = null;
		for (NodeSS x = first; x != null; x = x.next)
			if (key.equals(x.key))
			{
				lista = new ArrayList<>();
				lista.add(x.val);
				for (NodeSS y = x.sim; y != null; y = y.sim) 
				{
					lista.add(y.val);
				}
			}
		return lista;
	}

	//////////////////////////////////////////////////////////////////////////////////	

}
