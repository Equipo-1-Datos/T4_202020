package controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;


import model.logic.Modelo;
import view.View;

public class Controller {

	/* Instancia del Modelo*/
	private Modelo modelo;

	/* Instancia de la Vista*/
	private View view;

	/**
	 * Crear la vista y el modelo del proyecto
	 * @param capacidad tamaNo inicial del arreglo
	 */
	public Controller ()
	{
		view = new View();
		modelo = new Modelo();
	}

	public void run() throws Exception 
	{
		Scanner lector = new Scanner(System.in);
		boolean fin = false;
		String dato = null;
		
		while( !fin ){
			view.printMenu();

			int option = lector.nextInt();
			switch(option){
			case 1:
				view.printMessage("---------");
				modelo.cargarBST();
				view.printMessage("El numero total de accidentes en el a�o 2019 fue: " + modelo.darNumTotalAccidentes());
				view.printMessage("El numero de llaves ingresadas fue de: " + modelo.darNumKeys());
				view.printMessage("La altura del arbol es: " + modelo.darAltura());
				view.printMessage("El valor minimo de la llave es: " + modelo.darMin() + ". Y el maximo es: " + modelo.darMax());
				view.printMessage("---------");
				break;
				
			case 2:
				view.printMessage("---------");
				view.printMessage("Ingresar fecha en formato AAAA-MM-DD: ");
				lector.nextLine();
				dato = lector.nextLine();
				Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dato);
				modelo.get(date.toString());
				view.printMessage(modelo.detallesGravedad(date.toString()));
				view.printMessage("---------");
				break;

			case 3: 
				view.printMessage("--------- \n Hasta pronto !! \n---------"); 
				lector.close();
				fin = true;
				break;	

			default: 
				view.printMessage("--------- \n Opcion Invalida !! \n---------");
				break;
			}
		}

	}	
}