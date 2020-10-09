/**
 * 
 */
package model.logic;

import java.util.Date;

/**
 * @author cajiv
 *
 */
public class Accidente {

	Date fechaInicio;
	int severity;
	
	
	public Accidente(Date fechaIn, int severidad)
	{
		fechaInicio = fechaIn;
		severity = severidad;
	}
	
	/**
	 * @return the fechaInicio
	 */
	public Date getFechaInicio() {
		return fechaInicio;
	}
	
	/**
	 * @return the severity
	 */
	public int getSeverity() {
		return severity;
	}


}
