package model.data_structures;

import java.util.Iterator;

public class ListaEncadenada <T extends Comparable<T>> implements ILista<T>
//, Iterable
{

	Node<T> list;
	int listSize;

	public class Node <T>
	{
		Node<T> next;
		T elemento;

		public Node(T pElemento)
		{
			elemento = pElemento;
			next = null;
		}

		public T getElemento() {
			return elemento;
		}

		public Node<T> getNext() {
			return next;
		}

		public void setNext(Node<T> sig) {
			next = sig;
		}

		public void setElemento(T pElemento)
		{
			elemento = pElemento;
		}
	}


	public ListaEncadenada(T item) {

		list = new Node<T> (item);
		listSize = 1;

	}
	
	public Node<T> inicioLista()
	{
		return list;
	}

	@Override
	public void addFirst(T element) {
		Node <T> newHead = new Node<> (element);
		newHead.setNext(list);
		list = newHead;
		listSize++;

	}

	@Override
	public void addLast(T element) {
		Node <T> newNode = new Node<> (element);
		if(list == null) {
			list = newNode;
		}
		else {
			Node <T> actual = list;
			while(actual.getNext() != null) {
				actual = actual.getNext();
			}
			actual.setNext(newNode);
		}
		listSize++;
	}

	@Override
	public void insertElement(T element, int pos) {

		if(list == null)
		{
			System.out.println("No se puede eliminar nada si la lista esta vacia");
			return;
		}

		else if(listSize < pos)
		{
			System.out.println("No existe posicion de elemento que se quiere eliminar");
			return;
		}

		if(pos == 1)
		{
			addFirst(element);
		}

		Node<T> nuevo = new Node<T>(element);
		int posicion = 1;
		boolean termina = false;
		Node <T> actual = list;
		while(actual.getNext() != null && !termina) 
		{
			if(posicion == pos-1)// es el nodo antes del que se desea eliminar
			{
				nuevo.setNext(actual.getNext());
				actual.setNext(nuevo);
				listSize ++;
				termina = true;
			}
			actual = actual.getNext();
			posicion ++;
		}
	}

	public T removeFirst() {

		T rta = null;
		if(list == null)
		{
			System.out.println("La lista esta vacia, no se puede eliminar el primero");
		}

		else
		{
			rta = list.getElemento();
			list = list.getNext();
			listSize --;
		}

		return rta;
	}

	@Override// retorna elemento eliminado, null si no elimina nada.
	public T removeLast() {

		T rta = null;
		Node <T> actual = list;
		while(actual.getNext().getNext() != null) 
		{
			actual = actual.getNext();
		}
		rta = actual.getNext().getElemento();
		actual.setNext(null);
		listSize --;
		return rta;
	}

	@Override
	public T deleteElement(int pos) // enumeracion desde 1
	{

		if(pos == 1)
		{
			return removeFirst();
		}

		else if(pos == listSize)
		{
			return removeLast();
		}

		T rta = null;
		if(list == null)
		{
			System.out.println("No se puede eliminar nada si la lista esta vacia");
			return rta;
		}

		else if(listSize < pos)
		{
			System.out.println("No existe posicion de elemento que se quiere eliminar");
			return rta;
		}
		int posicion = 1;
		boolean termina = false;
		Node <T> actual = list;
		while(actual.getNext() != null && !termina) 
		{
			actual = actual.getNext();
			posicion ++;

			if(posicion == pos-1)// es el nodo antes del que se desea eliminar
			{
				rta = actual.getNext().getElemento();
				actual.setNext(actual.getNext().getNext());
				listSize --;
				termina = true;
			}
		}

		return rta;

	}

	@Override
	public T firstElement() {
		return list.getElemento();
	}

	@Override
	public T lastElement() {
		Node <T> actual = list;
		while(actual.getNext() != null) {
			actual = actual.getNext();
		}
		return actual.getElemento();
	}

	@Override
	public T getElement(int pos) {

		T rta = null;
		if(list == null)
		{
			System.out.println("No se puede retornar nada si la lista esta vacia");
			return rta;
		}

		else if(listSize < pos)
		{
			System.out.println("No existe posicion de elemento que se quiere retornar");
			return rta;
		}

		int posicion = 1;
		boolean termina = false;
		Node <T> actual = list;
		while(actual != null && !termina) 
		{
			if(posicion == pos)
			{
				rta = actual.getElemento();
				termina = true;
			}
			actual = actual.getNext();
			posicion ++;
		}
		return rta;
	}



	@Override
	public int size() {
		return listSize;
	}

	@Override
	public boolean isEmpty() {
		return list == null;
	}

	@Override// Busca el elemento y retorna su posicion, si no encuentra retorna -1 
	// y -2 si lista esta vacia
	public int isPresent(T element) {
		int rta = -1;
		if(list == null)
		{
			System.out.println("No se puede encontrar nada si la lista esta vacia");
			return -1;
		}
		int posicion = 1;
		boolean termina = false;
		Node <T> actual = list;
		while(actual != null && !termina) 
		{
			if(actual.getElemento().compareTo(element) == 0)
			{
				rta = posicion;
				termina = true;
			}
			actual = actual.getNext();
			posicion ++;
		}
		return rta;
	}

	@Override
	public void exchange(int pos1, int pos2) {

		if(list == null)
		{
			System.out.println("No se puede encontrar nada si la lista esta vacia");
			return;
		}

		T info1 = getElement(pos1);
		T info2 = getElement(pos2);

		if(info1 != null && info2 != null)
		{
			int posicion = 1;
			boolean termina1 = false;
			boolean termina2 = false;
			boolean full = false;
			Node <T> actual = list;
			while(actual != null && !full) 
			{
				if(posicion == pos1)
				{
					actual.setElemento(info2);
					termina1 = true;
				}

				else if(posicion == pos2)
				{
					actual.setElemento(info1);
					termina2 = true;
				}
				actual = actual.getNext();
				posicion ++;

				if(termina1 && termina2 )
				{
					full = true;
				}
			}
		}

		else
		{
			System.out.println("no se puede cambiar si no estan");
			return;
		}
	}



	@Override
	public void changeInfo(int pos, T elem) {

		if(getElement(pos) != null)
		{
			int posicion = 1;
			boolean termina = false;
			Node <T> actual = list;
			while(actual != null && !termina) 
			{
				if(posicion == pos)
				{
					actual.setElemento(elem);
					termina = true;
				}
				actual = actual.getNext();
				posicion ++;
			}
		}

	}

	/**
	 * 
	 */
	public void incrementListSize() {
		
		listSize ++;
	}

//	@Override
//	public Iterator<T> iterator() {
//
//		return new Iterator<T> 
//		{
//			Node <T> act = null;
//				
//			public boolean hasNext()
//			{
//				if (listSize == 0);
//					return false;
//
//				if (act == null)
//					return true;
//				return act.getNext() != null;
//			}
//			
//			public T next()
//			{
//				if (act == null)
//					act = list;
//				else
//					act = act.getNext();
//
//				return act.getElemento();
//			}
//		}
//	}

	// realizar UML diagrama de clase







}
