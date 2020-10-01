package model.data_structures;

import java.util.Arrays;
import java.util.Comparator;

public class Shell <T> {

	public static void sort(Comparable[] a)
	{ // Sort a[] into increasing order.
		int N = a.length;
		int h = 1;
		while (h < N/3) h = 3*h + 1; // 1, 4, 13, 40, 121, 364, 1093, ...
		while (h >= 1)
		{ // h-sort the array.
			for (int i = h; i < N; i++)
			{ // Insert a[i] among a[i-h], a[i-2*h], a[i-3*h]... .
				for (int j = i; j >= h && less(a[j], a[j-h]); j -= h)
					exch(a, j, j-h);
			}
			h = h/3;
		}
		
	}


	private static boolean less(Comparable v, Comparable w)
	{ return v.compareTo(w) < 0; }
	
	
	
	
	

	private static void exch(Comparable[] a, int i, int j)
	{ Comparable t = a[i]; a[i] = a[j]; a[j] = t; }

	
	public boolean lessEdit(Comparable comp1, Comparable comp2, int cual, Comparator comparador)
	{
		if(cual == 1)
			return comp1.compareTo(comp2) < 0;
		
		else
			return comparador.compare((T) comp1,  (T) comp2) < 0;
	}
	
	
	public void sortEdit(Comparable[] a, Comparator comparador)
	{ // Sort a[] into increasing order.
		int porCual = 1;
		if(comparador != null) porCual = 2;
		
		int N = a.length;
		int h = 1;
		while (h < N/3) h = 3*h + 1; // 1, 4, 13, 40, 121, 364, 1093, ...
		while (h >= 1)
		{ // h-sort the array.
			for (int i = h; i < N; i++)
			{ // Insert a[i] among a[i-h], a[i-2*h], a[i-3*h]... .
				for (int j = i; j >= h && lessEdit(a[j], a[j-h], porCual, comparador) ; j -= h)
					exch(a, j, j-h);
			}
			h = h/3;
		}
		
	}

}
