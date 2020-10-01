package view;

import model.logic.Modelo;

public class View 
{
	    /**
	     * Metodo constructor
	     */
	    public View()
	    {
	    	
	    }
	    
		public void printMenu()
		{
			System.out.println("1. Cargar Peliculas a Linear Probing");
			System.out.println("2. Cargar Peliculas a Separate Chaining");
			System.out.println("3. Peliculas por año y compañía de producción, Linear Probing");
			System.out.println("4. Peliculas por año y compañía de producción, Separate Chaining ");
			System.out.println("5. Hacer pruebas de desempeño, Linear Probing");
			System.out.println("6. Hacer pruebas de desempeño, Separate Chaining");
			System.out.println("8. Exit");
			
			System.out.println("Dar el numero de opcion a resolver, luego oprimir tecla Return: (e.g., 1):");
		}

		public void printMessage(String mensaje) {

			System.out.println(mensaje);
		}		
		
		public void printModelo(Modelo modelo)
		{
			System.out.println(modelo);
		}
}
