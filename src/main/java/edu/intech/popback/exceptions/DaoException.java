package edu.intech.popback.exceptions;

/**
 * La classe DaoException est une classe d'exception personnalisée qui étend la classe Exception
 */
public class DaoException extends Exception {

	private static final long serialVersionUID = 3090944451411286257L;
	
	// Le constructeur qui va prendre en charge nos erreurs de Dao (exception)
	public DaoException(String message, Throwable cause) {
		super(message, cause);
	}

}
