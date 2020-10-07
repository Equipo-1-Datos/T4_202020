package model.logic;

import java.io.FileReader;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.ThreadLocalRandom;

import com.opencsv.CSVReader;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReaderBuilder;

import model.data_structures.ArregloDinamico;
import model.data_structures.IArregloDinamico;
import model.data_structures.TablaSeparateChaining;
import model.data_structures.SequentialSearch.NodeSS;
import view.View;
import java.util.regex.Pattern;



import model.logic.Pelicula;

/**
 * Definicion del modelo del mundo
 * @param <T>
 *
 */
public class Modelo<T> {
	/**
	 * Atributos del modelo del mundo
	 */
	//	private ArregloDinamico datos;
	//
	//	private ListaEncadenada encadenada;

	private View vista= new View();

	private ArregloDinamico<Pelicula> datos;
	final static String CASTING_SMALL = "data/MoviesCastingRaw-small.csv";
	final static String DETAILS_SMALL = "data/SmallMoviesDetailsCleaned.csv";
	final static String CASTING_ALL = "data/AllMoviesCastingRaw.csv";
	final static String DETAILS_ALL = "data/AllMoviesDetailsCleaned.csv";

	private TablaSeparateChaining tablaSC;


	/**
	 * Constructor del modelo del mundo con capacidad predefinida
	 */
	public Modelo()
	{
		tablaSC = new TablaSeparateChaining<>();
	}

	/**
	 * Constructor del modelo del mundo con capacidad dada
	 * @param tamano
	 */
	public Modelo(int capacidad)
	{
		//		datos = new ArregloDinamico(capacidad);
	}

	/**
	 * Servicio de consulta de numero de elementos presentes en el modelo 
	 * @return numero de elementos presentes en el modelo
	 */


	public String impresa (Pelicula peli)
	{
		return "ID: "+ peli.getId() + ", release: "+peli.getReleaseDate() + ", Act1: "+ peli.getActor1()+ ", Act2: " +peli.getActor1() + ", Act3: "+ peli.getActor3()
		+ ", Act4: "+ peli.getActor4() + ", Act5: "+peli.getActor5() + ", Genero: "+ peli.getGenero() + ", Titulo: "+ peli.getTitle() + ", Compania Prod: "+ peli.getProdCompany();
	}

	public Pelicula get(int i)
	{
		return tablaSC.darPrimeroPosicion(i);
	}

	public int darTamanoSC()
	{
		return tablaSC.getM();
	}

	public int darNumElemSC()
	{
		return tablaSC.size();
	}

	public int darTotalElemSC()
	{
		return tablaSC.totalElem();
	}

	int t = 0;
	public void cargarSeparateChaining() throws Exception
	{
		try {
			Stopwatch timer = new Stopwatch();
			String casting = CASTING_ALL;
			String details = DETAILS_ALL;

			CSVParser parser1 = new CSVParserBuilder().withSeparator(';').build();
			CSVParser parser2 = new CSVParserBuilder().withSeparator(';').build();

			FileReader fr1 = new FileReader(casting);
			FileReader fr2 = new FileReader(details);

			CSVReader reader1 = new CSVReaderBuilder(fr1).withCSVParser(parser1).build();

			CSVReader reader2 = new CSVReaderBuilder(fr2).withCSVParser(parser2).build();

			String[] fila1 = null;
			String[] fila2 = null;

			while((fila1 = reader1.readNext()) != null && (fila2 = reader2.readNext()) != null) 
			{

				if(! fila1[0].equals("id"))
				{
					Pelicula nueva = new Pelicula(fila1[0], fila1[1], fila1[3], fila1[5], fila1[7], fila1[9],
							fila2[2], fila2[16], fila2[10], fila2[8]);

					if(!nueva.getReleaseDate().equals("none") && !nueva.getReleaseDate().equals("") && !nueva.getProdCompany().equals("none"))
					{
						String key = nueva.getProdCompany() + nueva.getReleaseDate();
						tablaSC.put(key, nueva);
						t++;
						//						System.out.println("Numero de agregadas "+ t);	
					}

				}
			}

			reader1.close();
			reader2.close();

			double time = timer.elapsedTime();
			vista.printMessage("Tiempo tomado: "+ time);
		} 
		catch (Exception e) {
			throw new Exception(e.getMessage() +"pifeo");
		}

	}

	public void pelisCompProdYAnioSC(String key)
	{
		Object[] lista = tablaSC.getAll(key).toArray();

		for (int i = 0; i < lista.length; i++) 
		{
			vista.printMessage(impresa( (Pelicula) lista[i]));
		}

		vista.printMessage("El numero de peliculas asi es "+ lista.length);

	}



	// cargar peliculas a arreglo dinamico, con math.random extraer posiciones del
	// arreglo al azar y sacar keys de estos (compania+anioProd).

	public void pruebaSC() throws Exception
	{
		if(datos == null)
		{
			datos = new ArregloDinamico<>(1000);
			cargarDatos();
		}
		float suma = 0;
		for (int i = 0; i < 800; i++) 
		{
			Stopwatch timer = new Stopwatch();
			int randomNum = ThreadLocalRandom.current().nextInt(0, datos.darTamano());
			String comp = datos.darElemento(randomNum).prodCompany;
			String date = datos.darElemento(randomNum).releaseDate;
			String key = comp+date;
			tablaSC.getAll(key);
			double time = timer.elapsedTime();
			suma += time;
		}

		for (int i = 0; i < 200; i++) 
		{
			Stopwatch timer = new Stopwatch();
			tablaSC.getAll("holi");	
			double time = timer.elapsedTime();
			suma += time;
		}
		vista.printMessage("Tiempo Promedio SC: "+ suma/1000);
	}





	public void cargarDatos() throws Exception
	{
		try {
			Stopwatch timer = new Stopwatch();
			String casting = CASTING_ALL;
			String details = DETAILS_ALL;

			CSVParser parser1 = new CSVParserBuilder().withSeparator(';').build();
			CSVParser parser2 = new CSVParserBuilder().withSeparator(';').build();

			FileReader fr1 = new FileReader(casting);
			FileReader fr2 = new FileReader(details);

			CSVReader reader1 = new CSVReaderBuilder(fr1).withCSVParser(parser1).build();

			CSVReader reader2 = new CSVReaderBuilder(fr2).withCSVParser(parser2).build();

			String[] fila1 = null;
			String[] fila2 = null;

			while((fila1 = reader1.readNext()) != null && (fila2 = reader2.readNext()) != null) 
			{	
				if(! fila1[0].equals("id"))
				{
					Pelicula nueva = new Pelicula(fila1[0], fila1[1], fila1[3], fila1[5], fila1[7], fila1[9],
							fila2[2], fila2[16], fila2[10], fila2[8]);

					if(!nueva.getReleaseDate().equals("none") && !nueva.getReleaseDate().equals("") && !nueva.getProdCompany().equals("none"))
					{
						datos.agregar(nueva);
					}
				}
			}

			reader1.close();
			reader2.close();

			double time = timer.elapsedTime();
			vista.printMessage("Tiempo tomado en cargar los datos al arreglo dinamico: "+ time);
		} 
		catch (Exception e) {
			throw new Exception(e.getMessage() +"pifeo");
		}

	}





	// cargar peliculas a arreglo dinamico, con math.random extraer posiciones del
	// arreglo al azar y sacar keys de estos (compania+anioProd).













}
