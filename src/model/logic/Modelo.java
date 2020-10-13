package model.logic;

import java.io.FileReader;

import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

import com.opencsv.CSVReader;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReaderBuilder;

import model.data_structures.BST;
import model.data_structures.Queue;
import view.View;
import java.util.regex.Pattern;


/**
 * Definicion del modelo del mundo
 * @param <T>
 *
 */
public class Modelo<T> {
	/**
	 * Atributos del modelo del mundo
	 */

	private View vista= new View();

	final static String ACCIDENTES_2019 = "data/us_accidents_dis_2019.csv";

	private BST arbol;

	/**
	 * Constructor del modelo del mundo con capacidad predefinida
	 */
	public Modelo()
	{
		arbol = new BST();
	}

	int t = 0;
	public void cargarBST() throws Exception
	{
		try {
			Stopwatch timer = new Stopwatch();
			String casting = ACCIDENTES_2019;

			CSVParser parser1 = new CSVParserBuilder().withSeparator(',').build();

			FileReader fr1 = new FileReader(casting);

			CSVReader reader1 = new CSVReaderBuilder(fr1).withCSVParser(parser1).build();

			String[] fila1 = null;

			while((fila1 = reader1.readNext()) != null) 
			{

				if(! fila1[0].equals("ID"))
				{
					String fecha = fila1[4].substring(0, 10);
					Date date = new SimpleDateFormat("yyyy-MM-dd").parse(fecha);

					Accidente nuevo = new Accidente(date,Integer.parseInt(fila1[3]));

					arbol.put(date.toString(), nuevo);
					t++;
//					System.out.println(t);
				}
			}

			reader1.close();

			double time = timer.elapsedTime();
			vista.printMessage("Tiempo tomado: "+ time);
		} 
		catch (Exception e) {
			throw new Exception(e.getMessage() +"pifeo");
		}

	}
	
	public int darNumTotalAccidentes()
	{
		ArrayList<T> x = (ArrayList<T>)arbol.valuesInRange(arbol.min(), arbol.max());
			return x.size();
	}
	
	public int darAltura() {
		return arbol.height();
	}
	
	public String darMin() {
		return (String)arbol.min();
	}
	
	public String darMax() {
		return (String)arbol.max();
	}
	
	public int darNumKeys() {
		Queue x = (Queue)arbol.keySet();
		return x.size();
	}
	
	public String detallesGravedad(String key) {
		int gravedad1 = 0;
		int gravedad2 = 0;
		int gravedad3 = 0;
		int gravedad4 = 0;
		String rta = "";
		ArrayList<Accidente> x = (ArrayList<Accidente>)arbol.get(key);
		for (int i = 0; i < x.size(); i++) {
			if (x.get(i).getSeverity() == 1) 
				gravedad1++;
			else if (x.get(i).getSeverity() == 2)
				gravedad2++;
			else if (x.get(i).getSeverity() == 3)
				gravedad3++;
			else
				gravedad4++;
		}
		rta = "El numero de accidentes por gravedad es: \nGravedad 1: " + gravedad1 + "\nGravedad 2: " + gravedad2 + "\nGravedad 3: " + gravedad3 + "\nGravedad 4: " + gravedad4;
		return rta;
	}

	public void get(String key)
	{
		ArrayList<Accidente> lista = arbol.get(key);

		for (int i = 0; i < lista.size(); i++) {
			vista.printMessage("Fecha: " + lista.get(i).getFechaInicio().toString() + " Severidad: "+ lista.get(i).getSeverity());
		}
	}











}