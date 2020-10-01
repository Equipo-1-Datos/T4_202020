package test.data_structures;

import model.data_structures.ArregloDinamico;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestArregloDinamico {

	private ArregloDinamico arreglo;
	private static int TAMANO=100;
	
	@Before
	public void setUp1() {
		arreglo= new ArregloDinamico(TAMANO);
	}

	public void setUp2() {
		for(int i =0; i< TAMANO*2; i++){
			arreglo.agregar(""+i);
		}
	}

	@Test
	public void testArregloDinamico() {
		assertTrue(arreglo != null);
		assertEquals(0, arreglo.darTamano());
	}
	
	@Test
	public void testAgregar()
	{
		setUp1();
		arreglo.agregar(1+"");
		assertTrue("no da el elemento deseado", arreglo.darElemento(0).equals(1+""));
	}
	
	@Test 
	public void testDarCapacidad()
	{
		setUp1();
		assertTrue("La capacidad es incorrecta", arreglo.darCapacidad() == 100);
	}
	
	@Test
	public void testDarTamano()
	{
		setUp1();
		assertTrue("El tamano actual es incorrecto", arreglo.darTamano() == 0);
	}

	@Test
	public void testDarElemento() {
		
		setUp2();
		assertTrue("no da el elemento deseado", arreglo.darElemento(100).equals(100+""));
		
	}
	
	@Test
	public void testBuscar()
	{
		setUp2();
		assertTrue("El elemento no coincide", arreglo.buscar(""+100).equals(""+100));
	}
	
	@Test 
	public void testEliminar()
	{
		setUp1();
		arreglo.agregar(""+1);
		assertTrue("El elemento no coincide", arreglo.eliminar(""+1) == ""+1);
	}

}
