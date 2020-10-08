package controller;

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
				modelo.cargarBST();
				
//				view.printMessage("Información primera película: "+ modelo.impresa(modelo.getPrimeraLP()));
//				view.printMessage("Información última película: "+ modelo.impresa(modelo.getUltimaLP()));
//				view.printMessage(modelo.darTamanoLP()+"");
//				view.printMessage(modelo.darNumElemLP()+"");
				break;
				
//			case 2:
//				view.printMessage("--------- \n Cargar Datos de los CSV en Lista Enlazada: ");
//				modelo.cargarDatosEncadenada();
//				view.printMessage("Información primera películas: "+ modelo.impresa(modelo.darElementoEncadenada(1)));
//				view.printMessage("Información última película: "+ modelo.impresa(modelo.darElementoEncadenada(modelo.darTamanoEncadenada())));
//				view.printMessage("Total de películas " + (modelo.darTamanoEncadenada()) + "\n---------");	
//				break;

//			case 3:
//				view.printMessage("--------- \n Crear Ranking de películas ");
//				view.printMessage("Ingresar número de películas en el ranking");
//				int x = lector.nextInt();
//				if(x < 10)
//				{
//					view.printMessage("El ranking debe ser de mínimo 10 películas.");
//					break;
//				}
//				view.printMessage("1 para ordenar por vote count, 2 para vote average");
//				int vote = lector.nextInt();
//				view.printMessage("1 para Worst, 2 para Best");
//				int forma = lector.nextInt();		
//				modelo.rankingPelisDinamica(x, vote, forma);
//				break;
//
//			case 5:
//				view.printMessage("--------- \n Digite el nombre del actor \n---------");
//				lector.nextLine();
//				dato = lector.nextLine();
//				modelo.peliculasPorActor(dato);
//				break;
//
//			case 6:
//				view.printMessage("--------- \nPeliculas de un genero:");
//				view.printMessage("Ingrese el genero");
//				lector.nextLine();
//				dato = lector.nextLine();
//				view.printMessage("Peliculas del genero " + dato + ":");
//				String rta2 = modelo.entenderGenero(dato);
//				view.printMessage(rta2);
//				break;
//
//			case 7:
//				view.printMessage("--------- \n Ranking por Género: ");
//				view.printMessage("Ingresar número de películas en el ranking");
//				int x2 = lector.nextInt();
//				view.printMessage("Ingresar género para el ranking");
//				lector.nextLine();
//				String genero = lector.nextLine();
//				view.printMessage("1 para ordenar por vote count, 2 para vote average");
//				int vote2 = lector.nextInt();
//				view.printMessage("1 para Worst, 2 para Best");
//				int forma2 = lector.nextInt();
//				modelo.rankingGeneroDinamica(x2, genero, vote2, forma2);
//				break;

			case 8: 
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
