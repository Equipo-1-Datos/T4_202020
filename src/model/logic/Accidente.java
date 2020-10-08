/**
 * 
 */
package model.logic;

import java.util.Date;

/**
 * @author cajiv
 *
 */
public class Accidente implements Comparable <Accidente> {

	Date fechaInicio;
	int severity;
	
	
	public Accidente(Date fechaIn, int severidad)
	{
		fechaInicio = fechaIn;
		severity = severidad;
	}
	@Override
	public int compareTo(Accidente o) {
		// TODO Auto-generated method stub
		return 0;
	}

}
